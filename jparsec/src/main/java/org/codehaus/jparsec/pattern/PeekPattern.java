package org.codehaus.jparsec.pattern;

/*-
 * #%L
 * jParsec
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

class PeekPattern extends Pattern {

  private final Pattern pp;

  public PeekPattern(Pattern pp) {
    this.pp = pp;
  }

  @Override public int match(CharSequence src, int begin, int end) {
    if (pp.match(src, begin, end) == Pattern.MISMATCH)
      return Pattern.MISMATCH;
    else return 0;
  }

  @Override public String toString() {
    return "(?:" + pp.toString() + ")";
  }
}
