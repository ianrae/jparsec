package org.codehaus.jparsec.examples.java.ast.declaration;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents an import statement.
 * 
 * @author Ben Yu
 */
public final class Import extends ValueObject {
  public final boolean staticImport;
  public final QualifiedName qname;
  public final boolean wildcard;
  
  public Import(boolean staticImport, QualifiedName qname, boolean wildcard) {
    this.staticImport = staticImport;
    this.qname = qname;
    this.wildcard = wildcard;
  }
  
  @Override public String toString() {
    return "import " + (staticImport ? "static " : "") + qname + (wildcard ? ".*" : "") + ";";
  }
}
