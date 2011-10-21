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

import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * Vector math package, converted to look similar to javax.vecmath.
 */
public class Color4f extends Tuple4f {

	/**
	 * Instantiates a new color4f.
	 */
	public Color4f() {
		this( 0, 0, 0, 0 );
	}
	
	/**
	 * Instantiates a new color4f.
	 * 
	 * @param x
	 *            the x
	 */
	public Color4f( float[] x ) {
		this.x = x[0];
		this.y = x[1];
		this.z = x[2];
		this.w = x[3];
	}

	/**
	 * Instantiates a new color4f.
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
	public Color4f( float x, float y, float z, float w ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Instantiates a new color4f.
	 * 
	 * @param t
	 *            the t
	 */
	public Color4f( Color4f t ) {
		this.x = t.x;
		this.y = t.y;
		this.z = t.z;
		this.w = t.w;
	}

	/**
	 * Instantiates a new color4f.
	 * 
	 * @param t
	 *            the t
	 */
	public Color4f( Tuple4f t ) {
		this.x = t.x;
		this.y = t.y;
		this.z = t.z;
		this.w = t.w;
	}

	/**
	 * Instantiates a new color4f.
	 * 
	 * @param c
	 *            the c
	 */
	public Color4f( Color c ) {
		set( c );
	}

	/**
	 * Sets the.
	 * 
	 * @param c
	 *            the c
	 */
	public void set( Color c ) {
		set( c.getRGBComponents( null ) );
	}

	/**
	 * Gets the.
	 * 
	 * @return the color
	 */
	public Color get() {
		return new Color( x, y, z, w );
	}
}
