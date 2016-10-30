package org.codehaus.jparsec.examples.java.ast.expression;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents expressions like {@code obj.f(...)}.
 * 
 * @author Ben Yu
 */
public final class MethodCallExpression extends ValueObject implements Expression {
  public final Expression target;
  public final List<TypeLiteral> typeParameters;
  public final String method;
  public final List<Expression> arguments;
  
  public MethodCallExpression(
      Expression target, List<TypeLiteral> typeParameters,
      String method, List<Expression> arguments) {
    this.target = target;
    this.typeParameters = Collections.unmodifiableList(typeParameters);
    this.method = method;
    this.arguments = Collections.unmodifiableList(arguments);
  }
  
  @Override public String toString() {
    return (target == null ? "" : target + ".")
        + (typeParameters.isEmpty() ? "" : "<" + Strings.join(", ", typeParameters) + ">")
        + method + "(" + Strings.join(", ", arguments) + ")";
  }
}
