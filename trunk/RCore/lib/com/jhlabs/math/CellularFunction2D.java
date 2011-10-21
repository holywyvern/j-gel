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

import java.awt.image.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CellularFunction2D.
 */
public class CellularFunction2D implements Function2D {

	/** The distance power. */
	public float distancePower = 2;
	
	/** The cells. */
	public boolean cells = false;
	
	/** The angular. */
	public boolean angular = false;
	
	/** The coefficients. */
	private float[] coefficients = { 1, 0, 0, 0 };
	
	/** The random. */
	private Random random = new Random();
	
	/** The results. */
	private Point[] results = null;
	
	/**
	 * Instantiates a new cellular function2 d.
	 */
	public CellularFunction2D() {
		results = new Point[2];
		for (int j = 0; j < results.length; j++)
			results[j] = new Point();
	}
	
	/**
	 * Sets the coefficient.
	 * 
	 * @param c
	 *            the c
	 * @param v
	 *            the v
	 */
	public void setCoefficient(int c, float v) {
		coefficients[c] = v;
	}
	
	/**
	 * Gets the coefficient.
	 * 
	 * @param c
	 *            the c
	 * @return the coefficient
	 */
	public float getCoefficient(int c) {
		return coefficients[c];
	}
	
	/**
	 * The Class Point.
	 */
	class Point {
		
		/** The index. */
		int index;
		
		/** The y. */
		float x, y;
		
		/** The distance. */
		float distance;
	}
	
	/**
	 * Check cube.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param cubeX
	 *            the cube x
	 * @param cubeY
	 *            the cube y
	 * @param results
	 *            the results
	 * @return the float
	 */
	private float checkCube(float x, float y, int cubeX, int cubeY, Point[] results) {
		random.setSeed(571*cubeX + 23*cubeY);
		int numPoints = 3 + random.nextInt() % 4;
		numPoints = 4;

		for (int i = 0; i < numPoints; i++) {
			float px = random.nextFloat();
			float py = random.nextFloat();
			float dx = Math.abs(x-px);
			float dy = Math.abs(y-py);
			float d;
			if (distancePower == 1.0f)
				d = dx + dy;
			else if (distancePower == 2.0f)
				d = (float)Math.sqrt(dx*dx + dy*dy);
			else
				d = (float)Math.pow(Math.pow(dx, distancePower) + Math.pow(dy, distancePower), 1/distancePower);

			// Insertion sort
			for (int j = 0; j < results.length; j++) {
				if (results[j].distance == Double.POSITIVE_INFINITY) {
					Point last = results[j];
					last.distance = d;
					last.x = px;
					last.y = py;
					results[j] = last;
					break;
				} else if (d < results[j].distance) {
					Point last = results[results.length-1];
					for (int k = results.length-1; k > j; k--)
						results[k] = results[k-1];
					last.distance = d;
					last.x = px;
					last.y = py;
					results[j] = last;
					break;
				}
			}
		}
		return results[1].distance;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.math.Function2D#evaluate(float, float)
	 */
	public float evaluate(float x, float y) {
		for (int j = 0; j < results.length; j++)
			results[j].distance = Float.POSITIVE_INFINITY;

		int ix = (int)x;
		int iy = (int)y;
		float fx = x-ix;
		float fy = y-iy;

		float d = checkCube(fx, fy, ix, iy, results);
		if (d > fy)
			d = checkCube(fx, fy+1, ix, iy-1, results);
		if (d > 1-fy)
			d = checkCube(fx, fy-1, ix, iy+1, results);
		if (d > fx) {
			checkCube(fx+1, fy, ix-1, iy, results);
			if (d > fy)
				d = checkCube(fx+1, fy+1, ix-1, iy-1, results);
			if (d > 1-fy)
				d = checkCube(fx+1, fy-1, ix-1, iy+1, results);
		}
		if (d > 1-fx) {
			d = checkCube(fx-1, fy, ix+1, iy, results);
			if (d > fy)
				d = checkCube(fx-1, fy+1, ix+1, iy-1, results);
			if (d > 1-fy)
				d = checkCube(fx-1, fy-1, ix+1, iy+1, results);
		}

		float t = 0;
		for (int i = 0; i < 2; i++)
			t += coefficients[i] * results[i].distance;
		if (angular)
			t += Math.atan2(fy-results[0].y, fx-results[0].x) / (2*Math.PI) + 0.5;
		return t;
	}
	
}
