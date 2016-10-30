package org.codehaus.jparsec.examples.java.ast.expression;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.declaration.DefBody;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a non-qualified "new" statement with possibly anonymous class syntax.
 * 
 * @author Ben Yu
 */
public class NewExpression extends ValueObject implements Expression {
  public final Expression qualifier;
  public final TypeLiteral type;
  public final List<Expression> arguments;
  public final DefBody classBody;
  
  public NewExpression(
      Expression qualifier, TypeLiteral type, List<Expression> arguments, DefBody classBody) {
    this.qualifier = qualifier;
    this.type = type;
    this.arguments = arguments;
    this.classBody = classBody;
  }
  
  @Override public String toString() {
    return (qualifier == null ? "" : qualifier + ".")
        + "new " + type + "(" + Strings.join(", ", arguments) + ")"
        + (classBody == null ? "" : " " + classBody);
  }
}
