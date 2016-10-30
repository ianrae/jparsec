package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents a decimal point number.
 * 
 * @author Ben Yu
 */
public final class DecimalPointNumberLiteral extends ValueObject implements Expression {
  public final String number;
  public final NumberType type;
  
  public DecimalPointNumberLiteral(String number, NumberType type) {
    this.number = number;
    this.type = type;
  }
  
  @Override public String toString() {
    return number + (type == NumberType.DOUBLE ? "" : type);
  }
}
