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
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ScratchFilter.
 */
public class ScratchFilter extends AbstractBufferedImageOp {
    
    /** The density. */
    private float density = 0.1f;
    
    /** The angle. */
    private float angle;
    
    /** The angle variation. */
    private float angleVariation = 1.0f;
    
    /** The width. */
    private float width = 0.5f;
    
    /** The length. */
    private float length = 0.5f;
    
    /** The color. */
    private int color = 0xffffffff;
    
    /** The seed. */
    private int seed = 0;

    /**
	 * Instantiates a new scratch filter.
	 */
    public ScratchFilter() {
	}
	
	/**
	 * Sets the angle.
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle( float angle ) {
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
	 * Sets the angle variation.
	 * 
	 * @param angleVariation
	 *            the new angle variation
	 */
	public void setAngleVariation( float angleVariation ) {
		this.angleVariation = angleVariation;
	}

	/**
	 * Gets the angle variation.
	 * 
	 * @return the angle variation
	 */
	public float getAngleVariation() {
		return angleVariation;
	}
	
	/**
	 * Sets the density.
	 * 
	 * @param density
	 *            the new density
	 */
	public void setDensity( float density ) {
		this.density = density;
	}

	/**
	 * Gets the density.
	 * 
	 * @return the density
	 */
	public float getDensity() {
		return density;
	}
	
	/**
	 * Sets the length.
	 * 
	 * @param length
	 *            the new length
	 */
	public void setLength( float length ) {
		this.length = length;
	}

	/**
	 * Gets the length.
	 * 
	 * @return the length
	 */
	public float getLength() {
		return length;
	}
	
	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the new width
	 */
	public void setWidth( float width ) {
		this.width = width;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Sets the color.
	 * 
	 * @param color
	 *            the new color
	 */
	public void setColor( int color ) {
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
	 * Sets the seed.
	 * 
	 * @param seed
	 *            the new seed
	 */
	public void setSeed( int seed ) {
		this.seed = seed;
	}

	/**
	 * Gets the seed.
	 * 
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}

    /* (non-Javadoc)
     * @see java.awt.image.BufferedImageOp#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
     */
    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        if ( dst == null )
            dst = createCompatibleDestImage( src, null );

        int width = src.getWidth();
        int height = src.getHeight();
        int numScratches = (int)(density * width * height / 100);
        float l = length * width;
        Random random = new Random( seed );
        Graphics2D g = dst.createGraphics();
        g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g.setColor( new Color( color ) );
        g.setStroke( new BasicStroke( this.width ) );
        for ( int i = 0; i < numScratches; i++ ) {
            float x = width * random.nextFloat();
            float y = height * random.nextFloat();
            float a = angle + ImageMath.TWO_PI * (angleVariation * (random.nextFloat() - 0.5f));
            float s = (float)Math.sin( a ) * l;
            float c = (float)Math.cos( a ) * l;
            float x1 = x-c;
            float y1 = y-s;
            float x2 = x+c;
            float y2 = y+s;
            g.drawLine( (int)x1, (int)y1, (int)x2, (int)y2 );
        }
        g.dispose();
        
        return dst;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Render/Scratches...";
	}
}
