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
public class Tuple4f {
	
	/** The w. */
	public float x, y, z, w;

	/**
	 * Instantiates a new tuple4f.
	 */
	public Tuple4f() {
		this( 0, 0, 0, 0 );
	}
	
	/**
	 * Instantiates a new tuple4f.
	 * 
	 * @param x
	 *            the x
	 */
	public Tuple4f( float[] x ) {
		this.x = x[0];
		this.y = x[1];
		this.z = x[2];
		this.w = x[2];
	}

	/**
	 * Instantiates a new tuple4f.
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
	public Tuple4f( float x, float y, float z, float w ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Instantiates a new tuple4f.
	 * 
	 * @param t
	 *            the t
	 */
	public Tuple4f( Tuple4f t ) {
		this.x = t.x;
		this.y = t.y;
		this.z = t.z;
		this.w = t.w;
	}

	/**
	 * Absolute.
	 */
	public void absolute() {
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
		w = Math.abs(w);
	}

	/**
	 * Absolute.
	 * 
	 * @param t
	 *            the t
	 */
	public void absolute( Tuple4f t ) {
		x = Math.abs(t.x);
		y = Math.abs(t.y);
		z = Math.abs(t.z);
		w = Math.abs(t.w);
	}

	/**
	 * Clamp.
	 * 
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 */
	public void clamp( float min, float max ) {
		if ( x < min )
			x = min;
		else if ( x > max )
			x = max;
		if ( y < min )
			y = min;
		else if ( y > max )
			y = max;
		if ( z < min )
			z = min;
		else if ( z > max )
			z = max;
		if ( w < min )
			w = min;
		else if ( w > max )
			w = max;
	}

	/**
	 * Sets the.
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
	public void set( float x, float y, float z, float w ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Sets the.
	 * 
	 * @param x
	 *            the x
	 */
	public void set( float[] x ) {
		this.x = x[0];
		this.y = x[1];
		this.z = x[2];
		this.w = x[2];
	}

	/**
	 * Sets the.
	 * 
	 * @param t
	 *            the t
	 */
	public void set( Tuple4f t ) {
		x = t.x;
		y = t.y;
		z = t.z;
		w = t.w;
	}

	/**
	 * Gets the.
	 * 
	 * @param t
	 *            the t
	 */
	public void get( Tuple4f t ) {
		t.x = x;
		t.y = y;
		t.z = z;
		t.w = w;
	}

	/**
	 * Gets the.
	 * 
	 * @param t
	 *            the t
	 */
	public void get( float[] t ) {
		t[0] = x;
		t[1] = y;
		t[2] = z;
		t[3] = w;
	}

	/**
	 * Negate.
	 */
	public void negate() {
		x = -x;
		y = -y;
		z = -z;
		w = -w;
	}

	/**
	 * Negate.
	 * 
	 * @param t
	 *            the t
	 */
	public void negate( Tuple4f t ) {
		x = -t.x;
		y = -t.y;
		z = -t.z;
		w = -t.w;
	}

	/**
	 * Interpolate.
	 * 
	 * @param t
	 *            the t
	 * @param alpha
	 *            the alpha
	 */
	public void interpolate( Tuple4f t, float alpha ) {
		float a = 1-alpha;
		x = a*x + alpha*t.x;
		y = a*y + alpha*t.y;
		z = a*z + alpha*t.z;
		w = a*w + alpha*t.w;
	}

	/**
	 * Scale.
	 * 
	 * @param s
	 *            the s
	 */
	public void scale( float s ) {
		x *= s;
		y *= s;
		z *= s;
		w *= s;
	}

	/**
	 * Adds the.
	 * 
	 * @param t
	 *            the t
	 */
	public void add( Tuple4f t ) {
		x += t.x;
		y += t.y;
		z += t.z;
		w += t.w;
	}

	/**
	 * Adds the.
	 * 
	 * @param t1
	 *            the t1
	 * @param t2
	 *            the t2
	 */
	public void add( Tuple4f t1, Tuple4f t2 ) {
		x = t1.x+t2.x;
		y = t1.y+t2.y;
		z = t1.z+t2.z;
		w = t1.w+t2.w;
	}

	/**
	 * Sub.
	 * 
	 * @param t
	 *            the t
	 */
	public void sub( Tuple4f t ) {
		x -= t.x;
		y -= t.y;
		z -= t.z;
		w -= t.w;
	}

	/**
	 * Sub.
	 * 
	 * @param t1
	 *            the t1
	 * @param t2
	 *            the t2
	 */
	public void sub( Tuple4f t1, Tuple4f t2 ) {
		x = t1.x-t2.x;
		y = t1.y-t2.y;
		z = t1.z-t2.z;
		w = t1.w-t2.w;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "["+x+", "+y+", "+z+", "+w+"]";
	}
	
}
