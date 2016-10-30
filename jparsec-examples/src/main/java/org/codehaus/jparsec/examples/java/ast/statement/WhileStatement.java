package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents the "while () ..." statement.
 * 
 * @author Ben Yu
 */
public final class WhileStatement extends ValueObject implements Statement {
  public final Expression condition;
  public final Statement statement;
  
  public WhileStatement(Expression condition, Statement statement) {
    this.condition = condition;
    this.statement = statement;
  }
  
  @Override public String toString() {
    return "while (" + condition + ") " + statement;
  }
}
