package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * "true" or "false".
 * 
 * @author Ben Yu
 */
public final class BooleanLiteral extends ValueObject implements Expression {
  public final boolean value;

  public BooleanLiteral(boolean value) {
    this.value = value;
  }
  
  @Override public String toString() {
    return Boolean.toString(value);
  }
}
