package org.codehaus.jparsec.examples.calculator;

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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link Calculator}.
 * 
 * @author Ben Yu
 */
public class CalculatorTest {

  @Test
  public void testEvaluate() {
    assertResult(1, "1");
    assertResult(1, "(1)");
    assertResult(3, "1+2");
    assertResult(-5, "1+2*-3");
    assertResult(1, "((1-2)/-1)");
  }

  private static void assertResult(int expected, String source) {
    assertEquals(expected, Calculator.evaluate(source));
  }
}
