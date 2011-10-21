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

// TODO: Auto-generated Javadoc
/**
 * The Class HSBAdjustFilter.
 */
public class HSBAdjustFilter extends PointFilter {
	
	/** The b factor. */
	public float hFactor, sFactor, bFactor;
	
	/** The hsb. */
	private float[] hsb = new float[3];
	
	/**
	 * Instantiates a new hSB adjust filter.
	 */
	public HSBAdjustFilter() {
		this(0, 0, 0);
	}

	/**
	 * Instantiates a new hSB adjust filter.
	 * 
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 */
	public HSBAdjustFilter(float r, float g, float b) {
		hFactor = r;
		sFactor = g;
		bFactor = b;
		canFilterIndexColorModel = true;
	}

	/**
	 * Sets the h factor.
	 * 
	 * @param hFactor
	 *            the new h factor
	 */
	public void setHFactor( float hFactor ) {
		this.hFactor = hFactor;
	}
	
	/**
	 * Gets the h factor.
	 * 
	 * @return the h factor
	 */
	public float getHFactor() {
		return hFactor;
	}
	
	/**
	 * Sets the s factor.
	 * 
	 * @param sFactor
	 *            the new s factor
	 */
	public void setSFactor( float sFactor ) {
		this.sFactor = sFactor;
	}
	
	/**
	 * Gets the s factor.
	 * 
	 * @return the s factor
	 */
	public float getSFactor() {
		return sFactor;
	}
	
	/**
	 * Sets the b factor.
	 * 
	 * @param bFactor
	 *            the new b factor
	 */
	public void setBFactor( float bFactor ) {
		this.bFactor = bFactor;
	}
	
	/**
	 * Gets the b factor.
	 * 
	 * @return the b factor
	 */
	public float getBFactor() {
		return bFactor;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		int a = rgb & 0xff000000;
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		Color.RGBtoHSB(r, g, b, hsb);
		hsb[0] += hFactor;
		while (hsb[0] < 0)
			hsb[0] += Math.PI*2;
		hsb[1] += sFactor;
		if (hsb[1] < 0)
			hsb[1] = 0;
		else if (hsb[1] > 1.0)
			hsb[1] = 1.0f;
		hsb[2] += bFactor;
		if (hsb[2] < 0)
			hsb[2] = 0;
		else if (hsb[2] > 1.0)
			hsb[2] = 1.0f;
		rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		return a | (rgb & 0xffffff);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Colors/Adjust HSB...";
	}
}

