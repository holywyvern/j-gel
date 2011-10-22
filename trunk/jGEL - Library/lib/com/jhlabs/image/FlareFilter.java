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

import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import com.jhlabs.math.*;

// TODO: Auto-generated Javadoc
/**
 * An experimental filter for rendering lens flares.
 */
public class FlareFilter extends PointFilter {

	/** The rays. */
	private int rays = 50;
	
	/** The radius. */
	private float radius;
	
	/** The base amount. */
	private float baseAmount = 1.0f;
	
	/** The ring amount. */
	private float ringAmount = 0.2f;
	
	/** The ray amount. */
	private float rayAmount = 0.1f;
	
	/** The color. */
	private int color = 0xffffffff;
	
	/** The height. */
	private int width, height;
	
	/** The centre y. */
	private float centreX = 0.5f, centreY = 0.5f;
	
	/** The ring width. */
	private float ringWidth = 1.6f;
	
	/** The linear. */
	private float linear = 0.03f;
	
	/** The gauss. */
	private float gauss = 0.006f;
	
	/** The mix. */
	private float mix = 0.50f;
	
	/** The falloff. */
	private float falloff = 6.0f;
	
	/** The sigma. */
	private float sigma;

	/** The icentre y. */
	private float icentreX, icentreY;

	/**
	 * Instantiates a new flare filter.
	 */
	public FlareFilter() {
		setRadius(50.0f);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color
	 *            the new color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets the ring width.
	 * 
	 * @param ringWidth
	 *            the new ring width
	 */
	public void setRingWidth(float ringWidth) {
		this.ringWidth = ringWidth;
	}

	/**
	 * Gets the ring width.
	 * 
	 * @return the ring width
	 */
	public float getRingWidth() {
		return ringWidth;
	}
	
	/**
	 * Sets the base amount.
	 * 
	 * @param baseAmount
	 *            the new base amount
	 */
	public void setBaseAmount(float baseAmount) {
		this.baseAmount = baseAmount;
	}

	/**
	 * Gets the base amount.
	 * 
	 * @return the base amount
	 */
	public float getBaseAmount() {
		return baseAmount;
	}

	/**
	 * Sets the ring amount.
	 * 
	 * @param ringAmount
	 *            the new ring amount
	 */
	public void setRingAmount(float ringAmount) {
		this.ringAmount = ringAmount;
	}

	/**
	 * Gets the ring amount.
	 * 
	 * @return the ring amount
	 */
	public float getRingAmount() {
		return ringAmount;
	}

	/**
	 * Sets the ray amount.
	 * 
	 * @param rayAmount
	 *            the new ray amount
	 */
	public void setRayAmount(float rayAmount) {
		this.rayAmount = rayAmount;
	}

	/**
	 * Gets the ray amount.
	 * 
	 * @return the ray amount
	 */
	public float getRayAmount() {
		return rayAmount;
	}

	/**
	 * Sets the centre.
	 * 
	 * @param centre
	 *            the new centre
	 */
	public void setCentre( Point2D centre ) {
		this.centreX = (float)centre.getX();
		this.centreY = (float)centre.getY();
	}

	/**
	 * Gets the centre.
	 * 
	 * @return the centre
	 */
	public Point2D getCentre() {
		return new Point2D.Float( centreX, centreY );
	}
	
	/**
	 * Set the radius of the effect.
	 * @param radius the radius
     * @min-value 0
     * @see #getRadius
	 */
	public void setRadius(float radius) {
		this.radius = radius;
		sigma = radius/3;
	}

	/**
	 * Get the radius of the effect.
	 * @return the radius
     * @see #setRadius
	 */
	public float getRadius() {
		return radius;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#setDimensions(int, int)
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		icentreX = centreX*width;
		icentreY = centreY*height;
		super.setDimensions(width, height);
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		float dx = x-icentreX;
		float dy = y-icentreY;
		float distance = (float)Math.sqrt(dx*dx+dy*dy);
		float a = (float)Math.exp(-distance*distance*gauss)*mix + (float)Math.exp(-distance*linear)*(1-mix);
		float ring;

		a *= baseAmount;

		if (distance > radius + ringWidth)
			a = ImageMath.lerp((distance - (radius + ringWidth))/falloff, a, 0);

		if (distance < radius - ringWidth || distance > radius + ringWidth)
			ring = 0;
		else {
	        ring = Math.abs(distance-radius)/ringWidth;
	        ring = 1 - ring*ring*(3 - 2*ring);
	        ring *= ringAmount;
		}

		a += ring;

		float angle = (float)Math.atan2(dx, dy)+ImageMath.PI;
		angle = (ImageMath.mod(angle/ImageMath.PI*17 + 1.0f + Noise.noise1(angle*10), 1.0f) - 0.5f)*2;
		angle = Math.abs(angle);
		angle = (float)Math.pow(angle, 5.0);

		float b = rayAmount * angle / (1 + distance*0.1f);
		a += b;

		a = ImageMath.clamp(a, 0, 1);
		return ImageMath.mixColors(a, rgb, color);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Stylize/Flare...";
	}
}
