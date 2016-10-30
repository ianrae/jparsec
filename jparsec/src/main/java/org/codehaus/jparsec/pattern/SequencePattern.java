package org.codehaus.jparsec.pattern;

class SequencePattern extends Pattern {
  private final Pattern[] patterns;

  SequencePattern(Pattern... patterns) {
    this.patterns = patterns;
  }

  @Override public int match(final CharSequence src, final int begin, final int end) {
    int current = begin;
    for (Pattern pattern : patterns) {
      int l = pattern.match(src, current, end);
      if (l == Pattern.MISMATCH) return l;
      current += l;
    }
    return current - begin;
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Pattern pattern : patterns) {
      sb.append(pattern);
    }
    return sb.toString();
  }
}
