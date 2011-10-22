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
 * The Class TransferFilter.
 */
public abstract class TransferFilter extends PointFilter {

	/** The b table. */
	protected int[] rTable, gTable, bTable;
	
	/** The initialized. */
	protected boolean initialized = false;
	
	/**
	 * Instantiates a new transfer filter.
	 */
	public TransferFilter() {
		canFilterIndexColorModel = true;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		int a = rgb & 0xff000000;
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		r = rTable[r];
		g = gTable[g];
		b = bTable[b];
		return a | (r << 16) | (g << 8) | b;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
	 */
	public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
		if (!initialized)
			initialize();
		return super.filter( src, dst );
	}

	/**
	 * Initialize.
	 */
	protected void initialize() {
		initialized = true;
		rTable = gTable = bTable = makeTable();
	}

	/**
	 * Make table.
	 * 
	 * @return the int[]
	 */
	protected int[] makeTable() {
		int[] table = new int[256];
		for (int i = 0; i < 256; i++)
			table[i] = PixelUtils.clamp( (int)( 255 * transferFunction( i / 255.0f ) ) );
		return table;
	}

	/**
	 * Transfer function.
	 * 
	 * @param v
	 *            the v
	 * @return the float
	 */
	protected float transferFunction( float v ) {
		return 0;
	}

	/**
	 * Gets the lUT.
	 * 
	 * @return the lUT
	 */
	public int[] getLUT() {
		if (!initialized)
			initialize();
		int[] lut = new int[256];
		for ( int i = 0; i < 256; i++ ) {
			lut[i] = filterRGB( 0, 0, (i << 24) | (i << 16) | (i << 8) | i );
		}
		return lut;
	}
	
}
