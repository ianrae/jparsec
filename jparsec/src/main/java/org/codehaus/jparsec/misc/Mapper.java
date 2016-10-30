package org.codehaus.jparsec.misc;

import static org.codehaus.jparsec.internal.util.Checks.checkArgument;
import static org.codehaus.jparsec.internal.util.Checks.checkNotNullState;
import static org.codehaus.jparsec.internal.util.Checks.checkState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.functors.Binary;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Unary;
import org.codehaus.jparsec.internal.util.Lists;
import static org.codehaus.jparsec.internal.util.Checks.checkArgument;
import static org.codehaus.jparsec.internal.util.Checks.checkNotNullState;
import static org.codehaus.jparsec.internal.util.Checks.checkState;

/**
 * Allows mapping arbitrary number of {@link Parser} results  to an object of {@code T} type-safely
 * using the {@code map} method defined in subclass, or the curried constructor
 * defined in the target class.
 * 
 * <p> The {@link #sequence(Parser[])} method creates a parser that runs a series of
 * parser objects and maps the return values via the {@code map} method defined in the
 * subclass (or the curried constructor in target class).
 * 
 * <p> For example: <pre>
 * {@code
 * Parser<Foo> fooParser = new Mapper<Foo>() {
 *   Foo map(String s, Integer n, Bar bar, Baz baz) {
 *     return new Foo(s, n, bar, baz);
 *   }
 * }.sequence(stringParser, integerParser, barParser, bazParser);
 * }
 * </pre>
 * 
 * <p> Alternatively, instead of sequencing the operands and operators directly,
 * a parser of {@link Unary} or {@link Binary} can be returned to cooperate with
 * {@link org.codehaus.jparsec.OperatorTable}, {@link Parser#prefix(Parser)},
 *  {@link Parser#postfix(Parser)}, {@link Parser#infixl(Parser)},
 * {@link Parser#infixn(Parser)} or {@link Parser#infixr(Parser)}.
 * 
 * <p> Another useful utility provide by this class, is the {@link #curry(Class, Object[])} method.
 * It allows currying constructor defined in the target class so that no explicit mapping code
 * is needed. For the above example, it can be more concisely written as:
 * <pre>
 * {@code
 * Parser<Foo> fooParser = Mapper.curry(Foo.class)
 *     .sequence(stringParser, integerParser, barParser, bazParser);
 * }
 * </pre>
 * 
 * <p> NOTE: cglib is required on the classpath.
 * 
 * @author Ben Yu
 */
public abstract class Mapper<T> {
  
  private static final ConcurrentHashMap<Class<?>, FastMethod> mapMethods =
      new ConcurrentHashMap<Class<?>, FastMethod>();
  
  final Object source;
  final Invokable invokable;
  
  /** Default constructor that uses the {@code map} method defined in subclass for mapping. */
  protected Mapper() {
    FastMethod method = mapMethod(getClass());
    this.source = method;
    this.invokable = Invokable.method(this, method);
  }
  
  Mapper(Object source, Invokable invokable) {
    this.source = source;
    this.invokable = invokable;
  }
  
  /**
   * A {@link Mapper} that curries the only public constructor defined in {@code clazz}
   * and invokes it with parameters returned by the sequentially executed {@link Parser} objects.
   * For example, to parse an expression with binary operator and create an instance of
   * the following object model:
   * <pre>
   * class BinaryExpression implements Expression {
   *   public BinaryExpression(Expression left, Operator op, Expression right) {...}
   *   ...
   * }
   * </pre>
   * The parser that parses this expression with binary operator can be written as:
   * <pre>
   * {@code 
   * Parser<Expression> binary(Parser<Expression> operand, Parser<Operator> operator) {
   *   return Sequencer.<Expression>curry(BinaryExpression.class)
   *       .sequence(operand, operator, operand);
   * }
   * }
   * </pre>
   * Which is equivalent to the more verbose but reflection-free version:
   * <pre>
   * {@code 
   * Parser<Expression> binary(Parser<Expression> expr, Parser<Operator> op) {
   *   return Parsers.sequence(expr, op, expr,
   *       new Map3<Expression, Operator, Expression, Expression>() {
   *         public Expression map(Expression left, Operator op, Expression right) {
   *           return new BinaryExpression(left, op, right);
   *         }
   *       });
   * }
   * }
   * </pre>
   */
  public static <T> Mapper<T> curry(Class<? extends T> clazz, Object... curryArgs) {
    return Curry.of(clazz, curryArgs);
  }
  
