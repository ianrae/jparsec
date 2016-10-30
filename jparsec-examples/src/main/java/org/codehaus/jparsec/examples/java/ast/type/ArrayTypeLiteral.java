package org.codehaus.jparsec.examples.java.ast.type;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents an array type literal.
 * 
 * @author Ben Yu
 */
public final class ArrayTypeLiteral extends ValueObject implements TypeLiteral {
  public final TypeLiteral elementType;

  public ArrayTypeLiteral(TypeLiteral elementType) {
    this.elementType = elementType;
  }
  
  @Override public String toString() {
    return elementType + "[]";
  }
}
