package org.codehaus.jparsec.examples.bnf.parser;

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
