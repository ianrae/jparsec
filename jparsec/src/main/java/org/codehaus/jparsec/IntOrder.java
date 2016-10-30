package org.codehaus.jparsec;

/**
 * Maps two integers to a boolean value.
 * 
 * @author Ben Yu
 */
enum IntOrder {
  
  /**
   * An {@link IntOrder} instance that determines if the first integer is less than the second one.
   * 
   * <p> {@code LT.compare(1, 2) == true}.
   */
  LT {
    @Override public boolean compare(int a, int b) { return a < b; }
    @Override public String toString() {
      return "shortest";
    }
  },
  
  /**
   * An {@link IntOrder} instance that determines if the first integer is smaller than the second
   * one.
   * 
   * <p> {@code GT.compare(2, 1) == true}.
   */
  GT {
    @Override public boolean compare(int a, int b) {return a > b;}
    @Override public String toString() {
      return "longest";
    }
  }

  ;
  /**
   * Compares two integers.
   * 
   * @param a 1st int
   * @param b 2nd int
   * @return the comparison result.
   */
  abstract boolean compare(int a, int b);
}
