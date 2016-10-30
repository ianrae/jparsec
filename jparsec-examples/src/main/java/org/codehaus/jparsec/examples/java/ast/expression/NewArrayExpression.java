package org.codehaus.jparsec.examples.java.ast.expression;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a "new Foo[] {...}" or "new Foo[size] {...}" expression.
 * 
 * @author Ben Yu
 */
public final class NewArrayExpression extends ValueObject implements Expression {
  public final TypeLiteral elementType;
  public final Expression length;
  public final List<Expression> initializer;
  
  public NewArrayExpression(TypeLiteral type, Expression length, List<Expression> initializer) {
    this.elementType = type;
    this.length = length;
    this.initializer = initializer;
  }
  
  @Override public String toString() {
    return "new " + elementType + "[" + (length == null ? "" : length) + "]"
        + (initializer == null ? "" : " {" + Strings.join(", ", initializer) + "}");
  }
}
