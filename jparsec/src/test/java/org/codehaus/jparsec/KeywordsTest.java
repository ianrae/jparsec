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

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link Keywords}.
 * 
 * @author Ben Yu
 */
public class KeywordsTest {

  @Test
  public void testLexicon_caseSensitive() {
    List<String> keywords = asList("foo", "Bar");
    Lexicon lexicon = Keywords.lexicon(
        Scanners.IDENTIFIER, keywords, StringCase.CASE_SENSITIVE, TokenizerMaps.IDENTIFIER_FRAGMENT);
    for (String keyword : keywords) {
      assertEquals(Tokens.reserved(keyword), lexicon.word(keyword));
    }
    for (String keyword : keywords) {
      assertEquals(Tokens.reserved(keyword), lexicon.tokenizer.parse(keyword));
    }
    assertEquals(Tokens.identifier("FOO"), lexicon.tokenizer.parse("FOO"));
    assertEquals(Tokens.identifier("baz"), lexicon.tokenizer.parse("baz"));
  }

  @Test
  public void testLexicon_caseInsensitive() {
    List<String> keywords = asList("foo", "Bar");
    Lexicon lexicon = Keywords.lexicon(
        Scanners.IDENTIFIER, keywords, StringCase.CASE_INSENSITIVE, TokenizerMaps.IDENTIFIER_FRAGMENT);
    for (String keyword : keywords) {
      assertEquals(Tokens.reserved(keyword), lexicon.word(keyword));
      assertEquals(Tokens.reserved(keyword), lexicon.word(keyword.toUpperCase()));
    }
    for (String keyword : keywords) {
      assertEquals(Tokens.reserved(keyword), lexicon.tokenizer.parse(keyword));
      assertEquals(Tokens.reserved(keyword), lexicon.tokenizer.parse(keyword.toUpperCase()));
    }
    assertEquals(Tokens.identifier("baz"), lexicon.tokenizer.parse("baz"));
  }

  @Test
  public void testUnique() {
    Asserts.assertArrayEquals(
        Keywords.unique(String.CASE_INSENSITIVE_ORDER, "foo", "Foo", "foo", "bar"),
        "bar", "foo");
  }
}
