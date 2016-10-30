package org.codehaus.jparsec.examples.java.parser;

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

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.pattern.CharPredicates;
import org.codehaus.jparsec.pattern.Pattern;
import org.codehaus.jparsec.pattern.Patterns;

import static org.codehaus.jparsec.pattern.Patterns.*;

public class JavaScanners {
  
  private JavaScanners(){}

  /**
   * A {@link org.codehaus.jparsec.pattern.Pattern} object that matches an octal integer that starts with a {@code 0} and is followed by 1 or more
   * {@code [0 - 7]} characters. A Java octal is always at least two characters long.
   */
  public static final Pattern OCT_INTEGER_PATTERN = isChar('0').next(many1(CharPredicates.range('0', '7')));

  /**
   * A {@link Pattern} object that matches a decimal integer, which is either '0' or starts with a non-zero digit and is followed by 0 or
   * more digits.
   */
  public static final Pattern DEC_INTEGER_PATTERN = or(isChar('0').next(Patterns.not(many1(CharPredicates.IS_DIGIT))),
      sequence(isChar(CharPredicates.range('1', '9')), many(CharPredicates.IS_DIGIT)));


  /** Scanner for a decimal number. single character '0' is an integer literal
   * 
   * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.1">Java Language Specification</a> */
  public static final Parser<String> DEC_INTEGER =
      DEC_INTEGER_PATTERN.toScanner("decimal integer").source();

  /** Scanner for a octal number. 0 is the leading digit. */
  public static final Parser<String> OCT_INTEGER =
      OCT_INTEGER_PATTERN.toScanner("octal integer").source();
}
