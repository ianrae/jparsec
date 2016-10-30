package org.codehaus.jparsec.examples.bnf.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a rule with a bunch of alternatives.
 * 
 * @author benyu
 */
public final class AltRule extends ValueObject implements Rule {
  public final List<Rule> alternatives;
  
  public AltRule(List<Rule> alternatives) {
    this.alternatives = Collections.unmodifiableList(alternatives);
  }
  
  @Override public String toString() {
    return "(" + Strings.join(" | ", alternatives) + ")";
  }
}
