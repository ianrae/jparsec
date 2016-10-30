package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * A string literal.
 * 
 * @author Ben Yu
 */
public final class StringExpression extends ValueObject implements Expression {
  public final String string;

  public StringExpression(String string) {
    this.string = string;
  }
}
