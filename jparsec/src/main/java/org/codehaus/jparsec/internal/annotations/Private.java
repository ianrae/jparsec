package org.codehaus.jparsec.internal.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Target;

/**
 * Annotates anything that should be private but is allowing test cases to access it.
 * 
 * @author Ben Yu
 */
@Target({TYPE, CONSTRUCTOR, METHOD, FIELD})
public @interface Private {}
