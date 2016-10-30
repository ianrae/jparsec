package org.codehaus.jparsec.examples.java.ast.declaration;

/*-
 * #%L
 * jParsec Examples
 * %%
 * Copyright (C) 2013 - 2016 jparsec
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
