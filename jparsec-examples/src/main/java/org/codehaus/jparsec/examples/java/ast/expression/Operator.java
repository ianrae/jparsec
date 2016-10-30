package org.codehaus.jparsec.examples.java.ast.expression;

/**
 * Defines operators used in Java.
 * 
 * @author Ben Yu
 */
public enum Operator {
  POST_INC("++"), POST_DEC("--"),
  INC("++"), DEC("--"), POSITIVE("+"), NEGATIVE("-"), NOT("!"), BITWISE_NOT("~"),
  MUL("*"), DIV("/"), MOD("%"),
  PLUS("+"), MINUS("-"),
  LSHIFT("<<"), RSHIFT(">>"), UNSIGNED_RSHIFT(">>>"),
  LT("<"), GT(">"), LE("<="), GE(">="), // instanceof has same precedence
  EQ("=="), NE("!="),
  BITWISE_AND("&"),
  BITWISE_XOR("|"),
  BITWISE_OR("^"),
  AND("&&"),
  OR("||"),
  // ?: falls here
  
  // The following have same precedence
  ASSIGNMENT("="), APLUS("+="), AMINUS("-="), AMUL("*="), ADIV("/="), AMOD("%="),
  AAND("&="), AOR("|="), AXOR("^="), ALSHIFT("<<="), ARSHIFT(">>="), UNSIGNED_ARSHIFT(">>>=");
  
  private final String name;
  
  private Operator(final String name) {
    this.name = name;
  }
  
  @Override public String toString() {
    return name;
  }
}
