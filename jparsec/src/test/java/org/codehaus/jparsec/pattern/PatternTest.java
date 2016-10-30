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

import org.junit.Test;

import static org.codehaus.jparsec.pattern.Pattern.MISMATCH;
import static org.codehaus.jparsec.pattern.Patterns.ALWAYS;
import static org.codehaus.jparsec.pattern.Patterns.NEVER;
import static org.junit.Assert.*;

/**
 * Unit test for {@link Pattern}.
 * 
 * @author Ben Yu
 */
public class PatternTest {

  @Test
  public void testMismatch() {
    assertTrue(MISMATCH < 0);
  }

  @Test
  public void testNext() {
    assertEquals(2, Patterns.hasAtLeast(1).next(Patterns.hasAtLeast(1)).match("abc", 0, 3));
    assertEquals(MISMATCH, ALWAYS.next(NEVER).match("abc", 0, 3));
    assertEquals(MISMATCH, NEVER.next(ALWAYS).match("abc", 0, 3));
  }

  @Test
  public void testNot() {
    assertEquals(0, NEVER.not().match("abc", 0, 3));
    assertEquals(MISMATCH, ALWAYS.not().match("abc", 0, 3));
  }

  @Test
  public void testOr() {
    assertEquals(1, Patterns.hasAtLeast(1).or(Patterns.hasAtLeast(2)).match("abc", 0, 2));
    assertEquals(1, Patterns.hasAtLeast(1).or(Patterns.hasAtLeast(2)).match("abc", 0, 1));
    assertEquals(0, NEVER.or(ALWAYS).match("abc", 0, 0));
    assertEquals(MISMATCH, NEVER.or(NEVER).match("", 0, 0));
  }

  @Test
  public void testOptional() {
    assertEquals(0, NEVER.optional().match("", 0, 0));
    assertEquals(0, ALWAYS.optional().match("", 0, 0));
    assertEquals(1, Patterns.hasAtLeast(1).optional().match("abc", 0, 3));
  }

  @Test
  public void testPeek() {
    assertEquals(0, Patterns.hasAtLeast(1).peek().match("abc", 0, 3));
    assertEquals(0, ALWAYS.peek().match("abc", 0, 3));
    assertEquals(MISMATCH, NEVER.peek().match("abc", 0, 3));
  }

  @Test
  public void testRepeat() {
    assertEquals(0, ALWAYS.times(2).match("abc", 0, 3));
    assertEquals(2, Patterns.hasAtLeast(1).times(2).match("abc", 0, 3));
    assertEquals(MISMATCH, Patterns.hasAtLeast(1).times(2).match("abc", 0, 1));
    assertEquals(MISMATCH, NEVER.times(2).match("abc", 0, 3));
    assertEquals(0, Patterns.hasAtLeast(1).times(0).match("abc", 0, 3));
  }

  @Test
  public void testRepeat_throwsForNegativeNumber() {
    try {
      ALWAYS.times(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("n < 0", e.getMessage());
    }
  }

  @Test
  public void testIfElse() {
    assertEquals(3,
        Patterns.isChar('a').ifelse(Patterns.string("bc"), Patterns.INTEGER)
            .match("abcd", 0, 4));
    assertEquals(MISMATCH,
        Patterns.isChar('a').ifelse(Patterns.string("bd"), Patterns.INTEGER)
            .match("abcd", 0, 4));
    assertEquals(2,
        Patterns.isChar('a').ifelse(Patterns.string("bc"), Patterns.INTEGER)
            .match("12c", 0, 3));
    assertEquals(MISMATCH,
        Patterns.isChar('a').ifelse(Patterns.string("bc"), Patterns.INTEGER)
            .match("xxx", 0, 3));
  }

  @Test
  public void testMany() {
    assertEquals(0, NEVER.many().match("abc", 0, 3));
    assertEquals(0, ALWAYS.many().match("abc", 0, 3));
    assertEquals(3, Patterns.hasAtLeast(1).many().match("abc", 0, 3));
    assertEquals(4, Patterns.hasAtLeast(2).many().match("abcde", 0, 5));
  }

  @Test
  public void testMany1() {
    assertEquals(MISMATCH, NEVER.many1().match("abc", 0, 3));
    assertEquals(0, ALWAYS.many1().match("abc", 0, 3));
    assertEquals(3, Patterns.hasAtLeast(1).many1().match("abc", 0, 3));
    assertEquals(4, Patterns.hasAtLeast(2).many1().match("abcde", 0, 5));
  }

  @Test
  public void testMany_withMin() {
    assertEquals(0, ALWAYS.atLeast(2).match("abc", 0, 3));
    assertEquals(0, ALWAYS.atLeast(0).match("abc", 0, 3));
    assertEquals(0, NEVER.atLeast(0).match("abc", 0, 3));
    assertEquals(3, Patterns.hasAtLeast(1).atLeast(2).match("abc", 0, 3));
    assertEquals(4, Patterns.hasAtLeast(2).atLeast(2).match("abcde", 0, 5));
    assertEquals(MISMATCH, NEVER.atLeast(2).match("abc", 0, 3));
    assertEquals(MISMATCH, Patterns.hasAtLeast(1).atLeast(2).match("abc", 0, 1));
  }

  @Test
  public void testMany_throwsForNegativeMin() {
    try {
      ALWAYS.atLeast(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("min < 0", e.getMessage());
    }
  }

  @Test
  public void testSome() {
    assertEquals(0, NEVER.atMost(2).match("abc", 0, 3));
    assertEquals(0, ALWAYS.atMost(2).match("abc", 0, 3));
    assertEquals(0, NEVER.atMost(0).match("abc", 0, 3));
    assertEquals(0, ALWAYS.atMost(0).match("abc", 0, 3));
    assertEquals(2, Patterns.hasAtLeast(1).atMost(2).match("abc", 0, 3));
    assertEquals(4, Patterns.hasAtLeast(2).atMost(2).match("abcde", 0, 5));
  }

  @Test
  public void testSome_withMin() {
    assertEquals(MISMATCH, NEVER.times(1, 2).match("abc", 0, 3));
    assertEquals(0, ALWAYS.times(1, 2).match("abc", 0, 3));
    assertEquals(0, NEVER.times(0, 1).match("abc", 0, 3));
    assertEquals(2, Patterns.hasAtLeast(1).times(1, 2).match("abc", 0, 3));
    assertEquals(4, Patterns.hasAtLeast(2).times(1, 2).match("abcde", 0, 5));
  }

  @Test
  public void testSome_throwsForNegativeMax() {
    try {
      ALWAYS.atMost(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("max < 0", e.getMessage());
    }
  }

  @Test
  public void testSome_throwsForNegativeMinMax() {
    try {
      ALWAYS.times(-1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("min < 0", e.getMessage());
    }
    try {
      ALWAYS.times(1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("max < 0", e.getMessage());
    }
  }

}
