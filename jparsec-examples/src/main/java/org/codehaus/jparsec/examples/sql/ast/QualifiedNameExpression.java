package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * An expression like "a.b.c".
 * 
 * @author Ben Yu
 */
public final class QualifiedNameExpression extends ValueObject implements Expression {
  public final QualifiedName qname;

  public QualifiedNameExpression(QualifiedName qname) {
    this.qname = qname;
  }
  
  public static QualifiedNameExpression of(String... names) {
    return new QualifiedNameExpression(QualifiedName.of(names));
  }
}
