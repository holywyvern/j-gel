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
 * The Class OffsetFilter.
 */
public class OffsetFilter extends TransformFilter {

	/** The height. */
	private int width, height;
	
	/** The y offset. */
	private int xOffset, yOffset;
	
	/** The wrap. */
	private boolean wrap;

	/**
	 * Instantiates a new offset filter.
	 */
	public OffsetFilter() {
		this(0, 0, true);
	}

	/**
	 * Instantiates a new offset filter.
	 * 
	 * @param xOffset
	 *            the x offset
	 * @param yOffset
	 *            the y offset
	 * @param wrap
	 *            the wrap
	 */
	public OffsetFilter(int xOffset, int yOffset, boolean wrap) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.wrap = wrap;
		setEdgeAction( ZERO );
	}

	/**
	 * Sets the x offset.
	 * 
	 * @param xOffset
	 *            the new x offset
	 */
	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	/**
	 * Gets the x offset.
	 * 
	 * @return the x offset
	 */
	public int getXOffset() {
		return xOffset;
	}
	
	/**
	 * Sets the y offset.
	 * 
	 * @param yOffset
	 *            the new y offset
	 */
	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	/**
	 * Gets the y offset.
	 * 
	 * @return the y offset
	 */
	public int getYOffset() {
		return yOffset;
	}
	
	/**
	 * Sets the wrap.
	 * 
	 * @param wrap
	 *            the new wrap
	 */
	public void setWrap(boolean wrap) {
		this.wrap = wrap;
	}
	
	/**
	 * Gets the wrap.
	 * 
	 * @return the wrap
	 */
	public boolean getWrap() {
		return wrap;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.TransformFilter#transformInverse(int, int, float[])
	 */
	protected void transformInverse(int x, int y, float[] out) {
		if ( wrap ) {
			out[0] = (x+width-xOffset) % width;
			out[1] = (y+height-yOffset) % height;
		} else {
			out[0] = x-xOffset;
			out[1] = y-yOffset;
		}
	}

    /* (non-Javadoc)
     * @see com.jhlabs.image.TransformFilter#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
     */
    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
		this.width = src.getWidth();
		this.height = src.getHeight();
		if ( wrap ) {
			while (xOffset < 0)
				xOffset += width;
			while (yOffset < 0)
				yOffset += height;
			xOffset %= width;
			yOffset %= height;
		}
		return super.filter( src, dst );
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Distort/Offset...";
	}
}
