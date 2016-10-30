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

import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Maps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

/**
 * Unit test for {@link Lexicon}.
 * 
 * @author Ben Yu
 */
public class LexiconTest {

  @Test
  public void testWord() {
    Map<String, Object> map = Maps.<String, Object>constant("foo");
    Parser<?> tokenizer = Terminals.CharLiteral.SINGLE_QUOTE_TOKENIZER;
    Lexicon lexicon = new Lexicon(map, tokenizer);
    assertSame(tokenizer, lexicon.tokenizer);
    assertEquals("foo", lexicon.word("whatever"));
  }

  @Test
  public void testWord_throwsForNullValue() {
    Map<String, Object> map = Maps.<String, Object>constant(null);
    Parser<?> tokenizer = Terminals.CharLiteral.SINGLE_QUOTE_TOKENIZER;
    Lexicon lexicon = new Lexicon(map, tokenizer);
    assertSame(tokenizer, lexicon.tokenizer);
    try {
      lexicon.word("whatever");
      fail();
    } catch (IllegalArgumentException e) {}
  }

}
