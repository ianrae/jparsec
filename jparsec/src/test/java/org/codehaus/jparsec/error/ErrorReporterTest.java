
import static org.easymock.EasyMock.expect;

import java.util.Arrays;

import org.codehaus.jparsec.easymock.BaseMockTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link ErrorReporter}.
 * 
 * @author Ben Yu
 */
public class ErrorReporterTest extends BaseMockTest {
  
  @Mock ParseErrorDetails error;

  @Test
  public void testToString_null() {
    assertEquals("", ErrorReporter.toString(null, null));
  }

  @Test
  public void testToString_nullError() {
    assertEquals("line 3, column 5", ErrorReporter.toString(null, new Location(3, 5)));
  }

  @Test
  public void testToString_failure() {
    expect(error.getFailureMessage()).andReturn("failure").atLeastOnce();
    replay();
    assertEquals("line 3, column 5:\nfailure", ErrorReporter.toString(error, new Location(3, 5)));
  }

  @Test
  public void testToString_expected() {
    expect(error.getFailureMessage()).andReturn(null).atLeastOnce();
    expect(error.getExpected()).andReturn(Arrays.asList("foo", "bar")).atLeastOnce();
    expect(error.getEncountered()).andReturn("baz");
    replay();
    assertEquals("line 3, column 5:\nfoo or bar expected, baz encountered."
        , ErrorReporter.toString(error, new Location(3, 5)));
  }

  @Test
  public void testToString_unexpected() {
    expect(error.getFailureMessage()).andReturn(null).atLeastOnce();
    expect(error.getExpected()).andReturn(Arrays.<String>asList());
    expect(error.getUnexpected()).andReturn("foo").atLeastOnce();
    replay();
    assertEquals("line 3, column 5:\nunexpected foo."
        , ErrorReporter.toString(error, new Location(3, 5)));
  }

  @Test
  public void testReportList() {
    assertEquals("", reportList());
    assertEquals("foo", reportList("foo"));
    assertEquals("foo or bar", reportList("foo", "bar"));
    assertEquals("foo, bar or baz", reportList("foo", "bar", "baz"));
    assertEquals("foo, bar or baz", reportList("foo", "bar", "baz", "baz"));
    assertEquals("foo or bar", reportList("foo", "foo", "bar"));
  }
  
  private static String reportList(String... strings) {
    StringBuilder builder = new StringBuilder();
    ErrorReporter.reportList(builder, Arrays.asList(strings));
    return builder.toString();
  }
}
