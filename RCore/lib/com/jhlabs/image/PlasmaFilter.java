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

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import com.jhlabs.math.*;

// TODO: Auto-generated Javadoc
/**
 * The Class PlasmaFilter.
 */
public class PlasmaFilter extends WholeImageFilter {
	
	/** The turbulence. */
	public float turbulence = 1.0f;
	
	/** The scaling. */
	private float scaling = 0.0f;
	
	/** The colormap. */
	private Colormap colormap = new LinearColormap();
	
	/** The random generator. */
	private Random randomGenerator;
	
	/** The seed. */
	private long seed = 567;
	
	/** The use colormap. */
	private boolean useColormap = false;
	
	/** The use image colors. */
	private boolean useImageColors = false;

	/**
	 * Instantiates a new plasma filter.
	 */
	public PlasmaFilter() {
		randomGenerator = new Random();
	}

	/**
     * Specifies the turbulence of the texture.
     * @param turbulence the turbulence of the texture.
     * @min-value 0
     * @max-value 10
     * @see #getTurbulence
     */
	public void setTurbulence(float turbulence) {
		this.turbulence = turbulence;
	}

	/**
     * Returns the turbulence of the effect.
     * @return the turbulence of the effect.
     * @see #setTurbulence
     */
	public float getTurbulence() {
		return turbulence;
	}

	/**
	 * Sets the scaling.
	 * 
	 * @param scaling
	 *            the new scaling
	 */
	public void setScaling(float scaling) {
		this.scaling = scaling;
	}

	/**
	 * Gets the scaling.
	 * 
	 * @return the scaling
	 */
	public float getScaling() {
		return scaling;
	}

    /**
     * Set the colormap to be used for the filter.
     * @param colormap the colormap
     * @see #getColormap
     */
	public void setColormap(Colormap colormap) {
		this.colormap = colormap;
	}
	
    /**
     * Get the colormap to be used for the filter.
     * @return the colormap
     * @see #setColormap
     */
	public Colormap getColormap() {
		return colormap;
	}
	
	/**
	 * Sets the use colormap.
	 * 
	 * @param useColormap
	 *            the new use colormap
	 */
	public void setUseColormap(boolean useColormap) {
		this.useColormap = useColormap;
	}

	/**
	 * Gets the use colormap.
	 * 
	 * @return the use colormap
	 */
	public boolean getUseColormap() {
		return useColormap;
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
	 * Sets the seed.
	 * 
	 * @param seed
	 *            the new seed
	 */
	public void setSeed(int seed) {
		this.seed = seed;
	}

	/**
	 * Gets the seed.
	 * 
	 * @return the seed
	 */
	public int getSeed() {
		return (int)seed;
	}

	/**
	 * Randomize.
	 */
	public void randomize() {
		seed = new Date().getTime();
	}
	
	/**
	 * Random rgb.
	 * 
	 * @param inPixels
	 *            the in pixels
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the int
	 */
	private int randomRGB(int[] inPixels, int x, int y) {
		if (useImageColors) {
			return inPixels[y*originalSpace.width+x];
		} else {
			int r = (int)(255 * randomGenerator.nextFloat());
			int g = (int)(255 * randomGenerator.nextFloat());
			int b = (int)(255 * randomGenerator.nextFloat());
			return 0xff000000 | (r << 16) | (g << 8) | b;
		}
	}

	/**
	 * Displace.
	 * 
	 * @param rgb
	 *            the rgb
	 * @param amount
	 *            the amount
	 * @return the int
	 */
	private int displace(int rgb, float amount) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		r = PixelUtils.clamp(r + (int)(amount * (randomGenerator.nextFloat()-0.5)));
		g = PixelUtils.clamp(g + (int)(amount * (randomGenerator.nextFloat()-0.5)));
		b = PixelUtils.clamp(b + (int)(amount * (randomGenerator.nextFloat()-0.5)));
		return 0xff000000 | (r << 16) | (g << 8) | b;
	}

	/**
	 * Average.
	 * 
	 * @param rgb1
	 *            the rgb1
	 * @param rgb2
	 *            the rgb2
	 * @return the int
	 */
	private int average(int rgb1, int rgb2) {
		return PixelUtils.combinePixels(rgb1, rgb2, PixelUtils.AVERAGE);
	}

	/**
	 * Gets the pixel.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param pixels
	 *            the pixels
	 * @param stride
	 *            the stride
	 * @return the pixel
	 */
	private int getPixel(int x, int y, int[] pixels, int stride) {
		return pixels[y*stride+x];
	}
	
