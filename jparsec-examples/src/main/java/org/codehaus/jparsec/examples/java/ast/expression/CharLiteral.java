package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a character literal.
 * 
 * @author Ben Yu
 */
public final class CharLiteral extends ValueObject implements Expression {
  public final char character;

  public CharLiteral(char character) {
    this.character = character;
  }
  
  @Override public String toString() {
    return Character.toString(character);
  }
}
