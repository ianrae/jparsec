package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.functors.Pair;

/**
 * Represents the "switch case" expression.
 * 
 * @author Ben Yu
 */
public final class SwitchStatement extends ValueObject implements Statement {
  public final Expression condition;
  public final List<Pair<Expression, Statement>> cases;
  public final Statement defaultCase;
  
  public SwitchStatement(
      Expression condition, List<Pair<Expression, Statement>> cases, Statement defaultCase) {
    this.condition = condition;
    this.cases = cases;
    this.defaultCase = defaultCase;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("switch (").append(condition).append(") {");
    for (Pair<Expression, Statement> c : cases) {
      builder.append(" case ").append(c.a).append(":");
      if (c.b != null) {
        builder.append(" ").append(c.b);
      }
    }
    if (defaultCase != null) {
      builder.append(" default: ").append(defaultCase);
    }
    builder.append("}");
    return builder.toString();
  }
}
