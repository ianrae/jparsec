package org.codehaus.jparsec.functors;

/**
 * Creates {@link Pair} and tuple instances.
 * 
 * <p> These data holders can be used to hold temporary results during parsing so you don't have to
 * create your own data types.
 * 
 * @author Ben Yu
 */
public final class Tuples {
  
  /** Returns a {@link Pair} of 2 objects. Is equivalent to {@link #tuple(Object, Object)}. */
  public static <A, B> Pair<A, B> pair(A a, B b) {
    return new Pair<A, B>(a, b);
  }
  
  /** Returns a {@link Pair} of 2 objects. Is equivalent to {@link #pair(Object, Object)}. */
  public static <A, B> Pair<A, B> tuple(A a, B b) {
    return pair(a, b);
  }
  
  /** Returns a {@link Tuple3} of 3 objects. */
  public static <A, B, C> Tuple3<A, B, C> tuple(A a, B b, C c) {
    return new Tuple3<A, B, C>(a, b, c);
  }
  
  /** Returns a {@link Tuple4} of 4 objects. */
  public static <A, B, C, D> Tuple4<A, B, C, D> tuple(A a, B b, C c, D d) {
    return new Tuple4<A, B, C, D>(a, b, c, d);
  }
  
  /** Returns a {@link Tuple5} of 5 objects. */
  public static <A, B, C, D, E> Tuple5<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
    return new Tuple5<A, B, C, D, E>(a, b, c, d, e);
  }
}
