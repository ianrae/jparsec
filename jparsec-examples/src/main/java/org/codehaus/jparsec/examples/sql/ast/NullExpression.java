package org.codehaus.jparsec.examples.sql.ast;

/**
 * Represents "null".
 * 
 * @author Ben Yu
 */
public final class NullExpression implements Expression {
  private NullExpression() {}
  
  public static final NullExpression instance = new NullExpression();
}
