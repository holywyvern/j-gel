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
 * An image histogram.
 */
public class Histogram {

	/** The Constant RED. */
	public static final int RED = 0;
	
	/** The Constant GREEN. */
	public static final int GREEN = 1;
	
	/** The Constant BLUE. */
	public static final int BLUE = 2;
	
	/** The Constant GRAY. */
	public static final int GRAY = 3;

	/** The histogram. */
	protected int[][] histogram;
	
	/** The num samples. */
	protected int numSamples;
	
	/** The min value. */
	protected int[] minValue;
	
	/** The max value. */
	protected int[] maxValue;
	
	/** The min frequency. */
	protected int[] minFrequency;
	
	/** The max frequency. */
	protected int[] maxFrequency;
	
	/** The mean. */
	protected float[] mean;
	
	/** The is gray. */
	protected boolean isGray;

	/**
	 * Instantiates a new histogram.
	 */
	public Histogram() {
		histogram = null;
		numSamples = 0;
		isGray = true;
		minValue = null;
		maxValue = null;
		minFrequency = null;
		maxFrequency = null;
		mean = null;
	}

	/**
	 * Instantiates a new histogram.
	 * 
	 * @param pixels
	 *            the pixels
	 * @param w
	 *            the w
	 * @param h
	 *            the h
	 * @param offset
	 *            the offset
	 * @param stride
	 *            the stride
	 */
	public Histogram(int[] pixels, int w, int h, int offset, int stride) {
		histogram = new int[3][256];
		minValue = new int[4];
		maxValue = new int[4];
		minFrequency = new int[3];
		maxFrequency = new int[3];
		mean = new float[3];

		numSamples = w*h;
		isGray = true;

		int index = 0;
		for (int y = 0; y < h; y++) {
			index = offset+y*stride;
			for (int x = 0; x < w; x++) {
				int rgb = pixels[index++];
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = rgb & 0xff;
				histogram[RED][r]++;
				histogram[GREEN][g]++;
				histogram[BLUE][b]++;
			}
		}

		for (int i = 0; i < 256; i++) {
			if (histogram[RED][i] != histogram[GREEN][i] || histogram[GREEN][i] != histogram[BLUE][i]) {
				isGray = false;
				break;
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 256; j++) {
				if (histogram[i][j] > 0) {
					minValue[i] = j;
					break;
				}
			}

			for (int j = 255; j >= 0; j--) {
				if (histogram[i][j] > 0) {
					maxValue[i] = j;
					break;
				}
			}

			minFrequency[i] = Integer.MAX_VALUE;
			maxFrequency[i] = 0;
			for (int j = 0; j < 256; j++) {
				minFrequency[i] = Math.min(minFrequency[i], histogram[i][j]);
				maxFrequency[i] = Math.max(maxFrequency[i], histogram[i][j]);
				mean[i] += (float)(j*histogram[i][j]);
			}
			mean[i] /= (float)numSamples;
		}
		minValue[GRAY] = Math.min(Math.min(minValue[RED], minValue[GREEN]), minValue[BLUE]);
		maxValue[GRAY] = Math.max(Math.max(maxValue[RED], maxValue[GREEN]), maxValue[BLUE]);
	}

	/**
	 * Checks if is gray.
	 * 
	 * @return true, if is gray
	 */
	public boolean isGray() {
		return isGray;
	}

	/**
	 * Gets the num samples.
	 * 
	 * @return the num samples
	 */
	public int getNumSamples() {
		return numSamples;
	}

	/**
	 * Gets the frequency.
	 * 
	 * @param value
	 *            the value
	 * @return the frequency
	 */
	public int getFrequency(int value) {
		if (numSamples > 0 && isGray && value >= 0 && value <= 255)
			return histogram[0][value];
		return -1;
	}

	/**
	 * Gets the frequency.
	 * 
	 * @param channel
	 *            the channel
	 * @param value
	 *            the value
	 * @return the frequency
	 */
	public int getFrequency(int channel, int value) {
		if (numSamples < 1 || channel < 0 || channel > 2 ||
		 value < 0 || value > 255)
			return -1;
		return histogram[channel][value];
	}

	/**
	 * Gets the min frequency.
	 * 
	 * @return the min frequency
	 */
	public int getMinFrequency() {
		if (numSamples > 0 && isGray)
			return minFrequency[0];
		return -1;
	}

	/**
	 * Gets the min frequency.
	 * 
	 * @param channel
	 *            the channel
	 * @return the min frequency
	 */
	public int getMinFrequency(int channel) {
		if (numSamples < 1 || channel < 0 || channel > 2)
			return -1;
		return minFrequency[channel];
	}


	/**
	 * Gets the max frequency.
	 * 
	 * @return the max frequency
	 */
	public int getMaxFrequency() {
		if (numSamples > 0 && isGray)
			return maxFrequency[0];
		return -1;
	}

	/**
	 * Gets the max frequency.
	 * 
	 * @param channel
	 *            the channel
	 * @return the max frequency
	 */
	public int getMaxFrequency(int channel) {
		if (numSamples < 1 || channel < 0 || channel > 2)
			return -1;
		return maxFrequency[channel];
	}


	/**
	 * Gets the min value.
	 * 
	 * @return the min value
	 */
	public int getMinValue() {
		if (numSamples > 0 && isGray)
			return minValue[0];
		return -1;
	}

	/**
	 * Gets the min value.
	 * 
	 * @param channel
	 *            the channel
	 * @return the min value
	 */
	public int getMinValue(int channel) {
		return minValue[channel];
	}

	/**
	 * Gets the max value.
	 * 
	 * @return the max value
	 */
	public int getMaxValue() {
		if (numSamples > 0 && isGray)
			return maxValue[0];
		return -1;
	}

	/**
	 * Gets the max value.
	 * 
	 * @param channel
	 *            the channel
	 * @return the max value
	 */
	public int getMaxValue(int channel) {
		return maxValue[channel];
	}

	/**
	 * Gets the mean value.
	 * 
	 * @return the mean value
	 */
	public float getMeanValue() {
		if (numSamples > 0 && isGray)
			return mean[0];
		return -1.0F;
	}

	/**
	 * Gets the mean value.
	 * 
	 * @param channel
	 *            the channel
	 * @return the mean value
	 */
	public float getMeanValue(int channel) {
		if (numSamples > 0 && RED <= channel && channel <= BLUE)
			return mean[channel];
		return -1.0F;
	}


}
