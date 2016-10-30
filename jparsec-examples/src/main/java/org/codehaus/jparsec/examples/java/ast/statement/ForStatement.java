package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents the traditional for loop.
 * 
 * @author Ben Yu
 */
public final class ForStatement extends ValueObject implements Statement {
  public final Statement initializer;
  public final Expression condition;
  public final List<Expression> incrementer;
  public final Statement statement;
  
  public ForStatement(
      Statement initializer, Expression condition, List<Expression> incrementer,
      Statement statement) {
    this.initializer = initializer;
    this.condition = condition;
    this.incrementer = incrementer;
    this.statement = statement;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("for (");
    builder.append(initializer);
    if (condition != null) {
      builder.append(condition);
    }
    builder.append(";");
    Strings.join(builder, ", ", incrementer);
    builder.append(") ").append(statement);
    return builder.toString();
  }
}
