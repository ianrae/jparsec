package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents binary expression such as "a + b".
 * 
 * @author Ben Yu
 */
public final class BinaryExpression extends ValueObject implements Expression {
  public final Expression left;
  public final Operator op;
  public final Expression right;
  
  public BinaryExpression(Expression left, Operator op, Expression right) {
    this.left = left;
    this.op = op;
    this.right = right;
  }
  
  @Override public String toString() {
    return "(" + left + " " + op + " " + right + ")";
  }
}
