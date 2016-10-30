package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents an enum definition.
 * 
 * @author Ben Yu
 */
public final class EnumDef extends ValueObject implements Declaration {
  
  /** Represents an enum value. */
  public static final class Value {
    public final String name;
    public final List<Expression> arguments;
    public final List<Member> body;
    
    public Value(String name, List<Expression> arguments, List<Member> body) {
      this.name = name;
      this.arguments = arguments;
      this.body = body;
    }
    
    @Override public String toString() {
      return name + (arguments == null ? "" : "(" + Strings.join(", ", arguments) + ")")
        + (body == null ? "" : " {" + Strings.join(" ", body) +"}") ;
    }
  }
  
  public final List<Modifier> modifiers;
  public final String name;
  public final List<TypeLiteral> interfaces;
  public final List<Value> values;
  public final List<Member> members;
  
  public EnumDef(List<Modifier> modifiers, String name, List<TypeLiteral> interfaces,
      List<Value> values, List<Member> members) {
    this.modifiers = modifiers;
    this.name = name;
    this.interfaces = interfaces;
    this.values = values;
    this.members = members;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append("enum ").append(name);
    if (interfaces != null) {
      builder.append(" implements ");
      Strings.join(builder, ", ", interfaces);
    }
    builder.append(" {");
    Strings.join(builder, ", ", values);
    if (members != null) {
      builder.append("; ");
      Strings.join(builder, " ", members);
    }
    builder.append("}");
    return builder.toString();
  }
}
