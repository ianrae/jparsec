package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "synchronized" block.
 * 
 * @author Ben Yu
 */
public class SynchronizedBlockStatement extends ValueObject implements Statement {
  public final Statement block;

  public SynchronizedBlockStatement(Statement block) {
    this.block = block;
  }
  
  @Override public String toString() {
    return "synchronized " + block;
  }
}
