package org.codehaus.jparsec.examples.sql.ast;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models a tuple of expressions such as "(1, 2, 3)".
 * 
 * @author Ben Yu
 */
public class TupleExpression extends ValueObject implements Expression {
  public final List<Expression> expressions;

  public TupleExpression(List<Expression> expressions) {
    this.expressions = Collections.unmodifiableList(expressions);
  }
  
  public static TupleExpression of(Expression... expressions) {
    return new TupleExpression(Arrays.asList(expressions));
  }
}
