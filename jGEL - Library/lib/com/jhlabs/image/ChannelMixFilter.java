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
 * A filter which allows the red, green and blue channels of an image to be mixed into each other.
 */
public class ChannelMixFilter extends PointFilter {
	
	/** The green red. */
	private int blueGreen, redBlue, greenRed;
	
	/** The into b. */
	private int intoR, intoG, intoB;
	
	/**
	 * Instantiates a new channel mix filter.
	 */
	public ChannelMixFilter() {
		canFilterIndexColorModel = true;
	}

	/**
	 * Sets the blue green.
	 * 
	 * @param blueGreen
	 *            the new blue green
	 */
	public void setBlueGreen(int blueGreen) {
		this.blueGreen = blueGreen;
	}

	/**
	 * Gets the blue green.
	 * 
	 * @return the blue green
	 */
	public int getBlueGreen() {
		return blueGreen;
	}

	/**
	 * Sets the red blue.
	 * 
	 * @param redBlue
	 *            the new red blue
	 */
	public void setRedBlue(int redBlue) {
		this.redBlue = redBlue;
	}

	/**
	 * Gets the red blue.
	 * 
	 * @return the red blue
	 */
	public int getRedBlue() {
		return redBlue;
	}

	/**
	 * Sets the green red.
	 * 
	 * @param greenRed
	 *            the new green red
	 */
	public void setGreenRed(int greenRed) {
		this.greenRed = greenRed;
	}

	/**
	 * Gets the green red.
	 * 
	 * @return the green red
	 */
	public int getGreenRed() {
		return greenRed;
	}

	/**
	 * Sets the into r.
	 * 
	 * @param intoR
	 *            the new into r
	 */
	public void setIntoR(int intoR) {
		this.intoR = intoR;
	}

	/**
	 * Gets the into r.
	 * 
	 * @return the into r
	 */
	public int getIntoR() {
		return intoR;
	}

	/**
	 * Sets the into g.
	 * 
	 * @param intoG
	 *            the new into g
	 */
	public void setIntoG(int intoG) {
		this.intoG = intoG;
	}

	/**
	 * Gets the into g.
	 * 
	 * @return the into g
	 */
	public int getIntoG() {
		return intoG;
	}

	/**
	 * Sets the into b.
	 * 
	 * @param intoB
	 *            the new into b
	 */
	public void setIntoB(int intoB) {
		this.intoB = intoB;
	}

	/**
	 * Gets the into b.
	 * 
	 * @return the into b
	 */
	public int getIntoB() {
		return intoB;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		int a = rgb & 0xff000000;
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		int nr = PixelUtils.clamp((intoR * (blueGreen*g+(255-blueGreen)*b)/255 + (255-intoR)*r)/255);
		int ng = PixelUtils.clamp((intoG * (redBlue*b+(255-redBlue)*r)/255 + (255-intoG)*g)/255);
		int nb = PixelUtils.clamp((intoB * (greenRed*r+(255-greenRed)*g)/255 + (255-intoB)*b)/255);
		return a | (nr << 16) | (ng << 8) | nb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Colors/Mix Channels...";
	}
}

