package org.codehaus.jparsec.examples.java.ast.statement;

import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents the enhanced for loop statement.
 * 
 * @author Ben Yu
 */
public final class ForeachStatement extends ValueObject implements Statement {
  public final TypeLiteral type;
  public final String var;
  public final Expression of;
  public final Statement statement;
  
  public ForeachStatement(TypeLiteral type, String var, Expression of, Statement statement) {
    this.type = type;
    this.var = var;
    this.of = of;
    this.statement = statement;
  }
 
  @Override public String toString() {
    return "for (" + type + " " + var + " : " + of + ") " + statement;
  }
}
