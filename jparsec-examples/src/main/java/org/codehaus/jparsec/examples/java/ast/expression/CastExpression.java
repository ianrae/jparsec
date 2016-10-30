package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * A cast expression.
 * 
 * @author Ben Yu
 */
public final class CastExpression extends ValueObject implements Expression {
  public final TypeLiteral type;
  public final Expression expression;
  
  public CastExpression(TypeLiteral type, Expression expression) {
    this.type = type;
    this.expression = expression;
  }
  
  @Override public String toString() {
    return "((" + type + ") " + expression + ")";
  }
}
