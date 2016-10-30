package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents expression with postfix unary operator.
 * 
 * @author Ben Yu
 */
public final class PostfixUnaryExpression extends ValueObject implements Expression {
  public final Expression expression;
  public final Operator op;
  
  public PostfixUnaryExpression(Expression expression, Operator op) {
    this.expression = expression;
    this.op = op;
  }
  
  @Override public String toString() {
    return "(" + expression + op + ")";
  }
}
