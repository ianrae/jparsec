package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "break" statement.
 * 
 * @author Ben Yu
 */
public final class BreakStatement extends ValueObject implements Statement {
  public final String label;

  public BreakStatement(String label) {
    this.label = label;
  }
  
  @Override public String toString() {
    return label == null ? "break;" : "break " + label + ";";
  }
}
