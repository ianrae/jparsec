package org.codehaus.jparsec.examples.java.ast.statement;

/**
 * Represents built in modifiers.
 * 
 * @author Ben Yu
 */
public enum SystemModifier implements Modifier {
  STATIC, ABSTRACT, FINAL, SYNCHRONIZED, VOLATILE, TRANSIENT, NATIVE, PUBLIC, PROTECTED, PRIVATE;
  
  @Override public String toString() {
    return super.toString().toLowerCase();
  }
}
