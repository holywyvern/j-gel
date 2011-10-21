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

package com.jhlabs.vecmath;

// TODO: Auto-generated Javadoc
/**
 * Vector math package, converted to look similar to javax.vecmath.
 */
public class Vector4f extends Tuple4f {

	/**
	 * Instantiates a new vector4f.
	 */
	public Vector4f() {
		this( 0, 0, 0, 0 );
	}
	
	/**
	 * Instantiates a new vector4f.
	 * 
	 * @param x
	 *            the x
	 */
	public Vector4f( float[] x ) {
		this.x = x[0];
		this.y = x[1];
		this.z = x[2];
		this.w = x[2];
	}

	/**
	 * Instantiates a new vector4f.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param w
	 *            the w
	 */
	public Vector4f( float x, float y, float z, float w ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Instantiates a new vector4f.
	 * 
	 * @param t
	 *            the t
	 */
	public Vector4f( Vector4f t ) {
		x = t.x;
		y = t.y;
		z = t.z;
		w = t.w;
	}

	/**
	 * Instantiates a new vector4f.
	 * 
	 * @param t
	 *            the t
	 */
	public Vector4f( Tuple4f t ) {
		x = t.x;
		y = t.y;
		z = t.z;
		w = t.w;
	}

	/**
	 * Dot.
	 * 
	 * @param v
	 *            the v
	 * @return the float
	 */
	public float dot( Vector4f v ) {
		return v.x * x + v.y * y + v.z * z + v.w * w;
	}

	/**
	 * Length.
	 * 
	 * @return the float
	 */
	public float length() {
		return (float)Math.sqrt( x*x+y*y+z*z+w*w );
	}

	/**
	 * Normalize.
	 */
	public void normalize() {
		float d = 1.0f/( x*x+y*y+z*z+w*w );
		x *= d;
		y *= d;
		z *= d;
		w *= d;
	}

}
