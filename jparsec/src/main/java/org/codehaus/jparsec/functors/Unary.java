package org.codehaus.jparsec.functors;

/**
 * Represents a unary operation on the same type {@code T}.
 * 
 * <p> Implement this interface for unary operator instead of {@link Map} for brevity.
 * 
 * @author Ben Yu
 */
public interface Unary<T> extends Map<T, T> {}
