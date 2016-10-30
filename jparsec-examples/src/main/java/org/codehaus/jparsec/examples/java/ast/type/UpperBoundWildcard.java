package org.codehaus.jparsec.examples.java.ast.type;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "? extends SomeBound" type literal.
 * 
 * @author Ben Yu
 */
public final class UpperBoundWildcard extends ValueObject implements TypeLiteral {
  public final TypeLiteral bound;

  public UpperBoundWildcard(TypeLiteral bound) {
    this.bound = bound;
  }
  
  @Override public String toString() {
    return bound == null ? "?" : "? extends " + bound;
  }
}
