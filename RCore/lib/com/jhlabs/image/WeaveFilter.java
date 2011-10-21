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
 * The Class WeaveFilter.
 */
public class WeaveFilter extends PointFilter {

	/** The x width. */
	private float xWidth = 16;
	
	/** The y width. */
	private float yWidth = 16;
	
	/** The x gap. */
	private float xGap = 6;
	
	/** The y gap. */
	private float yGap = 6;
	
	/** The rows. */
	private int rows = 4;
	
	/** The cols. */
	private int cols = 4;
	
	/** The rgb x. */
	private int rgbX = 0xffff8080;
	
	/** The rgb y. */
	private int rgbY = 0xff8080ff;
	
	/** The use image colors. */
	private boolean useImageColors = true;
	
	/** The round threads. */
	private boolean roundThreads = false;
	
	/** The shade crossings. */
	private boolean shadeCrossings = true;

	/** The matrix. */
	public int[][] matrix = {
		{ 0, 1, 0, 1 },
		{ 1, 0, 1, 0 },
		{ 0, 1, 0, 1 },
		{ 1, 0, 1, 0 },
	};
	
	/**
	 * Instantiates a new weave filter.
	 */
	public WeaveFilter() {
	}
	
	/**
	 * Sets the x gap.
	 * 
	 * @param xGap
	 *            the new x gap
	 */
	public void setXGap(float xGap) {
		this.xGap = xGap;
	}

	/**
	 * Sets the x width.
	 * 
	 * @param xWidth
	 *            the new x width
	 */
	public void setXWidth(float xWidth) {
		this.xWidth = xWidth;
	}

	/**
	 * Gets the x width.
	 * 
	 * @return the x width
	 */
	public float getXWidth() {
		return xWidth;
	}

	/**
	 * Sets the y width.
	 * 
	 * @param yWidth
	 *            the new y width
	 */
	public void setYWidth(float yWidth) {
		this.yWidth = yWidth;
	}

	/**
	 * Gets the y width.
	 * 
	 * @return the y width
	 */
	public float getYWidth() {
		return yWidth;
	}

	/**
	 * Gets the x gap.
	 * 
	 * @return the x gap
	 */
	public float getXGap() {
		return xGap;
	}

	/**
	 * Sets the y gap.
	 * 
	 * @param yGap
	 *            the new y gap
	 */
	public void setYGap(float yGap) {
		this.yGap = yGap;
	}

	/**
	 * Gets the y gap.
	 * 
	 * @return the y gap
	 */
	public float getYGap() {
		return yGap;
	}

	/**
	 * Sets the crossings.
	 * 
	 * @param matrix
	 *            the new crossings
	 */
	public void setCrossings(int[][] matrix) {
		this.matrix = matrix;
	}
	
	/**
	 * Gets the crossings.
	 * 
	 * @return the crossings
	 */
	public int[][] getCrossings() {
		return matrix;
	}
	
	/**
	 * Sets the use image colors.
	 * 
	 * @param useImageColors
	 *            the new use image colors
	 */
	public void setUseImageColors(boolean useImageColors) {
		this.useImageColors = useImageColors;
	}

	/**
	 * Gets the use image colors.
	 * 
	 * @return the use image colors
	 */
	public boolean getUseImageColors() {
		return useImageColors;
	}

	/**
	 * Sets the round threads.
	 * 
	 * @param roundThreads
	 *            the new round threads
	 */
	public void setRoundThreads(boolean roundThreads) {
		this.roundThreads = roundThreads;
	}

	/**
	 * Gets the round threads.
	 * 
	 * @return the round threads
	 */
	public boolean getRoundThreads() {
		return roundThreads;
	}

	/**
	 * Sets the shade crossings.
	 * 
	 * @param shadeCrossings
	 *            the new shade crossings
	 */
	public void setShadeCrossings(boolean shadeCrossings) {
		this.shadeCrossings = shadeCrossings;
	}

	/**
	 * Gets the shade crossings.
	 * 
	 * @return the shade crossings
	 */
	public boolean getShadeCrossings() {
		return shadeCrossings;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		x += xWidth+xGap/2;
		y += yWidth+yGap/2;
		float nx = ImageMath.mod(x, xWidth+xGap);
		float ny = ImageMath.mod(y, yWidth+yGap);
		int ix = (int)(x / (xWidth+xGap));
		int iy = (int)(y / (yWidth+yGap));
		boolean inX = nx < xWidth;
		boolean inY = ny < yWidth;
		float dX, dY;
		float cX, cY;
		int lrgbX, lrgbY;

		if (roundThreads) {
			dX = Math.abs(xWidth/2-nx) / xWidth / 2;
			dY = Math.abs(yWidth/2-ny) / yWidth / 2;
		} else {
			dX = dY = 0;
		}

		if (shadeCrossings) {
			cX = ImageMath.smoothStep(xWidth/2, xWidth/2+xGap, Math.abs(xWidth/2-nx));
			cY = ImageMath.smoothStep(yWidth/2, yWidth/2+yGap, Math.abs(yWidth/2-ny));
		} else {
			cX = cY = 0;
		}

		if (useImageColors) {
			lrgbX = lrgbY = rgb;
		} else {
			lrgbX = rgbX;
			lrgbY = rgbY;
		}
		int v;
		int ixc = ix % cols;
		int iyr = iy % rows;
		int m = matrix[iyr][ixc];
		if (inX) {
			if (inY) {
				v = m == 1 ? lrgbX : lrgbY;
				v = ImageMath.mixColors(2 * (m == 1 ? dX : dY), v, 0xff000000);
			} else {
				if (shadeCrossings) {
					if (m != matrix[(iy+1) % rows][ixc]) {
						if (m == 0)
							cY = 1-cY;
						cY *= 0.5f;
						lrgbX = ImageMath.mixColors(cY, lrgbX, 0xff000000);
					} else if (m == 0)
						lrgbX = ImageMath.mixColors(0.5f, lrgbX, 0xff000000);
				}
				v = ImageMath.mixColors(2 * dX, lrgbX, 0xff000000);
			}
		} else if (inY) {
			if (shadeCrossings) {
				if (m != matrix[iyr][(ix+1) % cols]) {
					if (m == 1)
						cX = 1-cX;
					cX *= 0.5f;
					lrgbY = ImageMath.mixColors(cX, lrgbY, 0xff000000);
				} else if (m == 1)
					lrgbY = ImageMath.mixColors(0.5f, lrgbY, 0xff000000);
			}
			v = ImageMath.mixColors(2 * dY, lrgbY, 0xff000000);
		} else
			v = 0x00000000;
		return v;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Texture/Weave...";
	}

}


