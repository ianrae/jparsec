package org.codehaus.jparsec.examples.sql.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.functors.Pair;

/**
 * The "{@code case expr when cond then val ... end}" expression.
 * 
 * @author Ben Yu
 */
public final class SimpleCaseExpression extends ValueObject implements Expression {
  public final Expression condition;
  public final List<Pair<Expression, Expression>> cases;
  public final Expression defaultValue; // null if no default
  
  public SimpleCaseExpression(
      Expression condition, List<Pair<Expression, Expression>> cases, Expression defaultValue) {
    this.condition = condition;
    this.cases = Collections.unmodifiableList(cases);
    this.defaultValue = defaultValue;
  }
}