  /**
   * A {@link Parser} that sequentially runs {@code parsers} and invokes the underlying
   * {@code map} method or curried constructor using the returned values.
   */
  public final Parser<T> sequence(Parser<?>... parsers) {
    parsers = toArray(mergeSkipped(parsers));
    int providedParameters = parsers.length;
    int expectedParameters = expectedParams();
    checkArgument(providedParameters == expectedParameters,
        "%s parameters expected for sequencing, %s provided.",
        expectedParameters, providedParameters);
    return Parsers.array(parsers).map(asMap());
  }

  /**
   * A {@link Parser} that returns a {@link Unary} instance that invokes the underlying
   * {@code map} method or curried constructor with the only parameter of the
   * {@link Unary#map(Object)} method.
   */
  public final Parser<Unary<T>> unary() {
    return Parsers.constant(asUnary());
  }

  /**
   * A {@link Parser} that returns a {@link Binary} instance that invokes the underlying
   * {@code map} method or curried constructor with the two parameters of the
   * {@link Binary#map(Object, Object)} method.
   */
  public final Parser<Binary<T>> binary() {
    return Parsers.constant(asBinary());
  }
  
  /**
   * A {@link Parser} that runs {@code operator} and returns a {@link Unary} instance,
   * which will pass along the return value of {@code operator} followed by its only parameter
   * to the underlying {@code map} method or curried constructor.
   * 
   * <p> For example:
   * <pre>
   * {@code 
   * Parser<Unary&lt;Expression>> prefixOperator(Parser<Operator> op) {
   *   return new Mapper<Expression>() {
   *     Expression map(Operator operator, Expression operand) {
   *       return new PrefixExpression(operator, operand);
   *     }
   *   }.prefix(op);
   * }
   * }
   * </pre>
   * <pre>
   * {@code 
   * Or alternatively, by using the {@link #curry(Class, Object[])} method: <pre>
   * Parser<Unary<Expression>> prefixOperator(Parser<Operator> op) {
   *   return Mapper.<Expression>curry(PrefixExpression.class).prefix(op);
   * }
   * }
   * </pre>
   * 
   * <p> Useful when the returned parser is used in {@link Parser#prefix(Parser)} or
   * {@link org.codehaus.jparsec.OperatorTable}.
   */
  public final Parser<Unary<T>> prefix(Parser<?> operator) {
    checkNotSkipped(operator);
    checkFutureParameters(Unary.class, 2);
    return operator.map(new Map<Object, Unary<T>>() {
      @Override public Unary<T> map(final Object pre) {
        return new Unary<T>() {
          @Override public T map(T v) {
            return apply(pre, v);
          }
        };
      }
    });
  }
  
  /**
   * A {@link Parser} that runs {@code operator} sequentially and returns a {@link Unary} instance,
   * which will pass along the return values of {@code operator} followed by its only parameter
   * to the underlying {@code map} method or curried constructor.
   * 
   * <p> Use this version instead of {@link #prefix(Parser)} if the operator is composed of more
   * than one components. For example, the Java label statement (like {@code here:}) can be
   * modeled as a prefix operator applied to statements:
   * <pre>
   * {@code 
   * Parser&lt;Unary&lt;Statement>> label = new Mapper&lt;Statement>() {
   *     Statement map(String label, Statement statement) {
   *         return new LabelStatement(label, statement);
   *     }
   * }.prefix(Terminals.STRING, skip(terminal(":")));
   * }
   * </pre>
   * Or alternatively, by using the {@link #curry(Class, Object[])} method:
   * <pre>
   * {@code
   * Parser<Unary<Statement>> label = Mapper<Statement>curry(LabelStatement.class)
   *  .prefix(Terminals.STRING, skip(terminal(":")));
   * }
   * </pre>
   * 
   * <p> Useful when the returned parser is used in {@link Parser#prefix(Parser)} or
   * {@link org.codehaus.jparsec.OperatorTable}.
   */
  public final Parser<Unary<T>> prefix(Parser<?>... operator) {
    List<Parser<?>> operatorList = mergeSkipped(operator);
    if (operatorList.size() == 1) return prefix(operatorList.get(0));
    checkFutureParameters(Unary.class, operatorList.size() + 1);
    return Parsers.list(operatorList).map(new Map<List<Object>, Unary<T>>() {
      @Override public Unary<T> map(final List<Object> list) {
        return new Unary<T>() {
          @Override public T map(T v) {
            list.add(v);
            return apply(list.toArray());
          }
        };
      }
    });
  }
  
