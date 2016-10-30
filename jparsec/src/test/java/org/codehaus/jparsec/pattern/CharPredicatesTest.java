
import static org.codehaus.jparsec.pattern.CharPredicates.ALWAYS;
import static org.codehaus.jparsec.pattern.CharPredicates.NEVER;
import static org.codehaus.jparsec.pattern.CharPredicates.and;
import static org.codehaus.jparsec.pattern.CharPredicates.not;
import static org.codehaus.jparsec.pattern.CharPredicates.or;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for {@link CharPredicates}.
 * 
 * @author Ben Yu
 */
public class CharPredicatesTest {

  @Test
  public void testIsChar() {
    CharPredicate predicate = CharPredicates.isChar('a');
    assertTrue(predicate.isChar('a'));
    assertFalse(predicate.isChar('x'));
    assertEquals("a", predicate.toString());
  }

  @Test
  public void testNotChar() {
    CharPredicate predicate = CharPredicates.notChar('a');
    assertFalse(predicate.isChar('a'));
    assertTrue(predicate.isChar('x'));
    assertEquals("^a", predicate.toString());
  }

  @Test
  public void testRange() {
    CharPredicate predicate = CharPredicates.range('1', '3');
    assertTrue(predicate.isChar('1'));
    assertTrue(predicate.isChar('2'));
    assertTrue(predicate.isChar('3'));
    assertFalse(predicate.isChar('0'));
    assertFalse(predicate.isChar('4'));
    assertEquals("[1-3]", predicate.toString());
  }

  @Test
  public void testIsDigit() {
    CharPredicate predicate = CharPredicates.IS_DIGIT;
    assertTrue(predicate.isChar('0'));
    assertTrue(predicate.isChar('9'));
    assertFalse(predicate.isChar('a'));
    assertFalse(predicate.isChar(' '));
    assertEquals("[0-9]", predicate.toString());
  }

  @Test
  public void testNotRange() {
    CharPredicate predicate = CharPredicates.notRange('1', '3');
    assertFalse(predicate.isChar('1'));
    assertFalse(predicate.isChar('2'));
    assertFalse(predicate.isChar('3'));
    assertTrue(predicate.isChar('0'));
    assertTrue(predicate.isChar('4'));
    assertEquals("[^1-3]", predicate.toString());
  }

