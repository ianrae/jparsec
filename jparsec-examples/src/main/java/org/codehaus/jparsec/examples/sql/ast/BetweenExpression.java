package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Expression like "expr between a and b".
 * 
 * @author Ben Yu
 */
public final class BetweenExpression extends ValueObject implements Expression {
  public final Expression expression;
  public final boolean between; // between or not between
  public final Expression from;
  public final Expression to;
  
  public BetweenExpression(Expression expression, boolean between, Expression from, Expression to) {
    this.expression = expression;
    this.between = between;
    this.from = from;
    this.to = to;
  }
}
