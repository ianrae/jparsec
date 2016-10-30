package org.codehaus.jparsec.examples.java.ast.type;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "? super SomeBound" type literal.
 * 
 * @author Ben Yu
 */
public final class LowerBoundWildcard extends ValueObject implements TypeLiteral {
  public final TypeLiteral bound;

  public LowerBoundWildcard(TypeLiteral bound) {
    this.bound = bound;
  }
  
  @Override public String toString() {
    return "? super " + bound;
  }
}