  /**
   * A {@link Parser} that runs {@code operator} and returns a {@link Unary} instance,
   * which will pass along its only parameter followed by the return value of {@code operator}
   * to the underlying {@code map} method or curried constructor.
   * 
   * <p> For example:
   * <pre>
   * {@code 
   * Parser<Binary<Expression>> postfixOperator(Parser<Operator> op) {
   *   return new Mapper<Expression>() {
   *     Expression map(Expression operand, Operator operator) {
   *       return new PostfixExpression(operand, operator);
   *     }
   *   }.postfix(op);
   * }
   * }
   * </pre>
   * Or alternatively, by using the {@link #curry(Class, Object[])} method:
   * <pre>
   * {@code 
   * Parser<Unary<Expression>> postfixOperator(Parser<Operator> op) {
   *   return Mapper.<Expression>curry(PostfixExpression.class).postfix(op);
   * }
   * }
   * </pre>
   * 
   * <p> Useful when the returned parser is used in {@link Parser#postfix(Parser)} or
   * {@link org.codehaus.jparsec.OperatorTable}.
   */
  public final Parser<Unary<T>> postfix(Parser<?> operator) {
    checkNotSkipped(operator);
    checkFutureParameters(Unary.class, 2);
    return operator.map(new Map<Object, Unary<T>>() {
      @Override public Unary<T> map(final Object post) {
        return new Unary<T>() {
          @Override public T map(T v) {
            return apply(v, post);
          }
        };
      }
    });
  }
  
  /**
   * A {@link Parser} that runs {@code operator} sequentially and returns a {@link Unary} instance,
   * which will pass along its only parameter followed by the return values of {@code operator}
   * to the underlying {@code map} method or curried constructor.
   * 
   * <p> Use this version instead of {@link #postfix(Parser)} if the operator is composed of more
   * than one components.
   * For example, in order to parse an array slice syntax such as {@code array[from, to]},
   * where {@code array}, {@code from} and {@code to} are all themselves expressions,
   * the {@code [from, to]} part can be modeled as a postfix operator that turns the
   * {@code array} expression to an array slice expression.
   * The parser can be written as:
   * <pre>
   * {@code 
   * Parser<Unary<Expression>> slice(Parser<Expression> bound) {
   *   return new Mapper<Expression>() {
   *  Expression map(Expression array, Expression from, Expression to) {
   *    return new ArraySliceExpression(array, from, to);
   *  }
   * }.postfix(skip(terminal("[")), bound, skip(terminal(",")), bound, skip(terminal("]")));
   * }
   * }
   * </pre>
   * Or alternatively, by using the {@link #curry(Class, Object[])} method:
   * <pre>
   * {@code 
   * Parser<Unary<Expression>> slice(Parser<Expression bound) {
   *   return Mapper.<Expression>curry(ArraySliceExpression.class)
   *       .postfix(skip(terminal("[")), bound, skip(terminal(",")), bound, skip(terminal("]")));
   * }
   * }
   * </pre>
   * 
   * <p> Useful when the returned parser is used in {@link Parser#postfix(Parser)} or
   * {@link org.codehaus.jparsec.OperatorTable}.
   */
  public final Parser<Unary<T>> postfix(Parser<?>... operator) {
    operator = toArray(mergeSkipped(operator));
    if (operator.length == 1) return postfix(operator[0]);
    checkFutureParameters(Unary.class, operator.length + 1);
    return Parsers.array(operator).map(new Map<Object[], Unary<T>>() {
      @Override public Unary<T> map(final Object[] array) {
        return new Unary<T>() {
          @Override public T map(T v) {
            Object[] args = new Object[array.length + 1];
            args[0] = v;
            System.arraycopy(array, 0, args, 1, array.length);
            return apply(args);
          }
        };
      }
    });
  }
  
