package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;

/**
 * Represents a "this(params)" statement.
 * 
 * @author benyu
 */
public final class SuperCallStatement extends ValueObject implements Statement {
  public final List<Expression> args;

  public SuperCallStatement(List<Expression> args) {
    this.args = Collections.unmodifiableList(args);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("super(");
    Strings.join(builder, ", ", args);
    builder.append(");");
    return builder.toString();
  }
}
