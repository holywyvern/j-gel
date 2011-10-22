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
import com.jhlabs.math.*;

// TODO: Auto-generated Javadoc
/**
 * The superclass for some of the filters which work on binary images.
 */
public abstract class BinaryFilter extends WholeImageFilter {

	/** The new color. */
	protected int newColor = 0xff000000;
	
	/** The black function. */
	protected BinaryFunction blackFunction = new BlackFunction();
	
	/** The iterations. */
	protected int iterations = 1;
	
	/** The colormap. */
	protected Colormap colormap;

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
	 * Sets the new color.
	 * 
	 * @param newColor
	 *            the new new color
	 */
	public void setNewColor(int newColor) {
		this.newColor = newColor;
	}

	/**
	 * Gets the new color.
	 * 
	 * @return the new color
	 */
	public int getNewColor() {
		return newColor;
	}

	/**
	 * Sets the black function.
	 * 
	 * @param blackFunction
	 *            the new black function
	 */
	public void setBlackFunction(BinaryFunction blackFunction) {
		this.blackFunction = blackFunction;
	}

	/**
	 * Gets the black function.
	 * 
	 * @return the black function
	 */
	public BinaryFunction getBlackFunction() {
		return blackFunction;
	}

}

