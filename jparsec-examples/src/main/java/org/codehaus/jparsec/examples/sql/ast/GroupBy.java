package org.codehaus.jparsec.examples.sql.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * "group by" clause.
 * 
 * @author Ben Yu
 */
public class GroupBy extends ValueObject {
  public final List<Expression> by;
  public final Expression having;
  
  public GroupBy(List<Expression> by, Expression having) {
    this.by = Collections.unmodifiableList(by);
    this.having = having;
  }
}
