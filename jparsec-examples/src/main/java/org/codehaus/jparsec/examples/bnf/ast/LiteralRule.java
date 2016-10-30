package org.codehaus.jparsec.examples.bnf.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a literal rule.
 * 
 * @author benyu
 */
public final class LiteralRule extends ValueObject implements Rule {
  public final String literal;

  public LiteralRule(String literal) {
    this.literal = literal;
  }
  
  
  @Override public String toString() {
    return "'" + literal + "'";
  }
}
