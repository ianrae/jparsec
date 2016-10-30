package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents a method call statement.
 * 
 * @author Ben Yu
 */
public final class ExpressionStatement extends ValueObject implements Statement {
  public final Expression expression;

  public ExpressionStatement(Expression expression) {
    this.expression = expression;
  }
  
  @Override public String toString() {
    return expression + ";";
  }
}
