package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents "Foo.class".
 * 
 * @author Ben Yu
 */
public final class ClassLiteral extends ValueObject implements Expression {
  public final TypeLiteral className;

  public ClassLiteral(TypeLiteral className) {
    this.className = className;
  }
  
  @Override public String toString() {
    return className.toString() + ".class";
  }
}
