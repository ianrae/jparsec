package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a field definition.
 * 
 * @author Ben Yu
 */
public final class FieldDef extends ValueObject implements Member {
  public final List<Modifier> modifiers;
  public final TypeLiteral type;
  public final String name;
  public final Expression value;
  
  public FieldDef(List<Modifier> modifiers, TypeLiteral type, String name, Expression value) {
    this.modifiers = Collections.unmodifiableList(modifiers);
    this.type = type;
    this.name = name;
    this.value = value;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append(type).append(' ').append(name);
    if (value != null) {
      builder.append(" = ").append(value);
    }
    builder.append(';');
    return builder.toString();
  }
}
