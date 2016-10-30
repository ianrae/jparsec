package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * A number expression.
 * 
 * @author Ben Yu
 */
public final class NumberExpression extends ValueObject implements Expression {
  public final String number;

  public NumberExpression(String number) {
    this.number = number;
  }
}
