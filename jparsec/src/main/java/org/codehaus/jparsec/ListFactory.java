package org.codehaus.jparsec;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jparsec.internal.util.Lists;

/**
 * Creates a {link List}.
 * 
 * @author Ben Yu
 */
abstract class ListFactory<T> {
  
  /** Creates a new list. */
  abstract List<T> newList();
  
  /** Returns a {@link ListFactory} that creates an empty {@link ArrayList}. */
  @SuppressWarnings("unchecked")
  static <T> ListFactory<T> arrayListFactory() {
    return ARRAY_LIST_FACTORY;
  }
  
  /**
   * Returns a {@link ListFactory} that creates an {@link ArrayList} instance
   * with {@code first} as the first element.
   */
  static <T> ListFactory<T> arrayListFactoryWithFirstElement(final T first) {
    return new ListFactory<T>() {
      @Override List<T> newList() {
        ArrayList<T> list = Lists.arrayList();
        list.add(first);
        return list;
      }
    };
  }
  @SuppressWarnings("rawtypes")
  private static final ListFactory ARRAY_LIST_FACTORY = new ListFactory<Object>() {
    @Override List<Object> newList() {
      return Lists.arrayList();
    }
  };
}
