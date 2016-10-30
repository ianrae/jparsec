package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models an expression like "exists (select ...)".
 * 
 * @author Ben Yu
 */
public final class UnaryRelationalExpression extends ValueObject implements Expression {
  public final Relation relation;
  public final Op operator;

  public UnaryRelationalExpression(Relation relation, Op operator) {
    this.relation = relation;
    this.operator = operator;
  }
}
