package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * An expression like "expr in (select ...)".
 * 
 * @author Ben Yu
 */
public final class BinaryRelationalExpression extends ValueObject implements Expression {
  public final Expression expression;
  public final Op operator;
  public final Relation relation;
  
  public BinaryRelationalExpression(Expression expression, Op operator, Relation relation) {
    this.expression = expression;
    this.operator = operator;
    this.relation = relation;
  }
}
