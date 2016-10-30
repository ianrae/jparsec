package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * An aliased relation.
 * 
 * @author Ben Yu
 */
public final class AliasedRelation extends ValueObject implements Relation {
  public final Relation relation;
  public final String alias;
  
  public AliasedRelation(Relation relation, String alias) {
    this.relation = relation;
    this.alias = alias;
  }
}
