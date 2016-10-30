package org.codehaus.jparsec.error;

import org.codehaus.jparsec.util.ObjectTester;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link Location}.
 * 
 * @author Ben Yu
 */
public class LocationTest {

  @Test
  public void testToString() {
    assertEquals("line 1 column 2", new Location(1, 2).toString());
  }

  @Test
  public void testEquals() {
    ObjectTester.assertEqual(new Location(1, 2), new Location(1, 2));
    ObjectTester.assertNotEqual(new Location(1, 2), new Location(2, 2), new Location(1, 1));
  }

}
