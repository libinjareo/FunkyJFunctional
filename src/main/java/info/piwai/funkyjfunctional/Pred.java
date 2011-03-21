/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional;

import com.google.common.base.Predicate;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class Pred<T> {

    /**
     * <p>
     * {@link ClassPredicate} is not part of the API, which is why it has
     * package-private scope.
     */
    static class ClassPredicate<T, U extends Pred<T>> implements Predicate<T> {

        private final ClassExecutorWithInput<U> executor;

        ClassPredicate(ClassExecutorWithInput<U> executor) {
            this.executor = executor;
        }

        @Override
        public boolean apply(T input) {
            U instance = executor.createExecutedInstance(input);
            return instance.r;
        }
    }

    protected T t;

    protected boolean r;

    public Pred() {
        t = FunkyExecutorWithInput.<T> getThreadLocalParameter();
    }

}
