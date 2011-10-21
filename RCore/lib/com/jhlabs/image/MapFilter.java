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
 * The Class MapFilter.
 */
public class MapFilter extends TransformFilter {

	/** The x map function. */
	private Function2D xMapFunction;
	
	/** The y map function. */
	private Function2D yMapFunction;

	/**
	 * Instantiates a new map filter.
	 */
	public MapFilter() {
	}
	
	/**
	 * Sets the x map function.
	 * 
	 * @param xMapFunction
	 *            the new x map function
	 */
	public void setXMapFunction(Function2D xMapFunction) {
		this.xMapFunction = xMapFunction;
	}

	/**
	 * Gets the x map function.
	 * 
	 * @return the x map function
	 */
	public Function2D getXMapFunction() {
		return xMapFunction;
	}

	/**
	 * Sets the y map function.
	 * 
	 * @param yMapFunction
	 *            the new y map function
	 */
	public void setYMapFunction(Function2D yMapFunction) {
		this.yMapFunction = yMapFunction;
	}

	/**
	 * Gets the y map function.
	 * 
	 * @return the y map function
	 */
	public Function2D getYMapFunction() {
		return yMapFunction;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.TransformFilter#transformInverse(int, int, float[])
	 */
	protected void transformInverse(int x, int y, float[] out) {
		float xMap, yMap;
		xMap = xMapFunction.evaluate(x, y);
		yMap = yMapFunction.evaluate(x, y);
		out[0] = xMap * transformedSpace.width;
		out[1] = yMap * transformedSpace.height;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Distort/Map Coordinates...";
	}
}
