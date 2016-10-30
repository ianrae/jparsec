package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.functors.Pair;

/**
 * Represents an "if" statement.
 * 
 * @author Ben Yu
 */
public final class IfStatement extends ValueObject implements Statement {
  public final Expression condition;
  public final Statement then;
  public final List<Pair<Expression, Statement>> elseifs;
  public final Statement otherwise;
  
  public IfStatement(
      Expression condition, Statement then,
      List<Pair<Expression, Statement>> elseifs, Statement otherwise) {
    this.condition = condition;
    this.then = then;
    this.elseifs = elseifs;
    this.otherwise = otherwise;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("if (").append(condition).append(") ").append(then);
    for (Pair<Expression, Statement> elseif : elseifs) {
      builder.append(" else if (").append(elseif.a).append(") ").append(elseif.b);
    }
    if (otherwise != null) {
      builder.append(" else ").append(otherwise);
    }
    return builder.toString();
  }
}
