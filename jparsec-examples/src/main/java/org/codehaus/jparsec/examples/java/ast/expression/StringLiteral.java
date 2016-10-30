package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a string literal.
 * 
 * @author Ben Yu
 */
public final class StringLiteral extends ValueObject implements Expression {
  public final String string;

  public StringLiteral(String string) {
    this.string = string;
  }
  
  @Override public String toString() {
    return string;
  }
}
