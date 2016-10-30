package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;

/**
 * Represents an annotation definition.
 * 
 * @author Ben Yu
 */
public final class AnnotationDef extends ValueObject implements Declaration {
  public final List<Modifier> modifiers;
  public final String name;
  public final DefBody body;
  
  public AnnotationDef(
      List<Modifier> modifiers, String name, DefBody body) {
    this.modifiers = modifiers;
    this.name = name;
    this.body = body;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append("@interface ").append(name).append(' ').append(body);
    return builder.toString();
  }
}
