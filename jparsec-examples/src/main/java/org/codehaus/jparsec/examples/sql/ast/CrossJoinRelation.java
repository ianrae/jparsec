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

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * Models cross join.
 * 
 * @author Ben Yu
 */
public final class CrossJoinRelation extends ValueObject implements Relation {
  public final Relation left;
  public final Relation right;
  
  public CrossJoinRelation(Relation left, Relation right) {
    this.left = left;
    this.right = right;
  }
}
