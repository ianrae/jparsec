package org.codehaus.jparsec.examples.bnf.ast;

/**
 * Qualifies a rule.
 * 
 * @author benyu
 */
public enum Quantifier {
  
  STAR("*"), PLUS("+"), OPTIONAL("?");
  
  private final String name;
  
  private Quantifier(String name) {
    this.name = name;
  }
  
  @Override public String toString() {
    return name;
  }
}
