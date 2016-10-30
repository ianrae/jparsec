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

import net.sf.cglib.reflect.FastConstructor;
import net.sf.cglib.reflect.FastMethod;

/**
 * Either a constructor or a method.
 * 
 * @author Ben Yu
 */
abstract class Invokable {
  abstract Class<?> returnType();
  abstract Class<?>[] parameterTypes();
  abstract Object invoke(Object[] args) throws Throwable;
  
  static Invokable constructor(final FastConstructor constructor) {
    final Class<?> ownerType = constructor.getDeclaringClass();
    final Class<?>[] parameterTypes = constructor.getParameterTypes();
    return new ValueInvokable(ownerType) {
      @Override Object invoke(Object[] args) throws Throwable {
        return constructor.newInstance(args);
      }
      @Override Class<?>[] parameterTypes() {
        return parameterTypes;
      }
      @Override Class<?> returnType() {
        return ownerType;
      }
    };
  }
  
  static Invokable method(final Object self, final FastMethod method) {
    final Class<?> returnType = method.getReturnType();
    final Class<?>[] parameterTypes = method.getParameterTypes();
    return new ValueInvokable(self) {
      @Override Object invoke(Object[] args) throws Throwable {
        return method.invoke(self, args);
      }
      @Override Class<?>[] parameterTypes() {
        return parameterTypes;
      }
      @Override Class<?> returnType() {
        return returnType;
      }
    };
  }
  
  private static abstract class ValueInvokable extends Invokable {
    private final Object value;

    ValueInvokable(Object value) {
      this.value = value;
    }

    @Override public int hashCode() {
      return value.hashCode();
    }

    @Override public boolean equals(Object obj) {
      if (obj instanceof ValueInvokable) {
        return value.equals(((ValueInvokable) obj).value);
      }
      return false;
    }
    
    @Override public String toString() {
      return value.toString();
    }
  }
}
