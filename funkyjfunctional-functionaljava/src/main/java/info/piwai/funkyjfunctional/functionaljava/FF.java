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

package info.piwai.funkyjfunctional.functionaljava;

import info.piwai.funkyjfunctional.Funky;
import fj.F;

/**
 * A Funky {@link F}
 * 
 * @see FunkyFunctionalJava FunkyFunctionalJava documentation
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class FF<A, B> {

    protected final A in;

    protected B out;

    public FF() {
        in = Funky.<A> getInput();
    }
}
