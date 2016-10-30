package org.codehaus.jparsec.examples.bnf.parser;

/*-
 * #%L
 * jParsec Examples
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

import org.codehaus.jparsec.Indentation;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.misc.Mapper;

/**
 * Parses terminals in a bnf.
 * 
 * @author benyu
 */
public final class TerminalParser {
  
  private static final String[] OPERATORS = {"*", "+", "?", "|", "::=", "(", ")"};
  private static final Terminals TERMS = Terminals.operators(OPERATORS);
  private static final Parser<Void> COMMENT = Scanners.lineComment("#");
  private static final Parser<String> LITERAL = Parsers.or(
      Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER,
      Terminals.StringLiteral.SINGLE_QUOTE_TOKENIZER);
  private static final Parser<?> IDENT = Terminals.Identifier.TOKENIZER;
  static final Parser<?> TOKENIZER = Parsers.<Object>or(TERMS.tokenizer(), LITERAL, IDENT);
  static final Indentation INDENTATION = new Indentation();
  
  static Parser<?> term(String name) {
    return Mapper.skip(TERMS.token(name));
  }
  
  static <T> T parse(Parser<T> parser, String source) {
    return parser.from(INDENTATION.lexer(TOKENIZER, Indentation.WHITESPACES.or(COMMENT).many()))
        .parse(source);
  }
}
