package org.codehaus.jparsec.examples.java.ast.type;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a non-array type literal.
 * 
 * @author Ben Yu
 */
public final class SimpleTypeLiteral extends ValueObject implements TypeLiteral {
  public final List<String> names;
  public final List<TypeLiteral> arguments;
  
  public SimpleTypeLiteral(List<String> names, List<TypeLiteral> arguments) {
    this.names = Collections.unmodifiableList(names);
    this.arguments = Collections.unmodifiableList(arguments);
  }
  
  @Override public String toString() {
    return Strings.join(".", names.toArray())
        + (arguments.isEmpty() ? "" : "<" + Strings.join(", ", arguments)+ ">");
  }
}
