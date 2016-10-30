package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents an "array[i]" expression.
 * 
 * @author Ben Yu
 */
public final class ArraySubscriptExpression extends ValueObject implements Expression {
  public final Expression array;
  public final Expression index;
  
  public ArraySubscriptExpression(Expression array, Expression index) {
    this.array = array;
    this.index = index;
  }
  
  @Override public String toString() {
    return array + "[" + index + "]";
  }
}
