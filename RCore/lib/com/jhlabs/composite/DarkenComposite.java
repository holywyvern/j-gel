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
 * The Class DarkenComposite.
 */
public final class DarkenComposite extends RGBComposite {

	/**
	 * Instantiates a new darken composite.
	 * 
	 * @param alpha
	 *            the alpha
	 */
	public DarkenComposite( float alpha ) {
        super( alpha );
	}

	/* (non-Javadoc)
	 * @see java.awt.Composite#createContext(java.awt.image.ColorModel, java.awt.image.ColorModel, java.awt.RenderingHints)
	 */
	public CompositeContext createContext( ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints ) {
		return new Context( extraAlpha, srcColorModel, dstColorModel );
	}

    /**
	 * The Class Context.
	 */
    static class Context extends RGBCompositeContext {
        
        /**
		 * Instantiates a new context.
		 * 
		 * @param alpha
		 *            the alpha
		 * @param srcColorModel
		 *            the src color model
		 * @param dstColorModel
		 *            the dst color model
		 */
        public Context( float alpha, ColorModel srcColorModel, ColorModel dstColorModel ) {
            super( alpha, srcColorModel, dstColorModel );
        }

        /* (non-Javadoc)
         * @see com.jhlabs.composite.RGBComposite.RGBCompositeContext#composeRGB(int[], int[], float)
         */
        public void composeRGB( int[] src, int[] dst, float alpha ) {
            int w = src.length;

            for ( int i = 0; i < w; i += 4 ) {
                int sr = src[i];
                int dir = dst[i];
                int sg = src[i+1];
                int dig = dst[i+1];
                int sb = src[i+2];
                int dib = dst[i+2];
                int sa = src[i+3];
                int dia = dst[i+3];
                int dor, dog, dob;

                dor = dir < sr ? dir : sr;
                dog = dig < sg ? dig : sg;
                dob = dib < sb ? dib : sb;

                float a = alpha*sa/255f;
                float ac = 1-a;

                dst[i] = (int)(a*dor + ac*dir);
                dst[i+1] = (int)(a*dog + ac*dig);
                dst[i+2] = (int)(a*dob + ac*dib);
                dst[i+3] = (int)(sa*alpha + dia*ac);
            }
        }
    }

}
