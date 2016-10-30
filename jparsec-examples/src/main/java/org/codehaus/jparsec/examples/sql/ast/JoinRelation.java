package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models a join.
 * 
 * @author Ben Yu
 */
public final class JoinRelation extends ValueObject implements Relation {
  public final Relation left;
  public final Relation right;
  public final JoinType joinType;
  public final Expression condition;
  
  public JoinRelation(Relation left, JoinType joinType, Relation right, Expression condition) {
    this.left = left;
    this.right = right;
    this.joinType = joinType;
    this.condition = condition;
  }
}
