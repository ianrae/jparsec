package org.codehaus.jparsec.examples.java.ast.declaration;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.statement.BlockStatement;

/**
 * Represents a static initializer or an instance initializer.
 * 
 * @author benyu
 */
public final class ClassInitializerDef extends ValueObject implements Member {
  public final boolean isStatic;
  public final BlockStatement block;
  
  public ClassInitializerDef(boolean isStatic, BlockStatement block) {
    this.isStatic = isStatic;
    this.block = block;
  }
  
  @Override public String toString() {
    return (isStatic ? "static " : "") + block;
  }
}
