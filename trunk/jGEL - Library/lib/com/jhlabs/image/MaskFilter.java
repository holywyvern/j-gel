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
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * Applies a bit mask to each ARGB pixel of an image. You can use this for, say, masking out the red channel.
 */
public class MaskFilter extends PointFilter {

	/** The mask. */
	private int mask;

	/**
	 * Instantiates a new mask filter.
	 */
	public MaskFilter() {
		this(0xff00ffff);
	}

	/**
	 * Instantiates a new mask filter.
	 * 
	 * @param mask
	 *            the mask
	 */
	public MaskFilter(int mask) {
		canFilterIndexColorModel = true;
		setMask(mask);
	}

	/**
	 * Sets the mask.
	 * 
	 * @param mask
	 *            the new mask
	 */
	public void setMask(int mask) {
		this.mask = mask;
	}

	/**
	 * Gets the mask.
	 * 
	 * @return the mask
	 */
	public int getMask() {
		return mask;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		return rgb & mask;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Mask";
	}

}
