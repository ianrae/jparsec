package org.codehaus.jparsec.internal.util;

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

import org.codehaus.jparsec.internal.util.Checks;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for {@link Checks}.
 * 
 * @author Ben Yu
 */
public class ChecksTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void checkArgument_noThrowIfConditionIsTrue() {
    Checks.checkArgument(true, "whatever");
    Checks.checkArgument(true, "whatever", 1, 2);
    Checks.checkArgument(true, "bad format %s and %s", 1);
  }

  @Test
  public void checkArgument_throwsIfConditionIsFalse() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("one = 1");
    Checks.checkArgument(false, "one = %s", 1);
  }

  @Test
  public void checkState_noThrowIfConditionIsTrue() {
    Checks.checkState(true, "whatever");
    Checks.checkState(true, "whatever", 1, 2);
    Checks.checkState(true, "bad format %s and %s", 1);
  }

  @Test
  public void checkState_throwsIfConditionIsFalse() {
    exception.expect(IllegalStateException.class);
    exception.expectMessage("one = 1");
    Checks.checkState(false, "one = %s", 1);
  }

  @Test
  public void checkNotNullState_noThrowIfObjectIsntNull() {
    Checks.checkNotNullState("1", "whatever");
    Checks.checkNotNullState("1", "whatever", 1, 2);
    Checks.checkNotNullState("1", "bad format %s and %s", 1);
  }

  @Test
  public void checkNotNullState_throwsIfObjectIsNull() {
    exception.expect(IllegalStateException.class);
    exception.expectMessage("object = null");
    Checks.checkNotNullState(null, "object = %s", "null");
  }

}
