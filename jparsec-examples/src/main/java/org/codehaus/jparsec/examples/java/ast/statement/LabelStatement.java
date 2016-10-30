package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a label statement such as "foo:".
 * 
 * @author Ben Yu
 */
public final class LabelStatement extends ValueObject implements Statement {
  public final String name;
  public final Statement statement;

  public LabelStatement(String name, Statement statement) {
    this.name = name;
    this.statement = statement;
  }
  
  @Override public String toString() {
    return name + ": " + statement;
  }
}
