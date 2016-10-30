package org.codehaus.jparsec.misc;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Common utility for working with reflection.
 * 
 * @author Ben Yu
 */
final class Reflection {
  
  /** Gets the class name of {@code obj} or "null" if {@code obj} is null. */
  static String getClassName(Object obj) {
    return (obj == null) ? "null" : obj.getClass().getName();
  }
  
  @SuppressWarnings("rawtypes")
  private static final Map<Class, Class> WRAPPERS = new HashMap<Class, Class>();
  
  private static <T> void primitive(Class<T> primitive, Class<T> wrapper) {
    WRAPPERS.put(primitive, wrapper);
  }
  
  static {
    primitive(byte.class, Byte.class);
    primitive(short.class, Short.class);
    primitive(int.class, Integer.class);
    primitive(long.class, Long.class);
    primitive(boolean.class, Boolean.class);
    primitive(float.class, Float.class);
    primitive(double.class, Double.class);
    primitive(char.class, Character.class);
    primitive(void.class, Void.class);
  }
  
  static <T> Class<T> wrapperClass(Class<T> type) {
    @SuppressWarnings("unchecked")
    Class<T> wrapperClass = WRAPPERS.get(type);
    return (wrapperClass == null) ? type : wrapperClass;
  }
  
  static boolean isInstance(Class<?> type, Object obj) {
    return wrapperClass(type).isInstance(obj);
  }
  
  static boolean isAssignable(Class<?> type, Object obj) {
    return obj == null ? !type.isPrimitive() : isInstance(type, obj);
  }
}
