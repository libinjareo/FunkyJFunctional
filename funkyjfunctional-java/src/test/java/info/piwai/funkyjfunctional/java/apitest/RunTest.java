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
package info.piwai.funkyjfunctional.java.apitest;

import static info.piwai.funkyjfunctional.java.FunkyJava.withRun;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class RunTest {

    static int counter;

    @Before
    public void setup() {
        counter = 0;
    }

    @Test
    public void run() {
        // @off
        class Increment {{ counter++; }}
        // @on

        Runnable runnable = withRun(Increment.class);

        assertEquals(0, counter);
        runnable.run();
        assertEquals(1, counter);
    }

}
