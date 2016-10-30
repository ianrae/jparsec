/*-
 * #%L
 * jParsec
 * %%
 * Copyright (C) 2013 - 2016 jparsec
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.codehaus.jparsec.internal.util.Objects;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for {@link Objects}.
 * 
 * @author Ben Yu
 */
public class ObjectsTest {

  @Test
  public void testEquals() {
    assertTrue(Objects.equals(null, null));
    assertFalse(Objects.equals(null, ""));
    assertFalse(Objects.equals("", null));
    assertTrue(Objects.equals("", ""));
  }

  @Test
  public void testHashCode() {
    assertEquals(0, Objects.hashCode(null));
    assertEquals("".hashCode(), Objects.hashCode(""));
  }

  @Test
  public void testIn() {
    assertTrue(Objects.in("b", "a", "b", "c"));
    assertFalse(Objects.in("x", "a", "b", "c"));
  }

}
