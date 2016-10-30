package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.statement.BlockStatement;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;
import org.codehaus.jparsec.examples.java.ast.statement.ParameterDef;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents a constructor definition.
 * 
 * @author benyu
 */
public final class ConstructorDef extends ValueObject implements Member {
  public final List<Modifier> modifiers;
  public final String name;
  public final List<ParameterDef> parameters;
  public final List<TypeLiteral> exceptions;
  public final BlockStatement body;
  
  public ConstructorDef(List<Modifier> modifiers, String name,
      List<ParameterDef> parameters, List<TypeLiteral> exceptions, BlockStatement body) {
    this.modifiers = modifiers;
    this.name = name;
    this.parameters = parameters;
    this.exceptions = exceptions;
    this.body = body;
  }
  
  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Modifier modifier : modifiers) {
      builder.append(modifier).append(' ');
    }
    builder.append(name).append('(');
    Strings.join(builder, ", ", parameters);
    builder.append(')');
    if (exceptions != null) {
      builder.append(" throws ");
      Strings.join(builder, ", ", exceptions);
    }
    builder.append(' ').append(body);
    return builder.toString();
  }
}
