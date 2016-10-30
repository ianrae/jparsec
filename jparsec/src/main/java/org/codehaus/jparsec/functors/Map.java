package org.codehaus.jparsec.functors;

/**
 * Maps object of type {@code From} to an object of type {@code To}.
 * 
 * @author Ben Yu
 */
public interface Map<From, To> {
  
  /** Maps {@code from} to the target object. */
  To map(From from);
}
