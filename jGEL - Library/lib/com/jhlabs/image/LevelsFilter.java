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
 * A filter which allows levels adjustment on an image.
 */
public class LevelsFilter extends WholeImageFilter {

	/** The lut. */
	private int[][] lut;
    
    /** The low level. */
    private float lowLevel = 0;
    
    /** The high level. */
    private float highLevel = 1;
    
    /** The low output level. */
    private float lowOutputLevel = 0;
    
    /** The high output level. */
    private float highOutputLevel = 1;

	/**
	 * Instantiates a new levels filter.
	 */
	public LevelsFilter() {
	}

    /**
	 * Sets the low level.
	 * 
	 * @param lowLevel
	 *            the new low level
	 */
    public void setLowLevel( float lowLevel ) {
        this.lowLevel = lowLevel;
    }
    
    /**
	 * Gets the low level.
	 * 
	 * @return the low level
	 */
    public float getLowLevel() {
        return lowLevel;
    }
    
    /**
	 * Sets the high level.
	 * 
	 * @param highLevel
	 *            the new high level
	 */
    public void setHighLevel( float highLevel ) {
        this.highLevel = highLevel;
    }
    
    /**
	 * Gets the high level.
	 * 
	 * @return the high level
	 */
    public float getHighLevel() {
        return highLevel;
    }
    
    /**
	 * Sets the low output level.
	 * 
	 * @param lowOutputLevel
	 *            the new low output level
	 */
    public void setLowOutputLevel( float lowOutputLevel ) {
        this.lowOutputLevel = lowOutputLevel;
    }
    
    /**
	 * Gets the low output level.
	 * 
	 * @return the low output level
	 */
    public float getLowOutputLevel() {
        return lowOutputLevel;
    }
    
    /**
	 * Sets the high output level.
	 * 
	 * @param highOutputLevel
	 *            the new high output level
	 */
    public void setHighOutputLevel( float highOutputLevel ) {
        this.highOutputLevel = highOutputLevel;
    }
    
    /**
	 * Gets the high output level.
	 * 
	 * @return the high output level
	 */
    public float getHighOutputLevel() {
        return highOutputLevel;
    }
    
	/* (non-Javadoc)
	 * @see com.jhlabs.image.WholeImageFilter#filterPixels(int, int, int[], java.awt.Rectangle)
	 */
	protected int[] filterPixels( int width, int height, int[] inPixels, Rectangle transformedSpace ) {
		Histogram histogram = new Histogram(inPixels, width, height, 0, width);

		int i, j;

		if (histogram.getNumSamples() > 0) {
			float scale = 255.0f / histogram.getNumSamples();
			lut = new int[3][256];

            float low = lowLevel * 255;
            float high = highLevel * 255;
            if ( low == high )
                high++;
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 256; j++)
					lut[i][j] = PixelUtils.clamp( (int)(255 * (lowOutputLevel + (highOutputLevel-lowOutputLevel) * (j-low)/(high-low))) );
			}
		} else
			lut = null;

		i = 0;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				inPixels[i] = filterRGB(x, y, inPixels[i]);
				i++;
			}
		lut = null;
		
		return inPixels;
	}

	/**
	 * Filter rgb.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param rgb
	 *            the rgb
	 * @return the int
	 */
	public int filterRGB(int x, int y, int rgb) {
		if (lut != null) {
			int a = rgb & 0xff000000;
			int r = lut[Histogram.RED][(rgb >> 16) & 0xff];
			int g = lut[Histogram.GREEN][(rgb >> 8) & 0xff];
			int b = lut[Histogram.BLUE][rgb & 0xff];

			return a | (r << 16) | (g << 8) | b;
		}
		return rgb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Colors/Levels...";
	}
}
