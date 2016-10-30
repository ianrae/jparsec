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

import org.codehaus.jparsec.Parser.Mode;
import org.codehaus.jparsec.functors.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.codehaus.jparsec.Scanners.isChar;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author michael
 */
@RunWith(Parameterized.class)
@SuppressWarnings("deprecation")
public class ReluctantBetweenTest {

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
          return Arrays.asList(new Object[] {Mode.PRODUCTION}, new Object[] {Mode.DEBUG});
  }

  private final Mode mode;

  public ReluctantBetweenTest(Mode mode) {
    this.mode = mode;
  }

  @Test
	public void parsing_input_with_delimiting_character_inside_delimiters () {
    Parser<?> relunctant = Parsers.tuple(
        Scanners.IDENTIFIER.followedBy(Scanners.isChar(':')),
        Scanners.ANY_CHAR.many().source());
    assertEquals(new Pair<String,String>("hello","world)"),
        relunctant.reluctantBetween(Scanners.isChar('('), Scanners.isChar(')')).parse("(hello:world))", mode));
	}

  @Test
	public void parsing_simple_input() {
      assertEquals("hello",
          Scanners.IDENTIFIER.many().source().reluctantBetween(isChar('('), isChar(')'))
          .followedBy(Scanners.ANY_CHAR.skipMany()).parse("(hello)and the rest", mode));
		assertEquals("hello",
		    Scanners.IDENTIFIER.many().source().reluctantBetween(isChar('('), isChar(')')).parse("(hello)", mode));
		assertEquals("hello",
		    Scanners.IDENTIFIER.many().source().reluctantBetween(isChar('('), isChar(')').optional()).parse("(hello", mode));
		assertEquals("",
		    Scanners.IDENTIFIER.many().source().reluctantBetween(isChar('('), isChar(')')).parse("()", mode));
	}

  @Test
	public void parsing_incorrect_input() {
		Asserts.assertFailure(mode,
		    Scanners.IDENTIFIER.many().source().reluctantBetween(isChar('('), isChar(')')),
				"(hello", 1,7);
	}
}
