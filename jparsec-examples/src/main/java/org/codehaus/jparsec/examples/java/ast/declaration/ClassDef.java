package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a class definition;
 * 
 * @author Ben Yu
 */
public final class ClassDef extends ValueObject implements Declaration {
  public final List<Modifier> modifiers;
  public final String name;
  public final List<TypeParameterDef> typeParameters;
  public final TypeLiteral superclass;
  public final List<TypeLiteral> interfaces;
  public final DefBody body;
  
  public ClassDef(List<Modifier> modifiers, String name, List<TypeParameterDef> typeParameters,
      TypeLiteral superclass, List<TypeLiteral> interfaces, DefBody body) {
    this.modifiers = modifiers;
    this.name = name;
    this.typeParameters = typeParameters;
    this.superclass = superclass;
    this.interfaces = interfaces;
    this.body = body;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append("class ").append(name);
    if (typeParameters != null) {
      builder.append('<');
      Strings.join(builder, ", ", typeParameters);
      builder.append('>');
    }
    if (superclass != null) {
      builder.append(" extends ").append(superclass);
    }
    if (interfaces != null) {
      builder.append(" implements ");
      Strings.join(builder, ", ", interfaces);
    }
    builder.append(' ').append(body);
    return builder.toString();
  }
}
