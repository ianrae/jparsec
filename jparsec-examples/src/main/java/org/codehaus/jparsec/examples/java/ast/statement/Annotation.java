package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.examples.java.ast.type.TypeLiteral;

/**
 * Represents the use of an annotation.
 * 
 * @author Ben Yu
 */
public final class Annotation extends ValueObject implements Modifier {
  
  public static final class Element extends ValueObject {
    public final String name;
    public final Expression value;
    
    public Element(String name, Expression value) {
      this.name = name;
      this.value = value;
    }
    
    @Override public String toString() {
      return (name == null ? value.toString() : name + "=" + value);
    }
  }
  public final TypeLiteral type;
  public final List<Element> elements;
  
  public Annotation(TypeLiteral type, List<Element> elements) {
    this.type = type;
    this.elements = elements;
  }
  
  @Override public String toString() {
    return "@" + type + (elements == null ? "" : "(" + Strings.join(", ", elements) + ")");
  }
}
