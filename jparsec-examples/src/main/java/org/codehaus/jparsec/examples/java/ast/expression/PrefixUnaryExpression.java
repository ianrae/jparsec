package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents expression with prefix unary operator.
 * 
 * @author Ben Yu
 */
public final class PrefixUnaryExpression extends ValueObject implements Expression {
  public final Operator op;
  public final Expression expression;
  
  public PrefixUnaryExpression(Operator op, Expression expression) {
    this.op = op;
    this.expression = expression;
  }
  
  @Override public String toString() {
    return "(" + op + expression + ")";
  }
}
