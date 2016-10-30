package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a simple name as expression.
 * 
 * @author Ben Yu
 */
public final class Identifier extends ValueObject implements Expression {
  public final String name;

  public Identifier(String name) {
    this.name = name;
  }
  
  @Override public String toString() {
    return name;
  }
}
