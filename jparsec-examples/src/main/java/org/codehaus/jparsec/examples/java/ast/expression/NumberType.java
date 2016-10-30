package org.codehaus.jparsec.examples.java.ast.expression;

/**
 * Distinguishes between "L", "F", "D" and int.
 * 
 * @author Ben Yu
 */
public enum NumberType {
  INT(""), LONG("L"), FLOAT("F"), DOUBLE("D");
  
  private final String name;
  
  private NumberType(String name) {
    this.name = name;
  }
  
  @Override public String toString() {
    return name;
  }
}
