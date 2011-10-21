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

package com.jhlabs.composite;

import java.awt.*;
import java.awt.image.*;

// TODO: Auto-generated Javadoc
/**
 * The Class RGBComposite.
 */
public abstract class RGBComposite implements Composite {

	/** The extra alpha. */
	protected float extraAlpha;

	/**
	 * Instantiates a new rGB composite.
	 */
	public RGBComposite() {
		this( 1.0f );
	}

	/**
	 * Instantiates a new rGB composite.
	 * 
	 * @param alpha
	 *            the alpha
	 */
	public RGBComposite( float alpha ) {
		if ( alpha < 0.0f || alpha > 1.0f )
			throw new IllegalArgumentException("RGBComposite: alpha must be between 0 and 1");
		this.extraAlpha = alpha;
	}

	/**
	 * Gets the alpha.
	 * 
	 * @return the alpha
	 */
	public float getAlpha() {
		return extraAlpha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return Float.floatToIntBits(extraAlpha);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof RGBComposite))
			return false;
		RGBComposite c = (RGBComposite)o;

		if ( extraAlpha != c.extraAlpha )
			return false;
		return true;
	}

    /**
	 * The Class RGBCompositeContext.
	 */
    public abstract static class RGBCompositeContext implements CompositeContext {

        /** The alpha. */
        private float alpha;
        
        /** The src color model. */
        private ColorModel srcColorModel;
        
        /** The dst color model. */
        private ColorModel dstColorModel;

        /**
		 * Instantiates a new rGB composite context.
		 * 
		 * @param alpha
		 *            the alpha
		 * @param srcColorModel
		 *            the src color model
		 * @param dstColorModel
		 *            the dst color model
		 */
        public RGBCompositeContext( float alpha, ColorModel srcColorModel, ColorModel dstColorModel ) {
            this.alpha = alpha;
            this.srcColorModel = srcColorModel;
            this.dstColorModel = dstColorModel;
        }

        /* (non-Javadoc)
         * @see java.awt.CompositeContext#dispose()
         */
        public void dispose() {
        }
        
        // Multiply two numbers in the range 0..255 such that 255*255=255
        /**
		 * Multiply255.
		 * 
		 * @param a
		 *            the a
		 * @param b
		 *            the b
		 * @return the int
		 */
        static int multiply255( int a, int b ) {
            int t = a * b + 0x80;
            return ((t >> 8) + t) >> 8;
        }
        
        /**
		 * Clamp.
		 * 
		 * @param a
		 *            the a
		 * @return the int
		 */
        static int clamp( int a ) {
            return a < 0 ? 0 : a > 255 ? 255 : a;
        }
	
        /**
		 * Compose rgb.
		 * 
		 * @param src
		 *            the src
		 * @param dst
		 *            the dst
		 * @param alpha
		 *            the alpha
		 */
        public abstract void composeRGB( int[] src, int[] dst, float alpha );

        /* (non-Javadoc)
         * @see java.awt.CompositeContext#compose(java.awt.image.Raster, java.awt.image.Raster, java.awt.image.WritableRaster)
         */
        public void compose( Raster src, Raster dstIn, WritableRaster dstOut ) {
            float alpha = this.alpha;

            int[] srcPix = null;
            int[] dstPix = null;

            int x = dstOut.getMinX();
            int w = dstOut.getWidth();
            int y0 = dstOut.getMinY();
            int y1 = y0 + dstOut.getHeight();

            for ( int y = y0; y < y1; y++ ) {
                srcPix = src.getPixels( x, y, w, 1, srcPix );
                dstPix = dstIn.getPixels( x, y, w, 1, dstPix );
                composeRGB( srcPix, dstPix, alpha );
                dstOut.setPixels( x, y, w, 1, dstPix );
            }
        }

    }
}
