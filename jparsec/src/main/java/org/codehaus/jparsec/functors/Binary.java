package org.codehaus.jparsec.functors;

/**
 * Represents a binary operation on the same type {@code T}.
 * 
 * <p> Implement this interface for binary operator instead of {@link Map2} for brevity.
 * 
 * @author Ben Yu
 */
public interface Binary<T> extends Map2<T, T, T> {}
