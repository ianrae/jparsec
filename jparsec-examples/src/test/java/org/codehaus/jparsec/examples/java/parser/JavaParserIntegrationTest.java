package org.codehaus.jparsec.examples.java.parser;

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

import java.io.IOException;
import java.net.URL;

import org.codehaus.jparsec.examples.java.ast.declaration.AnnotationDef;
import org.codehaus.jparsec.examples.java.ast.declaration.ClassDef;
import org.codehaus.jparsec.examples.java.ast.declaration.ConstructorDef;
import org.codehaus.jparsec.examples.java.ast.declaration.Declaration;
import org.codehaus.jparsec.examples.java.ast.declaration.FieldDef;
import org.codehaus.jparsec.examples.java.ast.declaration.InterfaceDef;
import org.codehaus.jparsec.examples.java.ast.declaration.Member;
import org.codehaus.jparsec.examples.java.ast.declaration.MethodDef;
import org.codehaus.jparsec.examples.java.ast.declaration.Program;
import org.codehaus.jparsec.examples.java.ast.expression.Expression;
import org.codehaus.jparsec.examples.java.ast.expression.NewArrayExpression;
import org.codehaus.jparsec.examples.java.ast.expression.NewExpression;
import org.codehaus.jparsec.examples.java.ast.expression.Operator;
import org.codehaus.jparsec.examples.java.ast.expression.QualifiedExpression;
import org.codehaus.jparsec.examples.java.ast.statement.Annotation;
import org.codehaus.jparsec.examples.java.ast.statement.Modifier;
import org.codehaus.jparsec.examples.sql.ast.FunctionExpression;

import org.junit.Test;

/**
 * Integration test for the entire java parser.
 * 
 * @author benyu
 */
public class JavaParserIntegrationTest {

  @Test
  public void testParse() throws Exception {
    parseJavaSourceFiles(
        DeclarationParser.class, StatementParser.class, ExpressionParser.class,
        TerminalParser.class, JavaLexer.class, TypeLiteralParser.class,
        Program.class, Declaration.class, Member.class, Modifier.class,
        FieldDef.class, MethodDef.class, ConstructorDef.class,
        AnnotationDef.class, InterfaceDef.class, ClassDef.class,
        Expression.class, NewArrayExpression.class, QualifiedExpression.class,
        Operator.class, NewExpression.class, FunctionExpression.class, Annotation.class);
  }

  private static void parseJavaSourceFiles(Class<?>...classes) throws IOException {
    for (Class<?> cls : classes) {
      parseJavaSourceFile(cls);
    }
  }
  private static void parseJavaSourceFile(Class<?> cls) throws IOException {
    DeclarationParser.parse(toSourceUrl(cls));
  }
  
  private static URL toSourceUrl(Class<?> cls) {
    URL url = cls.getResource(cls.getSimpleName() + ".java");
    if (url == null) {
      throw new IllegalArgumentException("Cannot find source file for " + cls.getName());
    }
    return url;
  }
}
