/*****************************************************************************
 * Copyright 2013 (C) Codehaus.org                                                *
 * ------------------------------------------------------------------------- *
 * Licensed under the Apache License, Version 2.0 (the "License");           *
 * you may not use this file except in compliance with the License.          *
 * You may obtain a copy of the License at                                   *
 *                                                                           *
 * http://www.apache.org/licenses/LICENSE-2.0                                *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing, software       *
 * distributed under the License is distributed on an "AS IS" BASIS,         *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * See the License for the specific language governing permissions and       *
 * limitations under the License.                                            *
 *****************************************************************************/
package org.codehaus.jparsec.pattern;

class HasExactPattern extends Pattern {

  private final int n;

  public HasExactPattern(int n) {
    this.n = n;
  }

  @Override
  public Pattern derive(char c) {
    return (n <= 0) ? Patterns.NEVER : Patterns.hasExact(n - 1);
  }

  @Override
  public int match(CharSequence src, int begin, int end) {
    if ((begin + n) != end)
      return Pattern.MISMATCH;
    else
      return n;
  }

  @Override
  public String toString() {
    return ".{" + n + "}";
  }
}