  /**
   * A {@link Parser} that runs {@code operator} and returns a {@link Binary} instance,
   * which will pass along its first parameter, followed by the return value of {@code operator},
   * followed by its second parameter to the underlying {@code map} method or curried constructor.
   * 
   * <p> For example:
   * <pre>
   * {@code 
   * Parser<Binary<Expression>> infixOperator(Parser<Operator> op) {
   *   return new Mapper<Expression>() {
   *     Expression map(Expression left, Operator operator, Expression right) {
   *       return new InfixExpression(left, operand, right);
   *     }
   *   }.infix(op);
   * }
   * }
   * </pre>
   * Or alternatively, by using the {@link #curry(Class, Object[])} method:
   * <pre>
   * {@code 
   * Parser<Binary<Expression>> infixOperator(Parser<Operator> op) {
   *   return Mapper.<Expression>curry(InfixExpression.class).infix(op);
   * }
   * }
   * </pre>
   * 
   * <p> Useful when the returned parser is used in {@link Parser#infixl(Parser)},
   * {@link Parser#infixn(Parser)}, {@link Parser#infixr(Parser)}
   * or {@link org.codehaus.jparsec.OperatorTable}.
   */
  public final Parser<Binary<T>> infix(Parser<?> operator) {
    checkNotSkipped(operator);
    checkFutureParameters(Binary.class, 3);
    return operator.map(new Map<Object, Binary<T>>() {
      @Override public Binary<T> map(final Object op) {
        return new Binary<T>() {
          @Override public T map(T left, T right) {
            return apply(left, op, right);
          }
        };
      }
    });
  }
  
  /**
   * A {@link Parser} that runs {@code operator} sequentially and returns a {@link Binary} instance,
   * which will pass along its first parameter, followed by the return values of {@code operator},
   * followed by its second parameter to the underlying {@code map} method or curried constructor.
   * 
   * <p> Use this version instead of {@link #infix(Parser)} if the operator is composed of more
   * than one components. 
   * For example, in order to parse the Java ternary {@code ?:} operator, we can model the
   * {@code ? consequence :} part as a right associative infix operator that binds the condition and
   * the alternative expression together as a composite expression. The parser can be written as:
   * <pre>
   * {@code 
   * Parser<Binary<Expression>> conditional(Parser<Expression> expr) {
   *   return new Mapper<Expression>() {
   *     Expression map(Expression condition, Expression consequence, Expression alternative) {
   *       return new ConditionalExpression(condition, consequence, alternative);
   *     }
   *   }.postfix(skip(terminal("?")), expr, skip(terminal(":")));
   * }
   * }
   * </pre>
   * Or alternatively, by using the {@link #curry(Class, Object[])} method:
   * <pre>
   * {@code 
   * Parser<Binary<Expression>> conditional(Parser<Expression> expr) {
   *   return Mapper.<Expression>.curry(ConditionalExpression.class)
   *     .postfix(skip(terminal("?")), expr, skip(terminal(":")));
   * }
   * }
   * </pre>
   * 
   * <p> Useful when the returned parser is used in {@link Parser#infixl(Parser)},
   * {@link Parser#infixn(Parser)}, {@link Parser#infixr(Parser)}
   * or {@link org.codehaus.jparsec.OperatorTable}.
   */
  public final Parser<Binary<T>> infix(Parser<?>... operator) {
    operator = toArray(mergeSkipped(operator));
    if (operator.length == 1) return infix(operator[0]);
    checkFutureParameters(Binary.class, operator.length + 2);
    return Parsers.array(operator).map(new Map<Object[], Binary<T>>() {
      @Override public Binary<T> map(final Object[] array) {
        return new Binary<T>() {
          @Override public T map(T left, T right) {
            Object[] args = new Object[array.length + 2];
            args[0] = left;
            System.arraycopy(array, 0, args, 1, array.length);
            args[args.length - 1] = right;
            return apply(args);
          }
        };
      }
    });
  }
  
