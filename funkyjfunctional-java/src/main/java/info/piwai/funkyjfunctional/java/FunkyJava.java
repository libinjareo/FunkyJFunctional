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
package info.piwai.funkyjfunctional.java;

import static info.piwai.funkyjfunctional.Funky.newFunction;
import static info.piwai.funkyjfunctional.Funky.newFunctionWithInput;
import info.piwai.funkyjfunctional.Funky;

import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.concurrent.Callable;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * Please see the {@link Funky} javadoc to learn how to use FunkyJFunctional.
 * 
 * <p>
 * This module is dedicated to FunkyJFunctional integration with the Java API.
 * 
 * <h1>Content</h1>
 * <ul>
 * <li>Java {@link Runnable} using {@link #withRun(Class, Object...)}
 * <li>Java {@link Comparator} with {@link Comp} and
 * {@link #withComp(Class, Object...)}
 * <li>Java {@link Callable} with {@link Call} and
 * {@link #withCall(Class, Object...)} *
 * <li>Swing {@link ActionListener} with {@link ActL} and
 * {@link #withActL(Class, Object...)}
 * </ul>
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyJava {

	/**
	 * <p>
	 * Creates a {@link Comparator} instance from a {@link Comp} class.
	 * 
	 * <p>
	 * If the init block of the applyingClass references an instance member or a
	 * local variable, you should give it the instance as the first
	 * constructorParameter.
	 * 
	 * <p>
	 * If the init block of the applyingClass does not reference an instance
	 * member or a local variable, you should not give any object to the
	 * constructorParameter vararg.
	 * 
	 * @param applyingClass
	 *            the local class that represents a {@link Comparator}.
	 * @param <T>
	 *            The type that is compared.
	 * @param <U>
	 *            The local class type. Must extend {@link Comp}.
	 * 
	 * @param constructorArguments
	 *            The constructor arguments to give to the created instance. The
	 *            first argument will usually be 'this' or 'null' if the
	 *            applyingClass has been declared in an instance method, and the
	 *            other arguments will be the local variables that you use from
	 *            the local class.
	 * @throws IllegalArgumentException
	 *             if the applyingClass does not provide a default constructor
	 *             <b>or</b> if the instance argument does not have the expected
	 *             type
	 * 
	 * @return A {@link Comparator} based on the applyingClass parameter.
	 */
	public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass, Object... constructorArguments) throws IllegalArgumentException {
		return new ClassComparator<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyJava FunkyJava documentation
	 */
	public static <U> Runnable withRun(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassRunnable<U>(newFunction(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyJava FunkyJava documentation
	 */
	public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassCallable<T, U>(newFunction(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyJava FunkyJava documentation
	 */
	public static <U extends ActL> ActionListener withActL(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassActionListener<U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	protected FunkyJava() {
	}

}
