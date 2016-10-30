package org.codehaus.jparsec.examples.bnf.parser;

import org.codehaus.jparsec.Parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link TerminalParser}.
 * 
 * @author benyu
 */
public class TerminalParserTest {

  @Test
  public void testTokenizer() {
    Parser<?> tokenizer = TerminalParser.TOKENIZER;
    tokenizer.parse("*");
    tokenizer.parse("+");
    tokenizer.parse("?");
    assertEquals("foo", tokenizer.parse("'foo'"));
    assertEquals("foo", tokenizer.parse("\"foo\""));
    assertEquals("foo", tokenizer.parse("foo").toString());
  }

}
