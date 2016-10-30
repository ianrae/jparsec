package org.codehaus.jparsec.functors;

/**
 * Maps two objects of type {@code A} and {@code B} respectively to an object of type {@code T}.
 * 
 * @author Ben Yu
 */
public interface Map2<A, B, T> {
  
  /** Maps {@code a} and {@code b} to the target object. */
  T map(A a, B b);
}
