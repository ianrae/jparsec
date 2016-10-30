package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents an interface definition.
 * 
 * @author Ben Yu
 */
public final class InterfaceDef extends ValueObject implements Declaration {
  public final List<Modifier> modifiers;
  public final String name;
  public final List<TypeParameterDef> typeParameters;
  public final List<TypeLiteral> interfaces;
  public final DefBody body;
  
  public InterfaceDef(
      List<Modifier> modifiers, String name, List<TypeParameterDef> typeParameters,
      List<TypeLiteral> interfaces, DefBody body) {
    this.modifiers = modifiers;
    this.name = name;
    this.typeParameters = typeParameters;
    this.interfaces = interfaces;
    this.body = body;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append("interface ").append(name);
    if (typeParameters != null) {
      builder.append('<');
      Strings.join(builder, ", ", typeParameters);
      builder.append('>');
    }
    if (interfaces != null) {
      builder.append(" extends ");
      Strings.join(builder, ", ", interfaces);
    }
    builder.append(' ').append(body);
    return builder.toString();
  }
}
