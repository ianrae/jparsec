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

import static org.codehaus.jparsec.internal.util.Checks.checkArgument;

import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.internal.annotations.Private;
import org.codehaus.jparsec.internal.util.Strings;

/**
 * A {@link Lexicon} is a group of lexical words that can be tokenized by a single tokenizer.
 * 
 * @author Ben Yu
 */
class Lexicon {
  
  /** Maps lexical word name to token value. */
  final Map<String, Object> words;
  
  /** The scanner that recognizes any of the lexical word. */
  final Parser<?> tokenizer;
  
  Lexicon(Map<String, Object> words, Parser<?> tokenizer) {
    this.words = words;
    this.tokenizer = tokenizer;
  }
  
  /**
   * Returns the tokenizer that tokenizes all terminals (operators, keywords, identifiers etc.)
   * managed in this instance.
   */
  public Parser<?> tokenizer() {
    return tokenizer;
  }
  
  /**
   * A {@link Parser} that recognizes a sequence of tokens identified by {@code tokenNames}, as an
   * atomic step.
   */
  public Parser<?> phrase(String... tokenNames) {
    Parser<?>[] wordParsers = new Parser<?>[tokenNames.length];
    for (int i = 0; i < tokenNames.length; i++) {
      wordParsers[i] = token(tokenNames[i]);
    }
    String phrase = Strings.join(" ", tokenNames);
    return Parsers.sequence(wordParsers).atomic().retn(phrase).label(phrase);
  }
  
  /** A {@link Parser} that recognizes a token identified by any of {@code tokenNames}. */
  public Parser<Token> token(String... tokenNames) {
    if (tokenNames.length == 0) return Parsers.never();
    @SuppressWarnings("unchecked")
    Parser<Token>[] ps = new Parser[tokenNames.length];
    for(int i = 0; i < tokenNames.length; i++) {
      ps[i] = Parsers.token(InternalFunctors.tokenWithSameValue(word(tokenNames[i])));
    }
    return Parsers.or(ps);
  }
  
  /** A {@link Parser} that recognizes the token identified by {@code tokenName}. */
  public Parser<Token> token(String tokenName) {
    return Parsers.token(InternalFunctors.tokenWithSameValue(word(tokenName)));
  }

  /**
   * Gets the token value identified by the token text. This text is the operator or the keyword.
   * 
   * @param name the token text.
   * @return the token object. 
   * @exception IllegalArgumentException if the token object does not exist.
   */
  @Private Object word(String name) {
    Object p = words.map(name);
    checkArgument(p != null, "token %s unavailable", name);
    return p;
  }
  
  /** Returns a {@link Lexicon} instance that's a union of {@code this} and {@code that}. */
  Lexicon union(Lexicon that) {
    return new Lexicon(
        fallback(words, that.words),
        Parsers.or(tokenizer, that.tokenizer));
  }

  /**
   * Returns a {@link Map} that delegates to {@code map} and falls back to {@code defaultMap} for
   * null return values.
   */
  private static <F, T> Map<F, T> fallback(
      final Map<F, T> map, final Map<? super F, ? extends T> defaultMap) {
    return new Map<F, T>() {
      @Override public T map(F v) {
        T result = map.map(v);
        return (result == null) ? defaultMap.map(v) : result;
      }
      @Override public String toString() {
        return "fallback";
      }
    };
  }
}
