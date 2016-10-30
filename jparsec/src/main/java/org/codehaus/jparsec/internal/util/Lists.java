package org.codehaus.jparsec.internal.util;

import java.util.ArrayList;

/**
 * Internal utility to work with {@link java.util.List}.
 * 
 * @author Ben Yu
 */
public final class Lists {
  
  /** Returns a new {@link ArrayList}. */
  public static <T> ArrayList<T> arrayList() {
    return new ArrayList<T>();
  }
  
  /** Returns a new {@link ArrayList} with enough capacity to hold {@code expectedElements}. */
  public static <T> ArrayList<T> arrayList(int expectedElements) {
    return new ArrayList<T>(capacity(expectedElements));
  }
  
  private static int capacity(int expectedElements) {
    return (int) Math.min(5L + expectedElements + (expectedElements / 10), Integer.MAX_VALUE);
  }
}
