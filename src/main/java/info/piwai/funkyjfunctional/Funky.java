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

import info.piwai.funkyjfunctional.Call.ClassCallable;
import info.piwai.funkyjfunctional.Comp.ClassComparator;
import info.piwai.funkyjfunctional.Func.ClassFunction;
import info.piwai.funkyjfunctional.Pred.ClassPredicate;
import info.piwai.funkyjfunctional.Supp.ClassSupplier;

import java.util.Comparator;
import java.util.concurrent.Callable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * <p>
 * FunkyJFunctional integrates with <a
 * href="http://code.google.com/p/guava-libraries/">Guava</a> (the former google
 * collections), but also provides shortcuts for some standard Java interfaces
 * such as {@link Runnable} or {@link Callable}.
 * 
 * <p>
 * The {@link Funky} class is the main entry point to FunkyJFunctional.
 * Therefore, most of the documentation for this project will be found here.
 * 
 * <h1>Contents</h1>
 * 
 * <ul>
 * <li><a href="#1">1. Let's learn the funky way</a>
 * <li><a href="#2">2. Various Funky ways</a>
 * <li><a href="#3">3. A word of caution</a>
 * </ul>
 * 
 * <h2 id="1">1. Let's learn the funky way</h2>
 * 
 * <p>
 * Using FunkyJFunctional always involves two steps:
 * 
 * <ul>
 * <li>function declaration, as a method local class,
 * <li>function instantiation, using the {@link Funky} class.
 * </ul>
 * 
 * <h3>Function declaration</h3>
 * 
 * <p>
 * Let's declare an Adult predicate, that returns true if the given integer
 * parameter is greater than 18.
 * 
 * <pre>
 * // t is the input parameter, and r is the returned value.
 * class Adult extends Pred&lt;Integer&gt; {{ r = t &gt; 18; }}
 * </pre>
 * 
 * <h3>Function instantiation</h3>
 * 
 * <p>
 * To use this predicate, we need to instantiate it as a Guava {@link Predicate}
 * that we can use in our code.
 * 
 * <pre>
 * // Let's import Funky statically, as well as Guava's Iterables class, so that the code looks clearer
 * import static info.piwai.funkyjfunctional.Funky.*;
 * import static com.google.common.collect.Iterables.*;
 * 
 * // Nothing too complex here
 * List&lt;Integer&gt; values = Arrays.asList(16, 21);
 * 
 * // To use Guava's Iterables.filter() method, the withPred() method creates a Predicate from our Adult class.
 * Iterable&lt;Integer&gt; adults = filter(values, withPred(Adult.class));
 * 
 * // the 'adults' Iterable contains only one element: '21'.
 * assertEquals(Arrays.asList(21), adults);
 * 
 * // The magic all happened in the withPred method, which created a Predicate:
 * Predicate&lt;Integer&gt; adultPredicate = withPred(Adult.class);
 * </pre>
 * 
 * <h2 id="2">2. Various Funky ways</h2>
 * <p>
 * To know more about what you can do with FunkyJFunctional, have a look at the
 * various with*() methods of the {@link Funky} class.
 * 
 * <p>
 * Here is the list of the various funky ways currently provided by
 * FunkyJFunctional:
 * 
 * <ul>
 * <li>For funky {@link Function}s, see {@link Func} and the
 * {@link #withFunc(Class)} method.
 * <li>For funky {@link Predicate}s, see {@link Pred} and the
 * {@link #withPred(Class)} method.
 * <li>For funky {@link Comparator}s, see {@link Comp} and the
 * {@link #withComp(Class)} method.
 * <li>For funky {@link Runnable}s, see the {@link #withRun(Class)} method.
 * <li>For funky {@link Callable}s, see {@link Call} and the
 * {@link #withCall(Class)} method.
 * <li>For funky {@link Supplier}s, see {@link Supp} and the
 * {@link #withSupp(Class)} method.
 * </ul>
 * 
 * <h2 id="3">3. A word of caution</h2>
 * <p>
 * <b>Warning: this paragraph is not clear and should be rewritten</b>
 * 
 * <p>
 * You might notice that there are two withPred() methods: one that takes a
 * class, and another one that takes a class and an instance object. In most
 * cases, you will only need to use the first one. However, if the function
 * declaration is done in an instance method (ie non static) AND this function
 * accesses other instance methods or instance fields, then you should use the
 * withPred() method that has two parameters. The second parameter is the
 * instance on which the methods / fields will be called (ie, in most cases,
 * 'this'). Otherwise, you will raise {@link NullPointerException}s.
 * 
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 * @author Florent Rami&egrave;re
 */
public abstract class Funky {

    /**
     * The constructor is private to make sure this class won't be instantiated.
     * The class itself is abstract so that it even cannot be instantiated using
     * the reflection API.
     */
    private Funky() {
    }

    public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass) {
        return withComp(applyingClass, null);
    }

    public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass, Object instance) {
        return new ClassComparator<T, U>(applyingClass, instance);
    }

    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass) {
        return withFunc(applyingClass, null);
    }

    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass, Object instance) {
        return new ClassFunction<From, To, U>(applyingClass, instance);
    }

    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass) {
        return withPred(applyingClass, null);
    }

    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass, Object instance) {
        return new ClassPredicate<T, U>(applyingClass, instance);
    }

    public static Runnable withRun(Class<?> applyingClass) {
        return withRun(applyingClass, null);
    }

    public static <U> Runnable withRun(Class<U> applyingClass, Object instance) {
        return new ClassRunnable<U>(applyingClass, instance);
    }

    public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass) {
        return withCall(applyingClass, null);
    }

    public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass, Object instance) {
        return new ClassCallable<T, U>(applyingClass, instance);
    }

    public static <T, U extends Supp<T>> Supplier<T> withSupp(Class<U> applyingClass) {
        return withSupp(applyingClass, null);
    }

    public static <T, U extends Supp<T>> Supplier<T> withSupp(Class<U> applyingClass, Object instance) {
        return new ClassSupplier<T, U>(applyingClass, instance);
    }

}
