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
public class Point3f extends Tuple3f {

	/**
	 * Instantiates a new point3f.
	 */
	public Point3f() {
		this( 0, 0, 0 );
	}
	
	/**
	 * Instantiates a new point3f.
	 * 
	 * @param x
	 *            the x
	 */
	public Point3f( float[] x ) {
		this.x = x[0];
		this.y = x[1];
		this.z = x[2];
	}

	/**
	 * Instantiates a new point3f.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public Point3f( float x, float y, float z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Instantiates a new point3f.
	 * 
	 * @param t
	 *            the t
	 */
	public Point3f( Point3f t ) {
		this.x = t.x;
		this.y = t.y;
		this.z = t.z;
	}

	/**
	 * Instantiates a new point3f.
	 * 
	 * @param t
	 *            the t
	 */
	public Point3f( Tuple3f t ) {
		this.x = t.x;
		this.y = t.y;
		this.z = t.z;
	}

	/**
	 * Distance l1.
	 * 
	 * @param p
	 *            the p
	 * @return the float
	 */
	public float distanceL1( Point3f p ) {
		return Math.abs(x-p.x) + Math.abs(y-p.y) + Math.abs(z-p.z);
	}

	/**
	 * Distance squared.
	 * 
	 * @param p
	 *            the p
	 * @return the float
	 */
	public float distanceSquared( Point3f p ) {
		float dx = x-p.x;
		float dy = y-p.y;
		float dz = z-p.z;
		return dx*dx+dy*dy+dz*dz;
	}

	/**
	 * Distance.
	 * 
	 * @param p
	 *            the p
	 * @return the float
	 */
	public float distance( Point3f p ) {
		float dx = x-p.x;
		float dy = y-p.y;
		float dz = z-p.z;
		return (float)Math.sqrt( dx*dx+dy*dy+dz*dz );
	}

}
