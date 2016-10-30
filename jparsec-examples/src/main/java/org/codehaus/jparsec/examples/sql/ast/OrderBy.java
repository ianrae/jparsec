package org.codehaus.jparsec.examples.sql.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * The "order by" clause.
 * 
 * @author Ben Yu
 */
public final class OrderBy extends ValueObject {
  
  public static final class Item extends ValueObject {
    public final Expression by;
    public final boolean ascending;
    
    public Item(Expression by, boolean ascending) {
      this.by = by;
      this.ascending = ascending;
    }
  }
  
  public final List<Item> items;
  
  public OrderBy(List<Item> items) {
    this.items = Collections.unmodifiableList(items);
  }
}
