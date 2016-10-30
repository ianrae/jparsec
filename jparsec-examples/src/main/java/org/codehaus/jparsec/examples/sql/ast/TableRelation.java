package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models a table name.
 * 
 * @author Ben Yu
 */
public final class TableRelation extends ValueObject implements Relation {
  public final QualifiedName tableName;

  public TableRelation(QualifiedName tableName) {
    this.tableName = tableName;
  }
}
