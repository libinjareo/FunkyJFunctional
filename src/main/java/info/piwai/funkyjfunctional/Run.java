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


/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class Run  {
    
    private static class ClassRunnable<U> extends FunkyExecutor<U> implements Runnable {

        public ClassRunnable(Class<U> applyingClass, Object instance) {
            super(applyingClass, instance);
        }

        @Override
        public void run() {
            call();
        }
    }
    
    public static Runnable with(Class<?> applyingClass) {
        return with(applyingClass, null);
    }

    public static <U> Runnable with(Class<U> applyingClass, Object instance) {
        return new ClassRunnable<U>(applyingClass, instance);
    }
}
