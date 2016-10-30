package org.codehaus.jparsec.examples.bnf.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a sequential rule.
 * 
 * @author benyu
 */
public final class SequentialRule extends ValueObject implements Rule {
  public final List<Rule> rules;
  
  public SequentialRule(List<Rule> rules) {
    this.rules = Collections.unmodifiableList(rules);
  }
  
  @Override public String toString() {
    return Strings.join(" ", rules);
  }
}
