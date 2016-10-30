package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents scientific notation number.
 * 
 * @author Ben Yu
 */
public final class ScientificNumberLiteral extends ValueObject implements Expression {
  public final String number;
  public final NumberType type;

  public ScientificNumberLiteral(String notation, NumberType type) {
    this.number = notation;
    this.type = type;
  }
  
  @Override public String toString() {
    return number + (type == NumberType.DOUBLE ? "" : type.toString());
  }
}
