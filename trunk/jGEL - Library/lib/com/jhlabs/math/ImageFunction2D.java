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

package com.jhlabs.math;

import java.awt.*;
import java.awt.image.*;
import com.jhlabs.image.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageFunction2D.
 */
public class ImageFunction2D implements Function2D {

	/** The Constant ZERO. */
	public final static int ZERO = 0;
	
	/** The Constant CLAMP. */
	public final static int CLAMP = 1;
	
	/** The Constant WRAP. */
	public final static int WRAP = 2;
	
	/** The pixels. */
	protected int[] pixels;
	
	/** The width. */
	protected int width;
	
	/** The height. */
	protected int height;
	
	/** The edge action. */
	protected int edgeAction = ZERO;
	
	/** The alpha. */
	protected boolean alpha = false;
	
	/**
	 * Instantiates a new image function2 d.
	 * 
	 * @param image
	 *            the image
	 */
	public ImageFunction2D(BufferedImage image) {
		this(image, false);
	}
	
	/**
	 * Instantiates a new image function2 d.
	 * 
	 * @param image
	 *            the image
	 * @param alpha
	 *            the alpha
	 */
	public ImageFunction2D(BufferedImage image, boolean alpha) {
		this(image, ZERO, alpha);
	}
	
	/**
	 * Instantiates a new image function2 d.
	 * 
	 * @param image
	 *            the image
	 * @param edgeAction
	 *            the edge action
	 * @param alpha
	 *            the alpha
	 */
	public ImageFunction2D(BufferedImage image, int edgeAction, boolean alpha) {
		init( getRGB( image, 0, 0, image.getWidth(), image.getHeight(), null), image.getWidth(), image.getHeight(), edgeAction, alpha);
	}
	
	/**
	 * Instantiates a new image function2 d.
	 * 
	 * @param pixels
	 *            the pixels
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param edgeAction
	 *            the edge action
	 * @param alpha
	 *            the alpha
	 */
	public ImageFunction2D(int[] pixels, int width, int height, int edgeAction, boolean alpha) {
		init(pixels, width, height, edgeAction, alpha);
	}
	
 	/**
		 * Instantiates a new image function2 d.
		 * 
		 * @param image
		 *            the image
		 */
	 public ImageFunction2D(Image image) {
		this( image, ZERO, false );
 	}
	
	/**
	 * Instantiates a new image function2 d.
	 * 
	 * @param image
	 *            the image
	 * @param edgeAction
	 *            the edge action
	 * @param alpha
	 *            the alpha
	 */
	public ImageFunction2D(Image image, int edgeAction, boolean alpha) {
 		PixelGrabber pg = new PixelGrabber(image, 0, 0, -1, -1, null, 0, -1);
 		try {
 			pg.grabPixels();
 		} catch (InterruptedException e) {
 			throw new RuntimeException("interrupted waiting for pixels!");
 		}
 		if ((pg.status() & ImageObserver.ABORT) != 0) {
 			throw new RuntimeException("image fetch aborted");
 		}
 		init((int[])pg.getPixels(), pg.getWidth(), pg.getHeight(), edgeAction, alpha);
  	}

	/**
	 * A convenience method for getting ARGB pixels from an image. This tries to
	 * avoid the performance penalty of BufferedImage.getRGB unmanaging the
	 * image.
	 * 
	 * @param image
	 *            the image
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param pixels
	 *            the pixels
	 * @return the rGB
	 */
	public int[] getRGB( BufferedImage image, int x, int y, int width, int height, int[] pixels ) {
		int type = image.getType();
		if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
			return (int [])image.getRaster().getDataElements( x, y, width, height, pixels );
		return image.getRGB( x, y, width, height, pixels, 0, width );
    }

	/**
	 * Inits the.
	 * 
	 * @param pixels
	 *            the pixels
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param edgeAction
	 *            the edge action
	 * @param alpha
	 *            the alpha
	 */
	public void init(int[] pixels, int width, int height, int edgeAction, boolean alpha) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
		this.edgeAction = edgeAction;
		this.alpha = alpha;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.math.Function2D#evaluate(float, float)
	 */
	public float evaluate(float x, float y) {
		int ix = (int)x;
		int iy = (int)y;
		if (edgeAction == WRAP) {
			ix = ImageMath.mod(ix, width);
			iy = ImageMath.mod(iy, height);
		} else if (ix < 0 || iy < 0 || ix >= width || iy >= height) {
			if (edgeAction == ZERO)
				return 0;
			if (ix < 0)
				ix = 0;
			else if (ix >= width)
				ix = width-1;
			if (iy < 0)
				iy = 0;
			else if (iy >= height)
				iy = height-1;
		}
		return alpha ? ((pixels[iy*width+ix] >> 24) & 0xff) / 255.0f : PixelUtils.brightness(pixels[iy*width+ix]) / 255.0f;
	}
	
	/**
	 * Sets the edge action.
	 * 
	 * @param edgeAction
	 *            the new edge action
	 */
	public void setEdgeAction(int edgeAction) {
		this.edgeAction = edgeAction;
	}

	/**
	 * Gets the edge action.
	 * 
	 * @return the edge action
	 */
	public int getEdgeAction() {
		return edgeAction;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the pixels.
	 * 
	 * @return the pixels
	 */
	public int[] getPixels() {
		return pixels;
	}
}

