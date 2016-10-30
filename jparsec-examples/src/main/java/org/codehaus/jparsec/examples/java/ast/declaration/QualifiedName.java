package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a qualified name in import or package statement.
 * 
 * @author Ben Yu
 */
public final class QualifiedName extends ValueObject {
  public final List<String> names;

  public QualifiedName(List<String> names) {
    this.names = names;
  }
  
  @Override public String toString() {
    return Strings.join(".", names);
  }
}
