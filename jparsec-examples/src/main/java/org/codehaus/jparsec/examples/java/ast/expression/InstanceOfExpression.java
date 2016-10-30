package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents "expr instanceof type".
 * 
 * @author Ben Yu
 */
public final class InstanceOfExpression extends ValueObject implements Expression {
  public final Expression expression;
  public final TypeLiteral typeLiteral;

  public InstanceOfExpression(Expression expression, TypeLiteral typeLiteral) {
    this.expression = expression;
    this.typeLiteral = typeLiteral;
  }
  
  @Override public String toString() {
    return "(" + expression + " instanceof " + typeLiteral + ")";
  }
}
