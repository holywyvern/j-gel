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

import java.awt.image.*;
import java.util.*;
import com.jhlabs.math.*;

// TODO: Auto-generated Javadoc
/**
 * A filter which produces textures from fractal Brownian motion.
 */
public class FBMFilter extends PointFilter implements Cloneable {

	/** The Constant NOISE. */
	public final static int NOISE = 0;
	
	/** The Constant RIDGED. */
	public final static int RIDGED = 1;
	
	/** The Constant VLNOISE. */
	public final static int VLNOISE = 2;
	
	/** The Constant SCNOISE. */
	public final static int SCNOISE = 3;
	
	/** The Constant CELLULAR. */
	public final static int CELLULAR = 4;

	/** The scale. */
	private float scale = 32;
	
	/** The stretch. */
	private float stretch = 1.0f;
	
	/** The angle. */
	private float angle = 0.0f;
	
	/** The amount. */
	private float amount = 1.0f;
	
	/** The H. */
	private float H = 1.0f;
	
	/** The octaves. */
	private float octaves = 4.0f;
	
	/** The lacunarity. */
	private float lacunarity = 2.0f;
	
	/** The gain. */
	private float gain = 0.5f;
	
	/** The bias. */
	private float bias = 0.5f;
	
	/** The operation. */
	private int operation;
	
	/** The m00. */
	private float m00 = 1.0f;
	
	/** The m01. */
	private float m01 = 0.0f;
	
	/** The m10. */
	private float m10 = 0.0f;
	
	/** The m11. */
	private float m11 = 1.0f;
	
	/** The min. */
	private float min;
	
	/** The max. */
	private float max;
	
	/** The colormap. */
	private Colormap colormap = new Gradient();
	
	/** The ridged. */
	private boolean ridged;
	
	/** The bm. */
	private FBM fBm;
	
	/** The random. */
	protected Random random = new Random();
	
	/** The basis type. */
	private int basisType = NOISE;
	
	/** The basis. */
	private Function2D basis;

	/**
	 * Instantiates a new fBM filter.
	 */
	public FBMFilter() {
		setBasisType(NOISE);
	}

	/**
	 * Set the amount of effect.
	 * @param amount the amount
     * @min-value 0
     * @max-value 1
     * @see #getAmount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * Get the amount of texture.
	 * @return the amount
     * @see #setAmount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation
	 *            the new operation
	 */
	public void setOperation(int operation) {
		this.operation = operation;
	}
	
	/**
	 * Gets the operation.
	 * 
	 * @return the operation
	 */
	public int getOperation() {
		return operation;
	}
	
	/**
     * Specifies the scale of the texture.
     * @param scale the scale of the texture.
     * @min-value 1
     * @max-value 300+
     * @see #getScale
     */
	public void setScale(float scale) {
		this.scale = scale;
	}

	/**
     * Returns the scale of the texture.
     * @return the scale of the texture.
     * @see #setScale
     */
	public float getScale() {
		return scale;
	}

	/**
     * Specifies the stretch factor of the texture.
     * @param stretch the stretch factor of the texture.
     * @min-value 1
     * @max-value 50+
     * @see #getStretch
     */
	public void setStretch(float stretch) {
		this.stretch = stretch;
	}

	/**
     * Returns the stretch factor of the texture.
     * @return the stretch factor of the texture.
     * @see #setStretch
     */
	public float getStretch() {
		return stretch;
	}

	/**
     * Specifies the angle of the texture.
     * @param angle the angle of the texture.
     * @angle
     * @see #getAngle
     */
	public void setAngle(float angle) {
		this.angle = angle;
		float cos = (float)Math.cos(this.angle);
		float sin = (float)Math.sin(this.angle);
		m00 = cos;
		m01 = sin;
		m10 = -sin;
		m11 = cos;
	}

	/**
     * Returns the angle of the texture.
     * @return the angle of the texture.
     * @see #setAngle
     */
	public float getAngle() {
		return angle;
	}

