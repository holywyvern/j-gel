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
 * The Class RGBAdjustFilter.
 */
public class RGBAdjustFilter extends PointFilter {
	
	/** The b factor. */
	public float rFactor, gFactor, bFactor;

	/**
	 * Instantiates a new rGB adjust filter.
	 */
	public RGBAdjustFilter() {
		this(0, 0, 0);
	}

	/**
	 * Instantiates a new rGB adjust filter.
	 * 
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 */
	public RGBAdjustFilter(float r, float g, float b) {
		rFactor = 1+r;
		gFactor = 1+g;
		bFactor = 1+b;
		canFilterIndexColorModel = true;
	}

	/**
	 * Sets the r factor.
	 * 
	 * @param rFactor
	 *            the new r factor
	 */
	public void setRFactor( float rFactor ) {
		this.rFactor = 1+rFactor;
	}
	
	/**
	 * Gets the r factor.
	 * 
	 * @return the r factor
	 */
	public float getRFactor() {
		return rFactor-1;
	}
	
	/**
	 * Sets the g factor.
	 * 
	 * @param gFactor
	 *            the new g factor
	 */
	public void setGFactor( float gFactor ) {
		this.gFactor = 1+gFactor;
	}
	
	/**
	 * Gets the g factor.
	 * 
	 * @return the g factor
	 */
	public float getGFactor() {
		return gFactor-1;
	}
	
	/**
	 * Sets the b factor.
	 * 
	 * @param bFactor
	 *            the new b factor
	 */
	public void setBFactor( float bFactor ) {
		this.bFactor = 1+bFactor;
	}
	
	/**
	 * Gets the b factor.
	 * 
	 * @return the b factor
	 */
	public float getBFactor() {
		return bFactor-1;
	}

	/**
	 * Gets the lUT.
	 * 
	 * @return the lUT
	 */
	public int[] getLUT() {
		int[] lut = new int[256];
		for ( int i = 0; i < 256; i++ ) {
			lut[i] = filterRGB( 0, 0, (i << 24) | (i << 16) | (i << 8) | i );
		}
		return lut;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		int a = rgb & 0xff000000;
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		r = PixelUtils.clamp((int)(r * rFactor));
		g = PixelUtils.clamp((int)(g * gFactor));
		b = PixelUtils.clamp((int)(b * bFactor));
		return a | (r << 16) | (g << 8) | b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Colors/Adjust RGB...";
	}
}

