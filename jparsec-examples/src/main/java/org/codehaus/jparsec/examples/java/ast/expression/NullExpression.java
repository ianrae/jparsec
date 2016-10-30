package org.codehaus.jparsec.examples.java.ast.expression;

/**
 * Represents "null".
 * 
 * @author Ben Yu
 */
public final class NullExpression implements Expression {
  private NullExpression() {}
  
  @Override public String toString() {
    return "null";
  }
  
  public static final Expression instance = new NullExpression();
}
