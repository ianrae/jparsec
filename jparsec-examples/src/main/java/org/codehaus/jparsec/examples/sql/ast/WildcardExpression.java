package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * "a.b.*" or "*".
 * 
 * @author Ben Yu
 */
public final class WildcardExpression extends ValueObject implements Expression {
  public final QualifiedName owner;

  public WildcardExpression(QualifiedName owner) {
    this.owner = owner;
  }
}
