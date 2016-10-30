package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents the "super" keyword.
 * 
 * @author Ben Yu
 */
public final class SuperExpression extends ValueObject implements Expression {
  @Override public String toString() {
    return "super";
  }
}
