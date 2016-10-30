package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "continue" statement.
 * 
 * @author Ben Yu
 */
public final class ContinueStatement extends ValueObject implements Statement {
  public final String label;

  public ContinueStatement(String label) {
    this.label = label;
  }
  
  @Override public String toString() {
    return label == null ? "continue;" : "continue " + label + ";";
  }
}
