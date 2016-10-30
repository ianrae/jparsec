package org.codehaus.jparsec.examples.java.ast.expression;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents "obj.field", "SomeType.staticField", "SomeType.SomeNestedType"
 * or "org.codehaus.jparsec" kind of qualified expressions. (The latter isn't really an expression,
 * but at parse time we have no way to tell).
 * 
 * @author Ben Yu
 */
public final class QualifiedExpression extends ValueObject implements Expression {
  public final Expression qualifier;
  public final String name;
  
  public QualifiedExpression(Expression qualifier, String name) {
    this.qualifier = qualifier;
    this.name = name;
  }
  
  @Override public String toString() {
    return "(" + qualifier + "." + name + ")";
  }
}
