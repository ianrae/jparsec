package org.codehaus.jparsec.examples.java.ast.statement;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents the "try-catch-finally" statement.
 * 
 * @author Ben Yu
 */
public final class TryStatement extends ValueObject implements Statement {
  
  public static final class CatchBlock extends ValueObject {
    public final ParameterDef parameter;
    public final BlockStatement body;
    
    public CatchBlock(ParameterDef parameter, BlockStatement body) {
      this.parameter = parameter;
      this.body = body;
    }
    
    @Override public String toString() {
      return "catch (" + parameter + ") " + body; 
    }
  }
  
  public final BlockStatement tryBlock;
  public final List<CatchBlock> catchBlocks;
  public final BlockStatement finallyBlock;
  
  public TryStatement(
      BlockStatement tryBlock, List<CatchBlock> catchBlocks, BlockStatement finallyBlock) {
    this.tryBlock = tryBlock;
    this.catchBlocks = catchBlocks;
    this.finallyBlock = finallyBlock;
  }
  
  @Override public String toString() {
    return "try " + tryBlock + Strings.prependEach(" ", catchBlocks) + 
      (finallyBlock == null ? "" : " finally " + finallyBlock);
  }
}
