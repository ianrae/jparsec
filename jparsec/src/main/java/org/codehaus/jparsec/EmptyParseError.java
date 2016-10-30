package org.codehaus.jparsec;

import java.util.Collections;
import java.util.List;

import org.codehaus.jparsec.error.ParseErrorDetails;

/**
 * Empty implementation of {@link ParseErrorDetails} for subclasses to override.
 * 
 * @author benyu
 */
class EmptyParseError implements ParseErrorDetails {
  
  private final int index;
  private final String encountered;
  
  EmptyParseError(int index, String encountered) {
    this.index = index;
    this.encountered = encountered;
  }
  
  @Override public final String getEncountered() {
    return encountered;
  }

  @Override public List<String> getExpected() {
    return Collections.emptyList();
  }

  @Override public String getFailureMessage() {
    return null;
  }

  @Override public final int getIndex() {
    return index;
  }

  @Override public String getUnexpected() {
    return null;
  }
}
