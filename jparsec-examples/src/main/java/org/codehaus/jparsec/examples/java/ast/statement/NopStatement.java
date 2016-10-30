package org.codehaus.jparsec.examples.java.ast.statement;

/**
 * Represents the no-op statement ";".
 * 
 * @author Ben Yu
 */
public final class NopStatement implements Statement {
  private NopStatement() {}
  
  @Override public String toString() {
    return ";";
  }
  
  public static final Statement instance = new NopStatement();
}
