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
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * A filter which draws a gradient interpolated between four colors defined at the corners of the image.
 */
public class FourColorFilter extends PointFilter {
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height;
	
	/** The color nw. */
	private int colorNW;
	
	/** The color ne. */
	private int colorNE;
	
	/** The color sw. */
	private int colorSW;
	
	/** The color se. */
	private int colorSE;
	
	/** The b nw. */
	private int rNW, gNW, bNW;
	
	/** The b ne. */
	private int rNE, gNE, bNE;
	
	/** The b sw. */
	private int rSW, gSW, bSW;
	
	/** The b se. */
	private int rSE, gSE, bSE;

	/**
	 * Instantiates a new four color filter.
	 */
	public FourColorFilter() {
		setColorNW( 0xffff0000 );
		setColorNE( 0xffff00ff );
		setColorSW( 0xff0000ff );
		setColorSE( 0xff00ffff );
	}

	/**
	 * Sets the color nw.
	 * 
	 * @param color
	 *            the new color nw
	 */
	public void setColorNW( int color ) {
		this.colorNW = color;
		rNW = (color >> 16) & 0xff;
		gNW = (color >> 8) & 0xff;
		bNW = color & 0xff;
	}

	/**
	 * Gets the color nw.
	 * 
	 * @return the color nw
	 */
	public int getColorNW() {
		return colorNW;
	}

	/**
	 * Sets the color ne.
	 * 
	 * @param color
	 *            the new color ne
	 */
	public void setColorNE( int color ) {
		this.colorNE = color;
		rNE = (color >> 16) & 0xff;
		gNE = (color >> 8) & 0xff;
		bNE = color & 0xff;
	}

	/**
	 * Gets the color ne.
	 * 
	 * @return the color ne
	 */
	public int getColorNE() {
		return colorNE;
	}

	/**
	 * Sets the color sw.
	 * 
	 * @param color
	 *            the new color sw
	 */
	public void setColorSW( int color ) {
		this.colorSW = color;
		rSW = (color >> 16) & 0xff;
		gSW = (color >> 8) & 0xff;
		bSW = color & 0xff;
	}

	/**
	 * Gets the color sw.
	 * 
	 * @return the color sw
	 */
	public int getColorSW() {
		return colorSW;
	}

	/**
	 * Sets the color se.
	 * 
	 * @param color
	 *            the new color se
	 */
	public void setColorSE( int color ) {
		this.colorSE = color;
		rSE = (color >> 16) & 0xff;
		gSE = (color >> 8) & 0xff;
		bSE = color & 0xff;
	}

	/**
	 * Gets the color se.
	 * 
	 * @return the color se
	 */
	public int getColorSE() {
		return colorSE;
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
		float fx = (float)x / width;
		float fy = (float)y / height;
		float p, q;

		p = rNW + (rNE - rNW) * fx;
		q = rSW + (rSE - rSW) * fx;
		int r = (int)( p + (q - p) * fy + 0.5f );

		p = gNW + (gNE - gNW) * fx;
		q = gSW + (gSE - gSW) * fx;
		int g = (int)( p + (q - p) * fy + 0.5f );

		p = bNW + (bNE - bNW) * fx;
		q = bSW + (bSE - bSW) * fx;
		int b = (int)( p + (q - p) * fy + 0.5f );

		return 0xff000000 | (r << 16) | (g << 8) | b;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Texture/Four Color Fill...";
	}
}
