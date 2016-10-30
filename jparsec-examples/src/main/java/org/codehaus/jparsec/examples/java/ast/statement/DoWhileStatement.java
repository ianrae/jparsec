package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents the "do ... while ()" statement.
 * 
 * @author Ben Yu
 */
public final class DoWhileStatement extends ValueObject implements Statement {
  public final Statement statement;
  public final Expression condition;
  
  public DoWhileStatement(Statement statement, Expression condition) {
    this.statement = statement;
    this.condition = condition;
  }
  
  @Override public String toString() {
    return "do " + statement + " while (" + condition + ");";
  }
}
