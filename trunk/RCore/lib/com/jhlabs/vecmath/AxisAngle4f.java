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
public class AxisAngle4f {
	
	/** The angle. */
	public float x, y, z, angle;

	/**
	 * Instantiates a new axis angle4f.
	 */
	public AxisAngle4f() {
		this( 0, 0, 0, 0 );
	}
	
	/**
	 * Instantiates a new axis angle4f.
	 * 
	 * @param x
	 *            the x
	 */
	public AxisAngle4f( float[] x ) {
		this.x = x[0];
		this.y = x[1];
		this.z = x[2];
		this.angle = x[2];
	}

	/**
	 * Instantiates a new axis angle4f.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param angle
	 *            the angle
	 */
	public AxisAngle4f( float x, float y, float z, float angle ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}

	/**
	 * Instantiates a new axis angle4f.
	 * 
	 * @param t
	 *            the t
	 */
	public AxisAngle4f( AxisAngle4f t ) {
		this.x = t.x;
		this.y = t.y;
		this.z = t.z;
		this.angle = t.angle;
	}

	/**
	 * Instantiates a new axis angle4f.
	 * 
	 * @param v
	 *            the v
	 * @param angle
	 *            the angle
	 */
	public AxisAngle4f( Vector3f v, float angle ) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.angle = angle;
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
	 * @param angle
	 *            the angle
	 */
	public void set( float x, float y, float z, float angle ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}

	/**
	 * Sets the.
	 * 
	 * @param t
	 *            the t
	 */
	public void set( AxisAngle4f t ) {
		x = t.x;
		y = t.y;
		z = t.z;
		angle = t.angle;
	}

	/**
	 * Gets the.
	 * 
	 * @param t
	 *            the t
	 */
	public void get( AxisAngle4f t ) {
		t.x = x;
		t.y = y;
		t.z = z;
		t.angle = angle;
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
		t[3] = angle;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "["+x+", "+y+", "+z+", "+angle+"]";
	}
	
}
