package org.codehaus.jparsec.functors;

import org.codehaus.jparsec.internal.util.Objects;

/**
 * Immutable data holder for 3 values.
 * 
 * @author Ben Yu
 */
public class Tuple3<A, B, C> extends Pair<A, B> {
  
  public final C c;
  
  public Tuple3(A a, B b, C c) {
    super(a, b);
    this.c = c;
  }
  
  boolean equals(Tuple3<?, ?, ?> other) {
    return super.equals(other) && Objects.equals(c, other.c);
  }
  
  @Override public boolean equals(Object obj) {
    if (obj instanceof Tuple3<?, ?, ?>) {
      return equals((Tuple3<?, ?, ?>) obj);
    }
    return false;
  }
  
  @Override public int hashCode() {
    return super.hashCode() * 31 + Objects.hashCode(c);
  }
  
  @Override public String toString() {
    return "(" + a + ", " + b + ", " + c + ")";
  }
}