	/**
	 * Put pixel.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param rgb
	 *            the rgb
	 * @param pixels
	 *            the pixels
	 * @param stride
	 *            the stride
	 */
	private void putPixel(int x, int y, int rgb, int[] pixels, int stride) {
		pixels[y*stride+x] = rgb;
	}
	
	/**
	 * Do pixel.
	 * 
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 * @param pixels
	 *            the pixels
	 * @param stride
	 *            the stride
	 * @param depth
	 *            the depth
	 * @param scale
	 *            the scale
	 * @return true, if successful
	 */
	private boolean doPixel(int x1, int y1, int x2, int y2, int[] pixels, int stride, int depth, int scale) {
		int mx, my;

		if (depth == 0) {
			int ml, mr, mt, mb, mm, t;

			int tl = getPixel(x1, y1, pixels, stride);
			int bl = getPixel(x1, y2, pixels, stride);
			int tr = getPixel(x2, y1, pixels, stride);
			int br = getPixel(x2, y2, pixels, stride);

			float amount = (256.0f / (2.0f * scale)) * turbulence;

			mx = (x1 + x2) / 2;
			my = (y1 + y2) / 2;

			if (mx == x1 && mx == x2 && my == y1 && my == y2)
				return true;

			if (mx != x1 || mx != x2) {
				ml = average(tl, bl);
				ml = displace(ml, amount);
				putPixel(x1, my, ml, pixels, stride);

				if (x1 != x2){
					mr = average(tr, br);
					mr = displace(mr, amount);
					putPixel(x2, my, mr, pixels, stride);
				}
			}

			if (my != y1 || my != y2){
				if (x1 != mx || my != y2){
					mb = average(bl, br);
					mb = displace(mb, amount);
					putPixel(mx, y2, mb, pixels, stride);
				}

				if (y1 != y2){
					mt = average(tl, tr);
					mt = displace(mt, amount);
					putPixel(mx, y1, mt, pixels, stride);
				}
			}

			if (y1 != y2 || x1 != x2) {
				mm = average(tl, br);
				t = average(bl, tr);
				mm = average(mm, t);
				mm = displace(mm, amount);
				putPixel(mx, my, mm, pixels, stride);
			}

			if (x2-x1 < 3 && y2-y1 < 3)
				return false;
			return true;
		}

		mx = (x1 + x2) / 2;
		my = (y1 + y2) / 2;

		doPixel(x1, y1, mx, my, pixels, stride, depth-1, scale+1);
		doPixel(x1, my, mx ,y2, pixels, stride, depth-1, scale+1);
		doPixel(mx, y1, x2 , my, pixels, stride, depth-1, scale+1);
		return doPixel(mx, my, x2, y2, pixels, stride, depth-1, scale+1);
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.WholeImageFilter#filterPixels(int, int, int[], java.awt.Rectangle)
	 */
	protected int[] filterPixels( int width, int height, int[] inPixels, Rectangle transformedSpace ) {
		int[] outPixels = new int[width * height];

		randomGenerator.setSeed(seed);

		int w1 = width-1;
		int h1 = height-1;
		putPixel(0, 0, randomRGB(inPixels, 0, 0), outPixels, width);
		putPixel(w1, 0, randomRGB(inPixels, w1, 0), outPixels, width);
		putPixel(0, h1, randomRGB(inPixels, 0, h1), outPixels, width);
		putPixel(w1, h1, randomRGB(inPixels, w1, h1), outPixels, width);
		putPixel(w1/2, h1/2, randomRGB(inPixels, w1/2, h1/2), outPixels, width);
		putPixel(0, h1/2, randomRGB(inPixels, 0, h1/2), outPixels, width);
		putPixel(w1, h1/2, randomRGB(inPixels, w1, h1/2), outPixels, width);
		putPixel(w1/2, 0, randomRGB(inPixels, w1/2, 0), outPixels, width);
		putPixel(w1/2, h1, randomRGB(inPixels, w1/2, h1), outPixels, width);

		int depth = 1;
		while (doPixel(0, 0, width-1, height-1, outPixels, width, depth, 0))
			depth++;

		if (useColormap && colormap != null) {
			int index = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					outPixels[index] = colormap.getColor((outPixels[index] & 0xff)/255.0f);
					index++;
				}
			}
		}
		return outPixels;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Texture/Plasma...";
	}
	
}
