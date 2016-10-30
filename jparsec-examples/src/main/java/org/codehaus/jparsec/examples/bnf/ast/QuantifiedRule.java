package org.codehaus.jparsec.examples.bnf.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a quantified rule.
 * 
 * @author benyu
 */
public final class QuantifiedRule extends ValueObject implements Rule {
  public final Rule rule;
  public final Quantifier quantifier;
  
  public QuantifiedRule(Rule rule, Quantifier quantifier) {
    this.rule = rule;
    this.quantifier = quantifier;
  }
  
  @Override public String toString() {
    return "(" + rule + ")" + quantifier;
  }
}
