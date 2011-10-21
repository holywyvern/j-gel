/*
Copyright 2006 Jerry Huxtable

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.jhlabs.math;

// TODO: Auto-generated Javadoc
/**
 * The Class MathFunction1D.
 */
public class MathFunction1D implements Function1D {

	/** The Constant SIN. */
	public final static int SIN = 1;
	
	/** The Constant COS. */
	public final static int COS = 2;
	
	/** The Constant TAN. */
	public final static int TAN = 3;
	
	/** The Constant SQRT. */
	public final static int SQRT = 4;
	
	/** The Constant ASIN. */
	public final static int ASIN = -1;
	
	/** The Constant ACOS. */
	public final static int ACOS = -2;
	
	/** The Constant ATAN. */
	public final static int ATAN = -3;
	
	/** The Constant SQR. */
	public final static int SQR = -4;

	/** The operation. */
	private int operation;
	
	/**
	 * Instantiates a new math function1 d.
	 * 
	 * @param operation
	 *            the operation
	 */
	public MathFunction1D(int operation) {
		this.operation = operation;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.math.Function1D#evaluate(float)
	 */
	public float evaluate(float v) {
		switch (operation) {
		case SIN:
			return (float)Math.sin(v);
		case COS:
			return (float)Math.cos(v);
		case TAN:
			return (float)Math.tan(v);
		case SQRT:
			return (float)Math.sqrt(v);
		case ASIN:
			return (float)Math.asin(v);
		case ACOS:
			return (float)Math.acos(v);
		case ATAN:
			return (float)Math.atan(v);
		case SQR:
			return v*v;
		}
		return v;
	}
}

