package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a single projection in a select statement, it can be either an expression, a wildcard
 * or an expression with an alias.
 * 
 * @author Ben Yu
 */
public final class Projection extends ValueObject {
  public final Expression expression;
  public final String alias;
  
  public Projection(Expression expression, String alias) {
    this.expression = expression;
    this.alias = alias;
  }
}
