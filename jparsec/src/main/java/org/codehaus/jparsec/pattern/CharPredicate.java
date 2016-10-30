package org.codehaus.jparsec.pattern;

/**
 * Evaluates a char to a boolean.
 *
 * @author Ben Yu
 */
public interface CharPredicate {
  
  /** Tests whether {@code c} satisfies the predicate. */
  boolean isChar(char c);
}
