package org.codehaus.jparsec.examples.java.ast.declaration;

import java.util.List;

import org.codehaus.jparsec.examples.common.Strings;
import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a single java source file.
 * 
 * @author Ben Yu
 */
public final class Program extends ValueObject {
  public final QualifiedName packageName;
  public final List<Import> imports;
  public final List<Declaration> declarations;
  
  public Program(QualifiedName packageName, List<Import> imports, List<Declaration> declarations) {
    this.packageName = packageName;
    this.imports = imports;
    this.declarations = declarations;
  }
  
  @Override public String toString() {
    return (packageName == null ? "" : "package " + packageName + "; ")
      + (imports.isEmpty() ? "" : Strings.join(" ", imports) + ' ')
      + Strings.join(" ", declarations);
  }
}
