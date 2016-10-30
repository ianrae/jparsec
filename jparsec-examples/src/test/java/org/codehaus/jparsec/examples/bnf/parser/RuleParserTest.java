package org.codehaus.jparsec.examples.bnf.parser;

import java.util.List;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.examples.bnf.ast.AltRule;
import org.codehaus.jparsec.examples.bnf.ast.LiteralRule;
import org.codehaus.jparsec.examples.bnf.ast.Rule;
import org.codehaus.jparsec.examples.bnf.ast.RuleDef;
import org.codehaus.jparsec.examples.bnf.ast.RuleReference;
import org.codehaus.jparsec.examples.bnf.ast.SequentialRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for {@link RuleParser}.
 * 
 * @author benyu
 */
public class RuleParserTest {

  @Test
  public void testLiteral() {
    assertParser(RuleParser.LITERAL, "'foo'", LiteralRule.class, "'foo'");
    assertParser(RuleParser.LITERAL, "\"bar\"", LiteralRule.class, "'bar'");
    assertParser(RuleParser.LITERAL, "'\"'", LiteralRule.class, "'\"'");
    assertParser(RuleParser.LITERAL, "\"'\"", LiteralRule.class, "'''");
  }

  @Test
  public void testIdent() {
    assertParser(RuleParser.IDENT, "foo", RuleReference.class, "foo");
  }

  @Test
  public void testRule() {
    Parser<Rule> parser = RuleParser.rule();
    assertParser(parser, "foo", RuleReference.class, "foo");
    assertParser(parser, "'foo'", LiteralRule.class, "'foo'");
    assertParser(parser, "foo bar", SequentialRule.class, "foo bar");
    assertParser(parser, "foo bar | baz |'foo'", AltRule.class, "(foo bar | baz | 'foo')");
    assertParser(parser, "foo bar | \n  baz |'foo'", AltRule.class, "(foo bar | (baz | 'foo'))");
    assertParser(parser, "foo bar  baz |'foo'", AltRule.class, "(foo bar baz | 'foo')");
    assertParser(parser, "foo bar \n  baz |'foo'", SequentialRule.class, "foo bar (baz | 'foo')");
    assertParser(parser, "foo bar (baz |'foo')", SequentialRule.class, "foo bar (baz | 'foo')");
    assertParser(parser, "foo \n| bar", AltRule.class, "(foo | bar)");
    assertParser(parser, "foo | \n  bar | baz \n| 'foo'",
        AltRule.class, "(foo | (bar | baz) | 'foo')");
  }

  @Test
  public void testRuleDef() {
    Parser<RuleDef> parser = RuleParser.RULE_DEF;
    assertParser(parser, "foo ::= bar | 'baz' \n  'a' | 'b'",
        RuleDef.class, "foo ::= (bar | 'baz' ('a' | 'b'))");
  }

  @Test
  public void testRuleDefs() {
    Parser<List<RuleDef>> parser = RuleParser.RULE_DEFS;
    assertParser(parser, "foo ::= bar \n baz | 'baz' \n\n #line comment \nbar::='bar'",
        List.class, "[foo ::= bar (baz | 'baz'), bar ::= 'bar']");
  }
  
  private static void assertParser(
      Parser<?> parser, String source, Class<?> expectedClass, String string) {
    Object result = TerminalParser.parse(parser, source);
    assertTrue(expectedClass.isInstance(result));
    assertEquals(string, result.toString());
  }
}
