package org.codehaus.jparsec.examples.bnf.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a reference to a named production rule.
 * 
 * @author benyu
 */
public final class RuleReference extends ValueObject implements Rule {
  public final String name;

  public RuleReference(String name) {
    this.name = name;
  }
  
  @Override public String toString() {
    return name;
  }
}
