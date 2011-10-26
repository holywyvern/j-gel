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
 * A filter which can be used to produce wipes by transferring the luma of a Destination image into the alpha channel of the source.
 */
public class ChromaKeyFilter extends AbstractBufferedImageOp {
	
	/** The h tolerance. */
	private float hTolerance = 0;
	
	/** The s tolerance. */
	private float sTolerance = 0;
	
	/** The b tolerance. */
	private float bTolerance = 0;
	
	/** The color. */
	private int color;

	/**
	 * Instantiates a new chroma key filter.
	 */
	public ChromaKeyFilter() {
	}

	/**
	 * Set the tolerance of the image in the range 0..1. *arg tolerance The
	 * tolerance
	 * 
	 * @param hTolerance
	 *            the new h tolerance
	 */
	public void setHTolerance( float hTolerance ) {
		this.hTolerance = hTolerance;
	}
	
	/**
	 * Gets the h tolerance.
	 * 
	 * @return the h tolerance
	 */
	public float getHTolerance() {
		return hTolerance;
	}
	
	/**
	 * Sets the s tolerance.
	 * 
	 * @param sTolerance
	 *            the new s tolerance
	 */
	public void setSTolerance( float sTolerance ) {
		this.sTolerance = sTolerance;
	}
	
	/**
	 * Gets the s tolerance.
	 * 
	 * @return the s tolerance
	 */
	public float getSTolerance() {
		return sTolerance;
	}
	
	/**
	 * Sets the b tolerance.
	 * 
	 * @param bTolerance
	 *            the new b tolerance
	 */
	public void setBTolerance( float bTolerance ) {
		this.bTolerance = bTolerance;
	}
	
	/**
	 * Gets the b tolerance.
	 * 
	 * @return the b tolerance
	 */
	public float getBTolerance() {
		return bTolerance;
	}
	
	/**
	 * Sets the color.
	 * 
	 * @param color
	 *            the new color
	 */
	public void setColor( int color ) {
		this.color = color;
	}
	
	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
		
    /* (non-Javadoc)
     * @see java.awt.image.BufferedImageOp#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
     */
    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        int width = src.getWidth();
        int height = src.getHeight();
		int type = src.getType();
		WritableRaster srcRaster = src.getRaster();

        if ( dst == null )
            dst = createCompatibleDestImage( src, null );
		WritableRaster dstRaster = dst.getRaster();

		float[] hsb1 = null;
		float[] hsb2 = null;
		int rgb2 = color;
		int r2 = (rgb2 >> 16) & 0xff;
		int g2 = (rgb2 >> 8) & 0xff;
		int b2 = rgb2 & 0xff;
		hsb2 = Color.RGBtoHSB( r2, b2, g2, hsb2 );
		int[] inPixels = null;
		for ( int y = 0; y < height; y++ ) {
			inPixels = getRGB( src, 0, y, width, 1, inPixels );
			for ( int x = 0; x < width; x++ ) {
				int rgb1 = inPixels[x];

				int r1 = (rgb1 >> 16) & 0xff;
				int g1 = (rgb1 >> 8) & 0xff;
				int b1 = rgb1 & 0xff;
				hsb1 = Color.RGBtoHSB( r1, b1, g1, hsb1 );
//                    int tolerance = (int)(255*tolerance);
//                    return Math.abs(r1-r2) <= tolerance && Math.abs(g1-g2) <= tolerance && Math.abs(b1-b2) <= tolerance;

//                   if ( PixelUtils.nearColors( in, clean, (int)(255*tolerance) ) )
				if ( Math.abs( hsb1[0] - hsb2[0] ) < hTolerance && Math.abs( hsb1[1] - hsb2[1] ) < sTolerance && Math.abs( hsb1[2] - hsb2[2] ) < bTolerance )
					inPixels[x] = rgb1 & 0xffffff;
				else
					inPixels[x] = rgb1;
			}
			setRGB( dst, 0, y, width, 1, inPixels );
		}

        return dst;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Keying/Chroma Key...";
	}
}