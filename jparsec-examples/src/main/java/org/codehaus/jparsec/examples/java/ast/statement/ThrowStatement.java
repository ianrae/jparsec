package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents a "throw" statement.
 * 
 * @author benyu
 */
public final class ThrowStatement extends ValueObject implements Statement {
  public final Expression thrown;

  public ThrowStatement(Expression thrown) {
    this.thrown = thrown;
  }
  
  @Override public String toString() {
    return "throw " + thrown + ";";
  }
}