  /**
   * Wraps {@code parser} so that it will be skipped when applied in {@link #sequence(Parser[])},
   * {@link #prefix(Parser[])} or {@link #postfix(Parser[])}. For example, the following code
   * maps the two expressions after "if" and "else" to the constructor of {@code IfElseExpression}
   * and skips the return values of the keyword "if" and "else".
   * <pre>
   * {@code
   * Parser<IfElseExpression> expression = curry(IfElseExpression.class).sequence(
   *  skip(word("if")), expr, skip(word("else")), expr);
   * }
   * </pre>
   */
  public static final Parser<?> skip(Parser<?> parser) {
    return parser.map(SKIP);
  }
  
  /** 
   * @return the string representation of this object. 
   */
  @Override public String toString() {
    return source.toString();
  }

  void checkFutureParameters(Class<?> targetType, int providedParameters) {
    checkFutureParameters(expectedParams(), targetType, providedParameters);
  }

  final void checkFutureParameters(
      int expectedParameters, Class<?> targetType, int providedParameters) {
    checkArgument(providedParameters == expectedParameters,
        "Invalid curry: %s parameters expected by %s," +
        " %s will be provided by curried and explicit parameters of %s",
        expectedParameters, invokable, providedParameters, targetType.getName());
  }
  
  int expectedParams() {
    return invokable.parameterTypes().length;
  }
  
  final String name() {
    return invokable.returnType().getName();
  }

  /**
   * Returns a {@link Unary} instance that invokes the underlying {@code map} method or
   * curried constructor with the only parameter of the {@link Unary#map(Object)} method.
   */
  final Unary<T> asUnary() {
    checkFutureParameters(Unary.class, 1);
    return new Unary<T>() {
      @Override public T map(T v) {
        return apply(v);
      }
      @Override public String toString() {
        return name();
      }
    };
  }

  /**
   * Returns a {@link Binary} instance that invokes the underlying {@code map} method or
   * curried constructor with the two parameters of the {@link Binary#map(Object, Object)} method. 
   */
  final Binary<T> asBinary() {
    checkFutureParameters(Binary.class, 2);
    return new Binary<T>() {
      @Override public T map(T left, T right) {
        return apply(left, right);
      }
      @Override public String toString() {
        return name();
      }
    };
  }

  /**
   * Returns a {@link Map} instance that invokes the underlying {@code map} method or
   * curried constructor with the only parameter of the {@link Unary#map(Object)} method.
   */
  final Map<Object[], T> asMap() {
    return new Map<Object[], T>() {
      @Override public T map(Object[] args) {
        return apply(args);
      }
      @Override public String toString() {
        return name();
      }
    };
  }
  
  @SuppressWarnings("unchecked")
  final T apply(Object... args) {
    try {
      return (T) invoke(args);
    } catch (Throwable e) {
      throw propagate(e);
    }
  }
  
