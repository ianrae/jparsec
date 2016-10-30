package org.codehaus.jparsec.examples.sql.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.functors.Pair;

/**
 * The "{@code case when cond1 then val1 when cond2 then val2 else def end}" expression.
 * 
 * @author Ben Yu
 */
public final class FullCaseExpression extends ValueObject implements Expression {
  public final List<Pair<Expression, Expression>> cases;
  public final Expression defaultValue;
  
  public FullCaseExpression(List<Pair<Expression, Expression>> cases, Expression defaultValue) {
    this.cases = Collections.unmodifiableList(cases);
    this.defaultValue = defaultValue;
  }
}
