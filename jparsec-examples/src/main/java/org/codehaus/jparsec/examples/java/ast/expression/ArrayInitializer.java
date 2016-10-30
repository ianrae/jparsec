package org.codehaus.jparsec.examples.java.ast.expression;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents array initializer used in variable definition.
 * 
 * @author Ben Yu
 */
public final class ArrayInitializer extends ValueObject implements Expression {
  public final List<Expression> values;

  public ArrayInitializer(List<Expression> values) {
    this.values = values;
  }
  
  @Override public String toString() {
    return "{" + Strings.join(", ", values) + "}";
  }
}
