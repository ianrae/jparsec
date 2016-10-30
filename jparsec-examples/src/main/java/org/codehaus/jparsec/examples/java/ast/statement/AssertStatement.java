package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents an "assert" statement.
 * 
 * @author Ben Yu
 */
public final class AssertStatement extends ValueObject implements Statement {
  public final Expression condition;
  public final Expression message;
  
  public AssertStatement(Expression condition, Expression message) {
    this.condition = condition;
    this.message = message;
  }
  
  @Override public String toString() {
    return "assert " + condition + (message == null ? ";" : " : " + message + ";");
  }
}
