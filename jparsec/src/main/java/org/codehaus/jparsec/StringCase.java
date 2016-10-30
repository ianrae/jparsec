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

import java.util.Comparator;
import java.util.Locale;

enum StringCase implements Comparator<String> {
  CASE_SENSITIVE {
    @Override public int compare(String a, String b) {
      return a.compareTo(b);
    }
    @Override String toKey(String k) {
      return k;
    }
  },
  CASE_INSENSITIVE {
    @Override public int compare(String a, String b) {
      return a.compareToIgnoreCase(b);
    }
    @Override public String toKey(String k) {
      return k.toLowerCase(Locale.ENGLISH);
    }
  }
  ;

  abstract String toKey(String k);

  final <T> org.codehaus.jparsec.functors.Map<String, T> toMap(
      final java.util.Map<String, T> m) {
    return new org.codehaus.jparsec.functors.Map<String,T>() {
      @Override public T map(String key) {
        return m.get(toKey(key));
      }
    };
  }
}