	/**
	 * Sets the octaves.
	 * 
	 * @param octaves
	 *            the new octaves
	 */
	public void setOctaves(float octaves) {
		this.octaves = octaves;
	}

	/**
	 * Gets the octaves.
	 * 
	 * @return the octaves
	 */
	public float getOctaves() {
		return octaves;
	}

	/**
	 * Sets the h.
	 * 
	 * @param H
	 *            the new h
	 */
	public void setH(float H) {
		this.H = H;
	}

	/**
	 * Gets the h.
	 * 
	 * @return the h
	 */
	public float getH() {
		return H;
	}

	/**
	 * Sets the lacunarity.
	 * 
	 * @param lacunarity
	 *            the new lacunarity
	 */
	public void setLacunarity(float lacunarity) {
		this.lacunarity = lacunarity;
	}

	/**
	 * Gets the lacunarity.
	 * 
	 * @return the lacunarity
	 */
	public float getLacunarity() {
		return lacunarity;
	}

	/**
	 * Sets the gain.
	 * 
	 * @param gain
	 *            the new gain
	 */
	public void setGain(float gain) {
		this.gain = gain;
	}

	/**
	 * Gets the gain.
	 * 
	 * @return the gain
	 */
	public float getGain() {
		return gain;
	}

	/**
	 * Sets the bias.
	 * 
	 * @param bias
	 *            the new bias
	 */
	public void setBias(float bias) {
		this.bias = bias;
	}

	/**
	 * Gets the bias.
	 * 
	 * @return the bias
	 */
	public float getBias() {
		return bias;
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
	 * Sets the basis type.
	 * 
	 * @param basisType
	 *            the new basis type
	 */
	public void setBasisType(int basisType) {
		this.basisType = basisType;
		switch (basisType) {
		default:
		case NOISE:
			basis = new Noise();
			break;
		case RIDGED:
			basis = new RidgedFBM();
			break;
		case VLNOISE:
			basis = new VLNoise();
			break;
		case SCNOISE:
			basis = new SCNoise();
			break;
		case CELLULAR:
			basis = new CellularFunction2D();
			break;
		}
	}

	/**
	 * Gets the basis type.
	 * 
	 * @return the basis type
	 */
	public int getBasisType() {
		return basisType;
	}

	/**
	 * Sets the basis.
	 * 
	 * @param basis
	 *            the new basis
	 */
	public void setBasis(Function2D basis) {
		this.basis = basis;
	}

	/**
	 * Gets the basis.
	 * 
	 * @return the basis
	 */
	public Function2D getBasis() {
		return basis;
	}

	/**
	 * Make fbm.
	 * 
	 * @param H
	 *            the h
	 * @param lacunarity
	 *            the lacunarity
	 * @param octaves
	 *            the octaves
	 * @return the fBM
	 */
	protected FBM makeFBM(float H, float lacunarity, float octaves) {
		FBM fbm = new FBM(H, lacunarity, octaves, basis);
		float[] minmax = Noise.findRange(fbm, null);
		min = minmax[0];
		max = minmax[1];
		return fbm;
	}
	
	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
	 */
	public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
		fBm = makeFBM(H, lacunarity, octaves);
		return super.filter( src, dst );
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {
		float nx = m00*x + m01*y;
		float ny = m10*x + m11*y;
		nx /= scale;
		ny /= scale * stretch;
		float f = fBm.evaluate(nx, ny);
		// Normalize to 0..1
		f = (f-min)/(max-min);
		f = ImageMath.gain(f, gain);
		f = ImageMath.bias(f, bias);
		f *= amount;
		int a = rgb & 0xff000000;
		int v;
		if (colormap != null)
			v = colormap.getColor(f);
		else {
			v = PixelUtils.clamp((int)(f*255));
			int r = v << 16;
			int g = v << 8;
			int b = v;
			v = a|r|g|b;
		}
		if (operation != PixelUtils.REPLACE)
			v = PixelUtils.combinePixels(rgb, v, operation);
		return v;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Texture/Fractal Brownian Motion...";
	}
	
}
