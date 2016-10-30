package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents any integral number literal.
 * 
 * @author Ben Yu
 */
public final class IntegerLiteral extends ValueObject implements Expression {
  
  public enum Radix {
    OCT("0"), DEC(""), HEX("0X");
    
    private final String name;
    
    private Radix(String name) {
      this.name = name;
    }
    
    @Override public String toString() {
      return name;
    }
  }
  
  public final Radix radix;
  public final String number;
  public final NumberType type;
  
  public IntegerLiteral(Radix radix, String number, NumberType type) {
    this.radix = radix;
    this.number = number;
    this.type = type;
  }
  
  @Override public String toString() {
    return radix + number + type;
  }
}
