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

package com.jhlabs.image;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FadeFilter.
 */
public class FadeFilter extends PointFilter {

	/** The height. */
	private int width, height;
	
	/** The angle. */
	private float angle = 0.0f;
	
	/** The fade start. */
	private float fadeStart = 1.0f;
	
	/** The fade width. */
	private float fadeWidth = 10.0f;
	
	/** The sides. */
	private int sides;
	
	/** The invert. */
	private boolean invert;
	
	/** The m00. */
	private float m00 = 1.0f;
	
	/** The m01. */
	private float m01 = 0.0f;
	
	/** The m10. */
	private float m10 = 0.0f;
	
	/** The m11. */
	private float m11 = 1.0f;

	/**
     * Specifies the angle of the texture.
     * @param angle the angle of the texture.
     * @angle
     * @see #getAngle
     */
	public void setAngle(float angle) {
		this.angle = angle;
		float cos = (float)Math.cos(angle);
		float sin = (float)Math.sin(angle);
		m00 = cos;
		m01 = sin;
		m10 = -sin;
		m11 = cos;
	}

	/**
     * Returns the angle of the texture.
     * @return the angle of the texture.
     * @see #setAngle
     */
	public float getAngle() {
		return angle;
	}

	/**
	 * Sets the sides.
	 * 
	 * @param sides
	 *            the new sides
	 */
	public void setSides(int sides) {
		this.sides = sides;
	}

	/**
	 * Gets the sides.
	 * 
	 * @return the sides
	 */
	public int getSides() {
		return sides;
	}

	/**
	 * Sets the fade start.
	 * 
	 * @param fadeStart
	 *            the new fade start
	 */
	public void setFadeStart(float fadeStart) {
		this.fadeStart = fadeStart;
	}

	/**
	 * Gets the fade start.
	 * 
	 * @return the fade start
	 */
	public float getFadeStart() {
		return fadeStart;
	}

	/**
	 * Sets the fade width.
	 * 
	 * @param fadeWidth
	 *            the new fade width
	 */
	public void setFadeWidth(float fadeWidth) {
		this.fadeWidth = fadeWidth;
	}

	/**
	 * Gets the fade width.
	 * 
	 * @return the fade width
	 */
	public float getFadeWidth() {
		return fadeWidth;
	}

	/**
	 * Sets the invert.
	 * 
	 * @param invert
	 *            the new invert
	 */
	public void setInvert(boolean invert) {
		this.invert = invert;
	}

	/**
	 * Gets the invert.
	 * 
	 * @return the invert
	 */
	public boolean getInvert() {
		return invert;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#setDimensions(int, int)
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		super.setDimensions(width, height);
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		float nx = m00*x + m01*y;
		float ny = m10*x + m11*y;
		if (sides == 2)
			nx = (float)Math.sqrt(nx*nx + ny*ny);
		else if (sides == 3)
			nx = ImageMath.mod(nx, 16);
		else if (sides == 4)
			nx = symmetry(nx, 16);
		int alpha = (int)(ImageMath.smoothStep(fadeStart, fadeStart+fadeWidth, nx) * 255);
		if (invert)
			alpha = 255-alpha;
		return (alpha << 24) | (rgb & 0x00ffffff);
	}

	/**
	 * Symmetry.
	 * 
	 * @param x
	 *            the x
	 * @param b
	 *            the b
	 * @return the float
	 */
	public float symmetry(float x, float b) {
/*
		int d = (int)(x / b);
		x = ImageMath.mod(x, b);
		if ((d & 1) == 1)
			return b-x;
		return x;
*/
		x = ImageMath.mod(x, 2*b);
		if (x > b)
			return 2*b-x;
		return x;
	}
	
/*
	public float star(float x, float y, int sides, float rMin, float rMax) {
		float sideAngle = 2*Math.PI / sides;
		float angle = Math.atan2(y, x);
		float r = Math.sqrt(x*x + y*y);
		float t = ImageMath.mod(angle, sideAngle) / sideAngle;
		if (t > 0.5)
			t = 1.0-t;
	}
*/

	/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
public String toString() {
		return "Fade...";
	}

}

