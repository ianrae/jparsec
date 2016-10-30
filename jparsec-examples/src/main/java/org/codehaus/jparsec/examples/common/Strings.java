package org.codehaus.jparsec.examples.common;

import java.util.Arrays;

/**
 * Manipulates String.
 * 
 * @author Ben Yu
 */
public final class Strings {
  
  /** Prepends {@code delim} before each object of {@code objects}. */
  public static String prependEach(String delim, Iterable<?> objects) {
    StringBuilder builder = new StringBuilder();
    for (Object obj : objects) {
      builder.append(delim);
      builder.append(obj);
    }
    return builder.toString();
  }
  
  /** Joins {@code objects} with {@code delim} as the delimiter. */
  public static String join(String delim, Iterable<?> objects) {
    return join(new StringBuilder(), delim, objects).toString();
  }
  
  /** Joins {@code objects} with {@code delim} as the delimiter. */
  public static String join(String delim, Object... objects) {
    return join(delim, Arrays.asList(objects));
  }

  /** Joins {@code objects} with {@code delim} as the delimiter. */
  public static StringBuilder join(StringBuilder builder, String delim, Iterable<?> objects) {
    int i = 0;
    for (Object obj : objects) {
      if (i++ > 0) builder.append(delim);
      builder.append(obj);
    }
    return builder;
  }

  /** Joins {@code objects} with {@code delim} as the delimiter. */
  public static StringBuilder join(StringBuilder builder, String delim, Object... objects) {
    return join(builder, delim, Arrays.asList(objects));
  }
}
