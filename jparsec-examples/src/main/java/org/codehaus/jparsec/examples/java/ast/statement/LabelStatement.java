package org.codehaus.jparsec.examples.java.ast.statement;

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

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Represents a label statement such as "foo:".
 * 
 * @author Ben Yu
 */
public final class LabelStatement extends ValueObject implements Statement {
  public final String name;
  public final Statement statement;

  public LabelStatement(String name, Statement statement) {
    this.name = name;
    this.statement = statement;
  }
  
  @Override public String toString() {
    return name + ": " + statement;
  }
}
