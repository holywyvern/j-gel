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
import java.awt.geom.*;
import java.awt.image.*;
import com.jhlabs.composite.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ShineFilter.
 */
public class ShineFilter extends AbstractBufferedImageOp {
	
	/** The radius. */
	private float radius = 5;
	
	/** The angle. */
	private float angle = (float)Math.PI*7/4;
	
	/** The distance. */
	private float distance = 5;
	
	/** The bevel. */
	private float bevel = 0.5f;
	
	/** The shadow only. */
	private boolean shadowOnly = false;
	
	/** The shine color. */
	private int shineColor = 0xffffffff;
	
	/** The brightness. */
	private float brightness = 0.2f;
	
	/** The softness. */
	private float softness = 0;

	/**
	 * Instantiates a new shine filter.
	 */
	public ShineFilter() {
	}

	/**
	 * Sets the angle.
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}

	/**
	 * Gets the angle.
	 * 
	 * @return the angle
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * Sets the distance.
	 * 
	 * @param distance
	 *            the new distance
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}

	/**
	 * Gets the distance.
	 * 
	 * @return the distance
	 */
	public float getDistance() {
		return distance;
	}

	/**
	 * Set the radius of the kernel, and hence the amount of blur. The bigger the radius, the longer this filter will take.
	 * @param radius the radius of the blur in pixels.
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	/**
	 * Get the radius of the kernel.
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * Sets the bevel.
	 * 
	 * @param bevel
	 *            the new bevel
	 */
	public void setBevel(float bevel) {
		this.bevel = bevel;
	}

	/**
	 * Gets the bevel.
	 * 
	 * @return the bevel
	 */
	public float getBevel() {
		return bevel;
	}

	/**
	 * Sets the shine color.
	 * 
	 * @param shineColor
	 *            the new shine color
	 */
	public void setShineColor(int shineColor) {
		this.shineColor = shineColor;
	}

	/**
	 * Gets the shine color.
	 * 
	 * @return the shine color
	 */
	public int getShineColor() {
		return shineColor;
	}

	/**
	 * Sets the shadow only.
	 * 
	 * @param shadowOnly
	 *            the new shadow only
	 */
	public void setShadowOnly(boolean shadowOnly) {
		this.shadowOnly = shadowOnly;
	}

	/**
	 * Gets the shadow only.
	 * 
	 * @return the shadow only
	 */
	public boolean getShadowOnly() {
		return shadowOnly;
	}

	/**
	 * Sets the brightness.
	 * 
	 * @param brightness
	 *            the new brightness
	 */
	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}
	
	/**
	 * Gets the brightness.
	 * 
	 * @return the brightness
	 */
	public float getBrightness() {
		return brightness;
	}
	
	/**
	 * Sets the softness.
	 * 
	 * @param softness
	 *            the new softness
	 */
	public void setSoftness(float softness) {
		this.softness = softness;
	}

	/**
	 * Gets the softness.
	 * 
	 * @return the softness
	 */
	public float getSoftness() {
		return softness;
	}

    /* (non-Javadoc)
     * @see java.awt.image.BufferedImageOp#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
     */
    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        int width = src.getWidth();
        int height = src.getHeight();

        if ( dst == null )
            dst = createCompatibleDestImage( src, null );

		float xOffset = distance*(float)Math.cos(angle);
		float yOffset = -distance*(float)Math.sin(angle);

        BufferedImage matte = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ErodeAlphaFilter s = new ErodeAlphaFilter( bevel * 10, 0.75f, 0.1f );
        matte = s.filter( src, null );

        BufferedImage shineLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = shineLayer.createGraphics();
		g.setColor( new Color( shineColor ) );
        g.fillRect( 0, 0, width, height );
        g.setComposite( AlphaComposite.DstIn );
        g.drawRenderedImage( matte, null );
        g.setComposite( AlphaComposite.DstOut );
        g.translate( xOffset, yOffset );
        g.drawRenderedImage( matte, null );
		g.dispose();
        shineLayer = new GaussianFilter( radius ).filter( shineLayer, null );
        shineLayer = new RescaleFilter( 3*brightness ).filter( shineLayer, shineLayer );

		g = dst.createGraphics();
        g.drawRenderedImage( src, null );
        g.setComposite( new AddComposite( 1.0f ) );
        g.drawRenderedImage( shineLayer, null );
		g.dispose();

        return dst;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Stylize/Shine...";
	}
}
