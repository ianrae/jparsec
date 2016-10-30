package org.codehaus.jparsec.examples.java.ast.declaration;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a type parameter in a type or method definition.
 * 
 * @author Ben Yu
 */
public final class TypeParameterDef extends ValueObject {
  
  public final String name;
  public final TypeLiteral bound;
  
  public TypeParameterDef(String name, TypeLiteral bound) {
    this.name = name;
    this.bound = bound;
  }
  
  @Override public String toString() {
    return name + (bound == null ? "" : " extends " + bound);
  }
}
