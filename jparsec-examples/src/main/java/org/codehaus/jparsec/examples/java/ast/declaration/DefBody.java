package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents the body of interface, class, or annotation.
 * 
 * @author Ben Yu
 */
public final class DefBody extends ValueObject {
  public final List<Member> members;

  public DefBody(List<Member> members) {
    this.members = Collections.unmodifiableList(members);
  }
  
  @Override public String toString() {
    return "{" + Strings.join(" ", members) + "}";
  }
}
