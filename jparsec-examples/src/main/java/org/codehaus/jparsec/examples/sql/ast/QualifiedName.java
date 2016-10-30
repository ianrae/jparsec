package org.codehaus.jparsec.examples.sql.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * A qualified name like "a.b.c".
 * @author Ben Yu
 */
public final class QualifiedName extends ValueObject implements Iterable<String> {
  public final List<String> names;

  public QualifiedName(List<String> names) {
    this.names = Collections.unmodifiableList(names);
  }
  
  public static QualifiedName of(String... names) {
    return new QualifiedName(Arrays.asList(names));
  }

  @Override public Iterator<String> iterator() {
    return names.iterator();
  }
}
