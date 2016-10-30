package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models an expression with unary operator.
 * 
 * @author Ben Yu
 */
public final class UnaryExpression extends ValueObject implements Expression {
  public final Expression operand;
  public final Op operator;
  
  public UnaryExpression(Op operator, Expression operand) {
    this.operand = operand;
    this.operator = operator;
  }
}
