package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a single variable declaration.
 * 
 * @author Ben Yu
 */
public final class VarStatement extends ValueObject implements Statement {
  
  public static final class Var extends ValueObject {
    public final String name;
    public final Expression value;
    
    public Var(String name, Expression value) {
      this.name = name;
      this.value = value;
    }
    
    @Override public String toString() {
      return name + (value == null ? "" : " = " + value);
    }
  }
  public final List<Modifier> modifiers;
  public final TypeLiteral type;
  public final List<Var> vars;
  
  public VarStatement(
      List<Modifier> modifiers, TypeLiteral type, List<Var> vars) {
    this.modifiers = modifiers;
    this.type = type;
    this.vars = vars;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append(type).append(" ");
    Strings.join(builder, ", ", vars);
    builder.append(";");
    return builder.toString();
  }
}
