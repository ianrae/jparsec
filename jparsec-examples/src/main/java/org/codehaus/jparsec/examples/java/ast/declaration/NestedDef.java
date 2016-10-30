package org.codehaus.jparsec.examples.java.ast.declaration;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a nested declaration.
 * 
 * @author benyu
 */
public final class NestedDef extends ValueObject implements Member {
  public final Declaration declaration;

  public NestedDef(Declaration declaration) {
    this.declaration = declaration;
  }
  
  @Override public String toString() {
    return declaration.toString();
  }
}
