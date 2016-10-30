package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "?:" expression.
 * 
 * @author Ben Yu
 */
public final class ConditionalExpression extends ValueObject implements Expression {
  public final Expression condition;
  public final Expression consequence;
  public final Expression alternative;
  
  public ConditionalExpression(Expression condition, Expression consequence, Expression alternative) {
    this.condition = condition;
    this.consequence = consequence;
    this.alternative = alternative;
  }
  
  @Override public String toString() {
    return "(" + condition + " ? " + consequence + " : " + alternative + ")";
  }
}
