package org.codehaus.jparsec;

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

import org.codehaus.jparsec.Tokens.Fragment;
import org.codehaus.jparsec.Tokens.Tag;
import org.codehaus.jparsec.functors.Map;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit test for {@link TokenizerMaps}.
 * 
 * @author Ben Yu
 */
public class TokenizerMapsTest {

  @Test
  public void testFragment() {
    assertFragment("foo", TokenizerMaps.fragment("foo"));
  }

  @Test
  public void testReservedFragment() {
    assertFragment(Tag.RESERVED, TokenizerMaps.RESERVED_FRAGMENT);
  }

  @Test
  public void testIdentifierFragment() {
    assertFragment(Tag.IDENTIFIER, TokenizerMaps.IDENTIFIER_FRAGMENT);
  }

  @Test
  public void testIntegerFragment() {
    assertFragment(Tag.INTEGER, TokenizerMaps.INTEGER_FRAGMENT);
  }

  @Test
  public void testDecimalFragment() {
    assertFragment(Tag.DECIMAL, TokenizerMaps.DECIMAL_FRAGMENT);
  }

  @Test
  public void testSingleQuoteChar() {
    assertEquals("SINGLE_QUOTE_CHAR", TokenizerMaps.SINGLE_QUOTE_CHAR.toString());
    assertEquals(Character.valueOf('a'), TokenizerMaps.SINGLE_QUOTE_CHAR.map("'a'"));
    assertEquals(Character.valueOf('a'), TokenizerMaps.SINGLE_QUOTE_CHAR.map("'\\a'"));
    try {
      TokenizerMaps.SINGLE_QUOTE_CHAR.map("'abc'");
      fail();
    } catch (IllegalStateException e) {}
  }

  @Test
  public void testDecAsLong() {
    assertEquals("DEC_AS_LONG", TokenizerMaps.DEC_AS_LONG.toString());
    assertEquals(Long.valueOf(123L), TokenizerMaps.DEC_AS_LONG.map("123"));
  }

  @Test
  public void testOctAsLong() {
    assertEquals("OCT_AS_LONG", TokenizerMaps.OCT_AS_LONG.toString());
    assertEquals(Long.valueOf(10L), TokenizerMaps.OCT_AS_LONG.map("012"));
  }

  @Test
  public void testHexAsLong() {
    assertEquals("HEX_AS_LONG", TokenizerMaps.HEX_AS_LONG.toString());
    assertEquals(Long.valueOf(255L), TokenizerMaps.HEX_AS_LONG.map("0xff"));
  }

  @Test
  public void testDoubleQuoteString() {
    assertEquals("DOUBLE_QUOTE_STRING", TokenizerMaps.DOUBLE_QUOTE_STRING.toString());
    assertEquals("c:\\home", TokenizerMaps.DOUBLE_QUOTE_STRING.map("\"c:\\\\home\""));
  }

  @Test
  public void testSingleQuoteString() {
    assertEquals("SINGLE_QUOTE_STRING", TokenizerMaps.SINGLE_QUOTE_STRING.toString());
    assertEquals("'a'", TokenizerMaps.SINGLE_QUOTE_STRING.map("'''a'''"));
  }

  @Test
  public void testScientificNotation() {
    assertEquals("SCIENTIFIC_NOTATION", TokenizerMaps.SCIENTIFIC_NOTATION.toString());
    assertEquals(Tokens.scientificNotation("1", "2"),
        TokenizerMaps.SCIENTIFIC_NOTATION.map("1e2"));
    assertEquals(Tokens.scientificNotation("1", "2"),
        TokenizerMaps.SCIENTIFIC_NOTATION.map("1e+2"));
    assertEquals(Tokens.scientificNotation("1", "-2"),
        TokenizerMaps.SCIENTIFIC_NOTATION.map("1e-2"));
    assertEquals(Tokens.scientificNotation("1.2", "30"),
        TokenizerMaps.SCIENTIFIC_NOTATION.map("1.2E30"));
    assertEquals(Tokens.scientificNotation("0", "0"),
        TokenizerMaps.SCIENTIFIC_NOTATION.map("0E0"));
  }

  private static void assertFragment(Object tag, Map<String, Fragment> map) {
    Fragment fragment = map.map("foo");
    assertEquals(tag, fragment.tag());
    assertEquals("foo", fragment.text());
    assertEquals(tag.toString(), map.toString());
  }
}
