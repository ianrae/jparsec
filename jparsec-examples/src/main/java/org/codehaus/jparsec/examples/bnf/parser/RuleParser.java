package org.codehaus.jparsec.examples.bnf.parser;

import static org.codehaus.jparsec.examples.bnf.parser.TerminalParser.INDENTATION;
import static org.codehaus.jparsec.examples.bnf.parser.TerminalParser.term;

import java.util.List;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.examples.bnf.ast.AltRule;
import org.codehaus.jparsec.examples.bnf.ast.LiteralRule;
import org.codehaus.jparsec.examples.bnf.ast.Rule;
import org.codehaus.jparsec.examples.bnf.ast.RuleDef;
import org.codehaus.jparsec.examples.bnf.ast.RuleReference;
import org.codehaus.jparsec.examples.bnf.ast.SequentialRule;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.misc.Mapper;

/**
 * Parser for bnf rules.
 * 
 * @author benyu
 */
public final class RuleParser {
  
  static final Parser<Rule> LITERAL =
      curry(LiteralRule.class).sequence(Terminals.StringLiteral.PARSER);
  
  static final Parser<Rule> IDENT = curry(RuleReference.class).sequence(
          Terminals.Identifier.PARSER.notFollowedBy(term("::=")));
  
  static Parser<RuleDef> RULE_DEF = Mapper.curry(RuleDef.class)
      .sequence(Terminals.Identifier.PARSER, term("::="), rule());
  
  public static Parser<List<RuleDef>> RULE_DEFS = RULE_DEF.many();
  
  static Parser<Rule> rule() {
    Parser.Reference<Rule> ref = Parser.newReference();
    Parser<Rule> atom = Parsers.or(LITERAL, IDENT, unit(ref.lazy()));
    Parser<Rule> parser = alternative(sequential(atom));
    ref.set(parser);
    return parser;
  }

  static Parser<Rule> unit(Parser<Rule> rule) {
    return Parsers.or(
        rule.between(term("("), term(")")),
        rule.between(INDENTATION.indent(), INDENTATION.outdent()));
  }
  
  static Parser<Rule> sequential(Parser<Rule> rule) {
    return rule.many1().map(new Map<List<Rule>, Rule>() {
      @Override public Rule map(List<Rule> list) {
        return list.size() == 1 ? list.get(0) : new SequentialRule(list);
      }
    });
  }
  
  static Parser<Rule> alternative(Parser<Rule> rule) {
    return rule.sepBy1(term("|")).map(new Map<List<Rule>, Rule>() {
      @Override public Rule map(List<Rule> list) {
        return list.size() == 1 ? list.get(0) : new AltRule(list);
      }
    });
  }
  
  private static Mapper<Rule> curry(Class<? extends Rule> ruleClass, Object... curryArgs) {
    return Mapper.curry(ruleClass, curryArgs);
  }
}
