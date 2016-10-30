package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a parameter definition.
 * 
 * @author Ben Yu
 */
public final class ParameterDef extends ValueObject {
  public final List<Modifier> modifiers;
  public final TypeLiteral type;
  public final boolean vararg;
  public final String name;
  
  public ParameterDef(List<Modifier> modifiers, TypeLiteral type, boolean vararg, String name) {
    this.modifiers = modifiers;
    this.type = type;
    this.vararg = vararg;
    this.name = name;
  }

  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append(type);
    if (vararg) builder.append("...");
    builder.append(' ').append(name);
    return builder.toString();
  }
}
