package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents the comma delimited expression list used in the initializer of a "for" loop.
 * 
 * @author Ben Yu
 */
public final class ExpressionListStatement extends ValueObject implements Statement {
  public final List<Expression> expressions;

  public ExpressionListStatement(List<Expression> expressions) {
    this.expressions = expressions;
  }
  
  @Override public String toString() {
    return Strings.join(", ", expressions) + ";";
  }
}
