package org.codehaus.jparsec.examples.sql.ast;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;


/**
 * Models the select statement.
 * 
 * @author Ben Yu
 */
public final class Select extends ValueObject implements Relation {
  public final boolean distinct;
  public final List<Projection> projections;
  public final List<Relation> from;
  public final Expression where;
  public final GroupBy groupBy;
  public final OrderBy orderBy;
  
  public Select(
      boolean distinct, List<Projection> projections, List<Relation> from, Expression where,
      GroupBy groupBy, OrderBy orderBy) {
    this.distinct = distinct;
    this.projections = Collections.unmodifiableList(projections);
    this.from = Collections.unmodifiableList(from);
    this.where = where;
    this.groupBy = groupBy;
    this.orderBy = orderBy;
  }
}
