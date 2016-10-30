
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for {@link Reflection}.
 * 
 * @author Ben Yu
 */
public class ReflectionTest {

  @Test
  public void testGetClassName() {
    assertEquals(String.class.getName(), Reflection.getClassName("1"));
    assertEquals("null", Reflection.getClassName(null));
  }

  @Test
  public void testIsInstance() {
    assertFalse(Reflection.isInstance(int.class, null));
    assertFalse(Reflection.isInstance(Integer.class, null));
    assertFalse(Reflection.isInstance(Integer.class, "1"));
    assertTrue(Reflection.isInstance(String.class, "1"));
    assertTrue(Reflection.isInstance(int.class, 1));
  }

  @Test
  public void testIsAssignable() {
    assertFalse(Reflection.isAssignable(int.class, null));
    assertTrue(Reflection.isAssignable(Integer.class, null));
    assertFalse(Reflection.isAssignable(Integer.class, "1"));
    assertTrue(Reflection.isAssignable(String.class, "1"));
    assertTrue(Reflection.isAssignable(int.class, 1));
  }

  @Test
  public void testWrapperClass() {
    assertEquals(Byte.class, Reflection.wrapperClass(byte.class));
    assertEquals(Short.class, Reflection.wrapperClass(short.class));
    assertEquals(Integer.class, Reflection.wrapperClass(int.class));
    assertEquals(Long.class, Reflection.wrapperClass(long.class));
    assertEquals(Boolean.class, Reflection.wrapperClass(boolean.class));
    assertEquals(Character.class, Reflection.wrapperClass(char.class));
    assertEquals(Float.class, Reflection.wrapperClass(float.class));
    assertEquals(Double.class, Reflection.wrapperClass(double.class));
    assertEquals(Void.class, Reflection.wrapperClass(void.class));
    assertEquals(String.class, Reflection.wrapperClass(String.class));
  }

}
