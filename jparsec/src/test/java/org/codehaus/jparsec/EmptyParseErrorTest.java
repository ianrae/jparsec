package org.codehaus.jparsec;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for {@link EmptyParseError}.
 * 
 * @author benyu
 */
public class EmptyParseErrorTest {

  @Test
  public void testEmptyParseError() {
    EmptyParseError error = new EmptyParseError(1, "foo");
    assertEquals(1, error.getIndex());
    assertEquals("foo", error.getEncountered());
    assertNull(error.getUnexpected());
    assertNull(error.getFailureMessage());
    assertEquals(0, error.getExpected().size());
  }

}
