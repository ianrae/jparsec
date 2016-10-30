package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models a union relation.
 *  
 * @author Ben Yu
 */
public final class UnionRelation extends ValueObject implements Relation {
  public final Relation left;
  public final boolean all; // true if union all
  public final Relation right;
  
  public UnionRelation(Relation left, boolean all, Relation right) {
    this.left = left;
    this.all = all;
    this.right = right;
  }
}