  static RuntimeException propagate(Throwable e) {
    if (e instanceof InvocationTargetException) {
      return propagate(((InvocationTargetException) e).getCause());
    }
    if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    }
    if (e instanceof Error) {
      throw (Error) e;
    }
    return new RuntimeException(e);
  }

  Object invoke(Object[] args) throws Throwable {
    checkArgumentTypes(args);
    return invokable.invoke(args);
  }
  
  private static FastMethod mapMethod(Class<?> type) {
    FastMethod method = mapMethods.get(type);
    if (method == null) {
      method = introspectMapperMethod(type);
      mapMethods.put(type, method);
    }
    return method;
  }
  
  private static FastMethod introspectMapperMethod(Class<?> type) {
    Method method = findMapMethod(type);
    checkNotNullState(method,
        "A method named as 'map' should be defined in %s", type.getName());
    Class<?> targetType = getTargetType(type);
    if (targetType != null) {
      checkState(
          targetType.isAssignableFrom(Reflection.wrapperClass(method.getReturnType())),
          "%s should return a subtype of %s", method, targetType.getName());
    }
    return FastClass.create(type).getMethod(method);
  }
  
  private static Class<?> getTargetType(Class<?> type) {
    Type genericSuperclass = type.getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
      if (parameterizedType.getRawType() == Mapper.class) {
        return getRawClass(parameterizedType.getActualTypeArguments()[0]);
      }
    }
    Class<?> superclass = type.getSuperclass();
    return (superclass == null) ? Object.class : getTargetType(superclass);
  }
  
  private static Class<?> getRawClass(Type type) {
    if (type instanceof Class<?>) {
      return (Class<?>) type;
    }
    if (type instanceof ParameterizedType) {
      return getRawClass(((ParameterizedType) type).getRawType());
    }
    return null;
  }

  private static Method findMapMethod(Class<?> type) {
    Method mapMethod = null;
    for (Method method : type.getDeclaredMethods()) {
      if (method.getName().equals("map")) {
        checkState(mapMethod == null,
            "only one map method can be defined: %s", type.getName());
        mapMethod = method;
      }
    }
    if (mapMethod != null) {
      return mapMethod;
    }
    Class<?> superclass = type.getSuperclass();
    return (superclass == null) ? null : findMapMethod(superclass);
  }

  private void checkArgumentTypes(Object... vals) {
    Class<?>[] parameterTypes = invokable.parameterTypes();
    if (vals.length != parameterTypes.length) {
      throw new IllegalArgumentException(vals.length + " arguments received, "
          + parameterTypes.length + " expected: " + invokable);
    }
    for (int i = 0; i < vals.length; i++) {
      checkArgumentType(i, parameterTypes[i], vals[i]);
    }
  }

  final void checkArgumentType(int i, Class<?> parameterType, Object arg) {
    if (!Reflection.isAssignable(parameterType, arg)) {
      throw new IllegalArgumentException(
          parameterType.getName() + " expected for parameter " + i
          + " of " + invokable
          + ", " + Reflection.getClassName(arg) + " provided.");
    }
  }
  
  // Use new to ensure uniqueness of the string.
  private static final String SKIPPED = new String("skipped");
  
  private static final Unary<Object> SKIP = new Unary<Object>() {
    @Override public Object map(Object v) {
      return v;
    }
    @Override public String toString() {
      return SKIPPED;
    }
  };
  
  private static boolean isSkipped(Parser<?> parser) {
    return parser.toString() == SKIPPED;
  }

  static Parser<?>[] toArray(Collection<? extends Parser<?>> parsers) {
    return parsers.toArray(new Parser<?>[parsers.size()]);
  }

  private static void checkNotSkipped(Parser<?> operator) {
    checkArgument(!isSkipped(operator), "Cannot skip the only parser parameter.");
  }
  
  private static List<Parser<?>> mergeSkipped(Parser<?>... parsers) {
    ArrayList<Parser<?>> result = Lists.arrayList(parsers.length);
    List<Parser<?>> all = Arrays.asList(parsers);
    for (int i = 0; i < parsers.length; i++) {
      Parser<?> parser = parsers[i];
      if (isSkipped(parser)) {
        // scan forward until a non-skipped parser is found or the end of the array.
        int from = i;
        for (i++; i < parsers.length && isSkipped(parsers[i]); i++) {}
        if (i == parsers.length) {
          // we are at the end of the array
          checkArgument(!result.isEmpty(), "Cannot skip all parser parameters.");
          Parser<?> skippedSequence = Parsers.sequence(all.subList(from, i));
          int lastIndex = result.size() - 1;
          result.set(lastIndex, result.get(lastIndex).followedBy(skippedSequence));
          return result;
        }
        // parsers[i] is not skipped.
        result.add(Parsers.sequence(all.subList(from, i + 1)));
      }
      else {
        result.add(parser);
      }
    }
    return result;
  }
}
