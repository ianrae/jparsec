package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents "return" statement.
 * 
 * @author Ben Yu
 */
public final class ReturnStatement extends ValueObject implements Statement {
  public final Expression value;

  public ReturnStatement(Expression value) {
    this.value = value;
  }
  
  @Override public String toString() {
    return value == null ? "return;" : "return " + value + ";";
  }
}