  @Test
  public void testAmong() {
    CharPredicate predicate = CharPredicates.among("a1");
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('1'));
    assertFalse(predicate.isChar(' '));
    assertEquals("[a1]", predicate.toString());
  }

  @Test
  public void testNotAmong() {
    CharPredicate predicate = CharPredicates.notAmong("a1");
    assertFalse(predicate.isChar('a'));
    assertFalse(predicate.isChar('1'));
    assertTrue(predicate.isChar(' '));
    assertEquals("^[a1]", predicate.toString());
  }

  @Test
  public void testIsHexDigit() {
    CharPredicate predicate = CharPredicates.IS_HEX_DIGIT;
    assertFalse(predicate.isChar('g'));
    assertFalse(predicate.isChar(' '));
    assertTrue(predicate.isChar('A'));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('F'));
    assertTrue(predicate.isChar('f'));
    assertTrue(predicate.isChar('0'));
    assertTrue(predicate.isChar('9'));
    assertTrue(predicate.isChar('E'));
    assertTrue(predicate.isChar('1'));
    assertEquals("[0-9a-fA-F]", predicate.toString());
  }

  @Test
  public void testIsUpperCase() {
    CharPredicate predicate = CharPredicates.IS_UPPER_CASE;
    assertFalse(predicate.isChar('a'));
    assertFalse(predicate.isChar('1'));
    assertFalse(predicate.isChar(' '));
    assertTrue(predicate.isChar('A'));
    assertTrue(predicate.isChar('Z'));
    assertEquals("uppercase", predicate.toString());
  }

  @Test
  public void testIsLowerCase() {
    CharPredicate predicate = CharPredicates.IS_LOWER_CASE;
    assertFalse(predicate.isChar('A'));
    assertFalse(predicate.isChar('1'));
    assertFalse(predicate.isChar(' '));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('z'));
    assertEquals("lowercase", predicate.toString());
  }

  @Test
  public void testIsWhitespace() {
    CharPredicate predicate = CharPredicates.IS_WHITESPACE;
    assertFalse(predicate.isChar('A'));
    assertFalse(predicate.isChar('1'));
    assertFalse(predicate.isChar('a'));
    assertTrue(predicate.isChar(' '));
    assertTrue(predicate.isChar('\t'));
    assertTrue(predicate.isChar('\n'));
    assertEquals("whitespace", predicate.toString());
  }

  @Test
  public void testIsAlpha() {
    CharPredicate predicate = CharPredicates.IS_ALPHA;
    assertFalse(predicate.isChar('-'));
    assertFalse(predicate.isChar('1'));
    assertFalse(predicate.isChar('_'));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('Z'));
    assertEquals("[a-zA-Z]", predicate.toString());
  }

  @Test
  public void testIsAlpha_() {
    CharPredicate predicate = CharPredicates.IS_ALPHA_;
    assertFalse(predicate.isChar('-'));
    assertFalse(predicate.isChar('1'));
    assertTrue(predicate.isChar('_'));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('Z'));
    assertEquals("[a-zA-Z_]", predicate.toString());
  }

  @Test
  public void testIsAlphaNumeric() {
    CharPredicate predicate = CharPredicates.IS_ALPHA_NUMERIC;
    assertFalse(predicate.isChar('-'));
    assertFalse(predicate.isChar('_'));
    assertTrue(predicate.isChar('1'));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('Z'));
    assertEquals("[0-9a-zA-Z]", predicate.toString());
  }

  @Test
  public void testIsAlphaNumeric_() {
    CharPredicate predicate = CharPredicates.IS_ALPHA_NUMERIC_;
    assertFalse(predicate.isChar('-'));
    assertTrue(predicate.isChar('1'));
    assertTrue(predicate.isChar('_'));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('Z'));
    assertEquals("[0-9a-zA-Z_]", predicate.toString());
  }

  @Test
  public void testIsLetter() {
    CharPredicate predicate = CharPredicates.IS_LETTER;
    assertFalse(predicate.isChar('-'));
    assertFalse(predicate.isChar('1'));
    assertFalse(predicate.isChar('_'));
    assertTrue(predicate.isChar('a'));
    assertTrue(predicate.isChar('Z'));
    assertEquals("letter", predicate.toString());
  }

  @Test
  public void testAlways() {
    assertTrue(ALWAYS.isChar('a'));
    assertTrue(ALWAYS.isChar('>'));
    assertTrue(ALWAYS.isChar('0'));
    assertEquals("any character", ALWAYS.toString());
  }

  @Test
  public void testNever() {
    assertFalse(NEVER.isChar('a'));
    assertFalse(NEVER.isChar('>'));
    assertFalse(NEVER.isChar('0'));
    assertEquals("none", NEVER.toString());
  }

  @Test
  public void testNot() {
    assertFalse(not(ALWAYS).isChar('a'));
    assertTrue(not(NEVER).isChar('a'));
    assertEquals("^any character", not(ALWAYS).toString());
  }

  @Test
  public void testAnd() {
    assertSame(ALWAYS, and());
    assertSame(CharPredicates.IS_ALPHA, and(CharPredicates.IS_ALPHA));
    assertFalse(and(ALWAYS, NEVER).isChar('a'));
    assertFalse(and(NEVER, ALWAYS).isChar('a'));
    assertFalse(and(NEVER, NEVER).isChar('a'));
    assertTrue(and(ALWAYS, ALWAYS).isChar('a'));
    assertFalse(and(ALWAYS, NEVER, ALWAYS).isChar('a'));
    assertFalse(and(NEVER, ALWAYS, ALWAYS).isChar('a'));
    assertFalse(and(NEVER, NEVER, NEVER).isChar('a'));
    assertTrue(and(ALWAYS, ALWAYS, ALWAYS).isChar('a'));
    assertEquals("any character and none", and(ALWAYS, NEVER).toString());
    assertEquals("any character and none and any character", and(ALWAYS, NEVER, ALWAYS).toString());
  }

  @Test
  public void testOr() {
    assertSame(NEVER, or());
    assertSame(CharPredicates.IS_ALPHA, or(CharPredicates.IS_ALPHA));
    assertTrue(or(ALWAYS, NEVER).isChar('a'));
    assertTrue(or(NEVER, ALWAYS).isChar('a'));
    assertTrue(or(ALWAYS, ALWAYS).isChar('a'));
    assertFalse(or(NEVER, NEVER).isChar('a'));
    assertTrue(or(ALWAYS, NEVER, NEVER).isChar('a'));
    assertTrue(or(NEVER, NEVER, ALWAYS).isChar('a'));
    assertTrue(or(ALWAYS, ALWAYS, ALWAYS).isChar('a'));
    assertFalse(or(NEVER, NEVER, NEVER).isChar('a'));
    assertEquals("any character or none", or(ALWAYS, NEVER).toString());
    assertEquals("any character or none or any character", or(ALWAYS, NEVER, ALWAYS).toString());
  }
}
