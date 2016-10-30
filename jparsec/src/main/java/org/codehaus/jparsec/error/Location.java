package org.codehaus.jparsec.error;

/**
 * Represents a line and column number of a character or token in the source.
 * 
 * @author Ben Yu
 */
public final class Location {
  
  /** 1-based line number. */
  public final int line;
  
  /** 1-based column number. */
  public final int column;
  
  /**
   * Creates a {@link Location} instance.
   * 
   * @param line line number
   * @param column column number
   */
  public Location(int line, int column) {
    this.line = line;
    this.column = column;
  }
  
  @Override public boolean equals(Object obj) {
    if (obj instanceof Location) {
      Location other = (Location) obj;
      return line == other.line && column == other.column;
    }
    return false;
  }

  @Override public int hashCode() {
    return line * 31 + column;
  }

  @Override public String toString() {
    return "line " + line + " column "+column;
  }
}
