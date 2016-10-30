package org.codehaus.jparsec.functors;

/**
 * Maps 5 objects of type {@code A}, {@code B}, {@code C}, {@code D} and {@code E} respectively
 * to an object of type {@code T}.
 * 
 * @author Ben Yu
 */
public interface Map5<A, B, C, D, E, T> {
  
  /** Maps {@code a}, {@code b}, {@code c}, {@code d} and {@code e} to the target object. */
  T map(A a, B b, C c, D d, E e);
}
