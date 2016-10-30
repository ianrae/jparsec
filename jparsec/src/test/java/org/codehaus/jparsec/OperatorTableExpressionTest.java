package org.codehaus.jparsec;

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

import org.codehaus.jparsec.easymock.BaseMockTest;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map2;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Unit test for {@link OperatorTable} for building expression parsers.
 * 
 * @author Ben Yu
 */
public class OperatorTableExpressionTest extends BaseMockTest {

  // Tests against a sample operator precedence grammar, with {@code +, -, *} as infix
  // left-associative operators, {@code ^} as right-associative operator, {@code ~} as prefix
  // operator, {@code %} as postfix operator, and {@code .} as infix non-associative operator.
  
  @Mock Map<String, String> negate;
  @Mock Map2<String, String, String> plus;
  @Mock Map2<String, String, String> subtract;
  @Mock Map2<String, String, String> multiply;
  @Mock Map<String, String> percent;
  @Mock Map2<String, String, String> point;
  @Mock Map2<String, String, String> power;

  @Test
  public void testBuildExpressionParser() {
    String source = "1+2.3-30%-1+~5*20000%%^2^1*~~3";
    expect(point.map("2", "3")).andReturn("2.3");
    expect(plus.map("1", "2.3")).andReturn("3.3");
    expect(percent.map("30")).andReturn("0.3");
    expect(subtract.map("3.3", "0.3")).andReturn("3.0");
    expect(subtract.map("3.0", "1")).andReturn("2.0");
    expect(negate.map("5")).andReturn("-5");
    expect(percent.map("20000")).andReturn("200");
    expect(percent.map("200")).andReturn("2");
    expect(negate.map("3")).andReturn("-3");
    expect(negate.map("-3")).andReturn("3");
    expect(power.map("2", "1")).andReturn("2");
    expect(power.map("2", "2")).andReturn("4");
    expect(multiply.map("-5", "4")).andReturn("-20");
    expect(multiply.map("-20", "3")).andReturn("-60");
    expect(plus.map("2.0", "-60")).andReturn("-58.0");
    replay();
    assertEquals("-58.0", parser().parse(source));
  }

  @Test
  public void testEmptyOperatorTable() {
    Parser<String> operand = Parsers.constant("foo");
    assertSame(operand, new OperatorTable<String>().build(operand));
  }

  private Parser<String> parser() {
    return new OperatorTable<String>()
        .prefix(op("~", negate), 100)
        .postfix(op("%", percent), 80)
        .infixr(op("^", power), 40)
        .infixl(op("+", plus), 10)
        .infixl(op("-", subtract), 10)
        .infixl(op("*", multiply), 20)
        .infixn(op(".", point), 200)
        .build(Scanners.INTEGER.source());
  }
  
  private static <T> Parser<T> op(String name, T value) {
    return Scanners.string(name).retn(value);
  }
}
