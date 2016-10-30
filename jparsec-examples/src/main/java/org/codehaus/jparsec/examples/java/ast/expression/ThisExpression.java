package org.codehaus.jparsec.examples.java.ast.expression;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * "this" or "A.B.this".
 * 
 * @author Ben Yu
 */
public final class ThisExpression extends ValueObject implements Expression {
  public final List<String> qualifiers;

  public ThisExpression(List<String> qualifiers) {
    this.qualifiers = qualifiers;
  }
  
  @Override public String toString() {
    return qualifiers.isEmpty() ? "this"
        : Strings.join(".", qualifiers) + ".this";
  }
}
