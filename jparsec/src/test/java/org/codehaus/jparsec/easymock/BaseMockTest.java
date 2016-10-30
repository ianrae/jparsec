package org.codehaus.jparsec.easymock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import org.junit.After;
import org.junit.Before;

/**
 * Provides convenient API for using EasyMock.
 * 
 * @author Ben Yu
 */
public abstract class BaseMockTest {

  /** Annotates a field as being mocked. */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  protected @interface Mock {}

  private static List<Field> getMockFields(Class<?> type) {
    List<Field> fields = new ArrayList<Field>();
    for (Field field : type.getDeclaredFields()) {
      if (field.isAnnotationPresent(Mock.class)) {
        fields.add(field);
      }
    }
    AccessibleObject.setAccessible(fields.toArray(new Field[fields.size()]), true);
    Class<?> superclass = type.getSuperclass();
    if (superclass != null) {
      fields.addAll(getMockFields(superclass));
    }
    fields = Collections.unmodifiableList(fields);
    return fields;
  }
  
  private IMocksControl control;
  private boolean replayed;

  @Before
  public void setUp() throws Exception {
    replayed = false;
    control = EasyMock.createControl();
    for (Field field : getMockFields(getClass())) {
      field.set(this, mock(field.getType()));
    }
  }
  
  @After
  public void tearDown() throws Exception {
    if (replayed) {
      control.verify();
    }
  }

  /** Returns a mock of {@code type}. */
  protected final <T> T mock(Class<T> type) {
    return control.createMock(type);
  }

  protected final void replay() {
    control.replay();
    replayed = true;
  }

}
