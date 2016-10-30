package org.codehaus.jparsec;


/**
 * Maps a {@link Token} to a an object of type {@code T}, or null if the token isn't recognized.
 * 
 * @author Ben Yu
 */
public interface TokenMap<T> {
  
  /**
   * Transforms {@code token} to an instance of {@code T}.
   * {@code null} is returned if the token isn't recognized.
   */
  T map(Token token);
}
