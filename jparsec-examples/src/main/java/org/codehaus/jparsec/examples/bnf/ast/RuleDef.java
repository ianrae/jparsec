package org.codehaus.jparsec.examples.bnf.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a rule definition.
 * 
 * @author benyu
 */
public final class RuleDef extends ValueObject {
  
  public final String name;
  public final Rule definition;
  
  public RuleDef(String name, Rule definition) {
    this.name = name;
    this.definition = definition;
  }
  
  @Override public String toString() {
    return name + " ::= " + definition;
  }
}
