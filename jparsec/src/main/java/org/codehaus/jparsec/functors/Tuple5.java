package org.codehaus.jparsec.functors;

import org.codehaus.jparsec.internal.util.Objects;

/**
 * Immutable data holder for 5 values.
 * 
 * @author Ben Yu
 */
public class Tuple5<A, B, C, D, E> extends Tuple4<A, B, C, D>{
  
  public final E e;
  
  public Tuple5(A a, B b, C c, D d, E e) {
    super(a, b, c, d);
    this.e = e;
  }
  
  boolean equals(Tuple5<?, ?, ?, ?, ?> other) {
    return super.equals(other) && Objects.equals(e, other.e);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Tuple5<?, ?, ?, ?, ?>) {
      return equals((Tuple5<?, ?, ?, ?, ?>) obj);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return super.hashCode() * 31 + Objects.hashCode(e);
  }
  
  @Override
  public String toString() {
    return "(" + a + ", " + b + ", " + c + ", " + d + ", " + e + ")";
  }
}
