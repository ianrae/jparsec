package org.codehaus.jparsec.pattern;

class OptionalPattern extends Pattern {
  private final Pattern pattern;

  OptionalPattern(Pattern pattern) {
    this.pattern = pattern;
  }

  @Override public int match(CharSequence src, int begin, int end) {
    int l = pattern.match(src, begin, end);
    return (l == Pattern.MISMATCH) ? 0 : l;
  }

  @Override public String toString() {
    return pattern + "?";
  }
}
