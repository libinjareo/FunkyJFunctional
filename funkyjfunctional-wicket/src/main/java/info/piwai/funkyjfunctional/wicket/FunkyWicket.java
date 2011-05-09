/**
 * Copyright (C) 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional.wicket;

import static info.piwai.funkyjfunctional.Funky.classExecutor;
import info.piwai.funkyjfunctional.wicket.ARON.ClassAbstractReadOnlyModel;
import info.piwai.funkyjfunctional.wicket.LDM.ClassLoadableDetachableModel;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class FunkyWicket {


    public static <T, U extends LDM<T>> LoadableDetachableModel<T> withLDM(Class<U> applyingClass) {
        return new ClassLoadableDetachableModel<T, U>(classExecutor(applyingClass));
    }

    public static <T, U extends LDM<T>> LoadableDetachableModel<T> withLDM(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassLoadableDetachableModel<T, U>(classExecutor(applyingClass, constructorParameters));
    }
    
    public static <T, U extends ARON<T>> AbstractReadOnlyModel<T> withARON(Class<U> applyingClass) {
        return new ClassAbstractReadOnlyModel<T, U>(classExecutor(applyingClass));
    }

    public static <T, U extends ARON<T>> AbstractReadOnlyModel<T> withARON(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassAbstractReadOnlyModel<T, U>(classExecutor(applyingClass, constructorParameters));
    }

    /**
     * The constructor is private to make sure this class won't be instantiated.
     * The class itself is abstract so that it even cannot be instantiated using
     * the reflection API.
     */
    private FunkyWicket() {
    }

}