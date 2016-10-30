package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models cross join.
 * 
 * @author Ben Yu
 */
public final class CrossJoinRelation extends ValueObject implements Relation {
  public final Relation left;
  public final Relation right;
  
  public CrossJoinRelation(Relation left, Relation right) {
    this.left = left;
    this.right = right;
  }
}
