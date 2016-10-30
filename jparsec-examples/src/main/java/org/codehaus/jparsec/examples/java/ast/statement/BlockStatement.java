package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a block.
 * 
 * @author Ben Yu
 */
public final class BlockStatement extends ValueObject implements Statement {
  public final List<Statement> statements;

  public BlockStatement(List<Statement> statements) {
    this.statements = Collections.unmodifiableList(statements);
  }
  
  @Override public String toString() {
    return "{" + Strings.join(" ", statements) + "}";
  }
}
