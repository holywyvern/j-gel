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
 * The Class QuiltFilter.
 */
public class QuiltFilter extends WholeImageFilter {

	/** The random generator. */
	private Random randomGenerator;
	
	/** The seed. */
	private long seed = 567;
	
	/** The iterations. */
	private int iterations = 25000;
	
	/** The a. */
	private float a = -0.59f;
	
	/** The b. */
	private float b = 0.2f;
	
	/** The c. */
	private float c = 0.1f;
	
	/** The d. */
	private float d = 0;
	
	/** The k. */
	private int k = 0;
	
	/** The colormap. */
	private Colormap colormap = new LinearColormap();

	/**
	 * Instantiates a new quilt filter.
	 */
	public QuiltFilter() {
		randomGenerator = new Random();
	}

	/**
	 * Randomize.
	 */
	public void randomize() {
		seed = new Date().getTime();
		randomGenerator.setSeed(seed);
		a = randomGenerator.nextFloat();
		b = randomGenerator.nextFloat();
		c = randomGenerator.nextFloat();
		d = randomGenerator.nextFloat();
		k = randomGenerator.nextInt() % 20 - 10;
	}
	
	/**
	 * Set the number of iterations the effect is performed.
	 * @param iterations the number of iterations
     * @min-value 0
     * @see #getIterations
	 */
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	/**
	 * Get the number of iterations the effect is performed.
	 * @return the number of iterations
     * @see #setIterations
	 */
	public int getIterations() {
		return iterations;
	}

	/**
	 * Sets the a.
	 * 
	 * @param a
	 *            the new a
	 */
	public void setA(float a) {
		this.a = a;
	}

	/**
	 * Gets the a.
	 * 
	 * @return the a
	 */
	public float getA() {
		return a;
	}

	/**
	 * Sets the b.
	 * 
	 * @param b
	 *            the new b
	 */
	public void setB(float b) {
		this.b = b;
	}

	/**
	 * Gets the b.
	 * 
	 * @return the b
	 */
	public float getB() {
		return b;
	}

	/**
	 * Sets the c.
	 * 
	 * @param c
	 *            the new c
	 */
	public void setC(float c) {
		this.c = c;
	}

	/**
	 * Gets the c.
	 * 
	 * @return the c
	 */
	public float getC() {
		return c;
	}

	/**
	 * Sets the d.
	 * 
	 * @param d
	 *            the new d
	 */
	public void setD(float d) {
		this.d = d;
	}

	/**
	 * Gets the d.
	 * 
	 * @return the d
	 */
	public float getD() {
		return d;
	}

	/**
	 * Sets the k.
	 * 
	 * @param k
	 *            the new k
	 */
	public void setK(int k) {
		this.k = k;
	}

	/**
	 * Gets the k.
	 * 
	 * @return the k
	 */
	public int getK() {
		return k;
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
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.WholeImageFilter#filterPixels(int, int, int[], java.awt.Rectangle)
	 */
	protected int[] filterPixels( int width, int height, int[] inPixels, Rectangle transformedSpace ) {
		int[] outPixels = new int[width * height];

		int i = 0;
		int max = 0;

		float x = 0.1f;
		float y = 0.3f;
		
		for (int n = 0; n < 20; n++) {
			float mx = ImageMath.PI*x;
			float my = ImageMath.PI*y;
			float smx2 = (float)Math.sin(2*mx);
			float smy2 = (float)Math.sin(2*my);
			float x1 = (float)(a*smx2 + b*smx2*Math.cos(2*my) +
				c*Math.sin(4*mx) + d*Math.sin(6*mx)*Math.cos(4*my) + k*x);
			x1 = x1 >= 0 ? x1 - (int)x1 : x1 - (int)x1 + 1;

			float y1 = (float)(a*smy2 + b*smy2*Math.cos(2*mx) +
				c*Math.sin(4*my) + d*Math.sin(6*my)*Math.cos(4*mx) + k*y);
			y1 = y1 >= 0 ? y1 - (int)y1 : y1 - (int)y1 + 1;
			x = x1;
			y = y1;
		}

		for (int n = 0; n < iterations; n++) {
			float mx = ImageMath.PI*x;
			float my = ImageMath.PI*y;
			float x1 = (float)(a*Math.sin(2*mx) + b*Math.sin(2*mx)*Math.cos(2*my) +
				c*Math.sin(4*mx) + d*Math.sin(6*mx)*Math.cos(4*my) + k*x);
			x1 = x1 >= 0 ? x1 - (int)x1 : x1 - (int)x1 + 1;

			float y1 = (float)(a*Math.sin(2*my) + b*Math.sin(2*my)*Math.cos(2*mx) +
				c*Math.sin(4*my) + d*Math.sin(6*my)*Math.cos(4*mx) + k*y);
			y1 = y1 >= 0 ? y1 - (int)y1 : y1 - (int)y1 + 1;
			x = x1;
			y = y1;
			int ix = (int)(width*x);
			int iy = (int)(height*y);
			if (ix >= 0 && ix < width && iy >= 0 && iy < height) {
				int t = outPixels[width*iy+ix]++;
				if (t > max)
					max = t;
			}
		}

		if (colormap != null) {
			int index = 0;
			for (y = 0; y < height; y++) {
				for (x = 0; x < width; x++) {
					outPixels[index] = colormap.getColor(outPixels[index] / (float)max);
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
		return "Texture/Chaotic Quilt...";
	}
	
}
