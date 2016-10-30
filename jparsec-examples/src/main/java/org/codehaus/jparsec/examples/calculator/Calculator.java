package org.codehaus.jparsec.examples.calculator;

import static org.codehaus.jparsec.Scanners.isChar;

import org.codehaus.jparsec.OperatorTable;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.functors.Binary;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Unary;

/**
 * The main calculator parser.
 * 
 * @author Ben Yu
 */
public final class Calculator {
  
  /** Parsers {@code source} and evaluates to an {@link Integer}. */
  public static int evaluate(String source) {
    return parser().parse(source);
  }
  
  static final Parser<Integer> NUMBER = Scanners.INTEGER.map(new Map<String, Integer>() {
    @Override public Integer map(String text) {
      return Integer.valueOf(text);
    }
  });
  
  static final Binary<Integer> PLUS = new Binary<Integer>() {
    @Override public Integer map(Integer a, Integer b) {
      return a + b;
    }
  };
  
  static final Binary<Integer> MINUS = new Binary<Integer>() {
    @Override public Integer map(Integer a, Integer b) {
      return a - b;
    }
  };
  
  static final Binary<Integer> MUL = new Binary<Integer>() {
    @Override public Integer map(Integer a, Integer b) {
      return a * b;
    }
  };
  
  static final Binary<Integer> DIV = new Binary<Integer>() {
    @Override public Integer map(Integer a, Integer b) {
      return a / b;
    }
  };
  
  static final Binary<Integer> MOD = new Binary<Integer>() {
    @Override public Integer map(Integer a, Integer b) {
      return a % b;
    }
  };
  
  static final Unary<Integer> NEG = new Unary<Integer>() {
    @Override public Integer map(Integer i) {
      return -i;
    }
  };
  
  private static <T> Parser<T> op(char ch, T value) {
    return isChar(ch).retn(value);
  }
  
  static Parser<Integer> parser() {
    Parser.Reference<Integer> ref = Parser.newReference();
    Parser<Integer> term = ref.lazy().between(isChar('('), isChar(')')).or(NUMBER);
    Parser<Integer> parser = new OperatorTable<Integer>()
        .prefix(op('-', NEG), 100)
        .infixl(op('+', PLUS), 10)
        .infixl(op('-', MINUS), 10)
        .infixl(op('*', MUL), 20)
        .infixl(op('/', DIV), 20)
        .infixl(op('%', MOD), 20)
        .build(term);
    ref.set(parser);
    return parser;
  }
}
