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
import java.util.*;
import com.jhlabs.math.*;

// TODO: Auto-generated Javadoc
/**
 * The Class SkyFilter.
 */
public class SkyFilter extends PointFilter {

	/** The scale. */
	private float scale = 0.1f;
	
	/** The stretch. */
	private float stretch = 1.0f;
	
	/** The angle. */
	private float angle = 0.0f;
	
	/** The amount. */
	private float amount = 1.0f;
	
	/** The H. */
	private float H = 1.0f;
	
	/** The octaves. */
	private float octaves = 8.0f;
	
	/** The lacunarity. */
	private float lacunarity = 2.0f;
	
	/** The gain. */
	private float gain = 1.0f;
	
	/** The bias. */
	private float bias = 0.6f;
	
	/** The operation. */
	private int operation;
	
	/** The min. */
	private float min;
	
	/** The max. */
	private float max;
	
	/** The ridged. */
	private boolean ridged;
	
	/** The bm. */
	private FBM fBm;
	
	/** The random. */
	protected Random random = new Random();
	
	/** The basis. */
	private Function2D basis;

	/** The cloud cover. */
	private float cloudCover = 0.5f;
	
	/** The cloud sharpness. */
	private float cloudSharpness = 0.5f;
	
	/** The time. */
	private float time = 0.3f;
	
	/** The glow. */
	private float glow = 0.5f;
	
	/** The glow falloff. */
	private float glowFalloff = 0.5f;
	
	/** The haziness. */
	private float haziness = 0.96f;
	
	/** The t. */
	private float t = 0.0f;
	
	/** The sun radius. */
	private float sunRadius = 10f;
	
	/** The sun color. */
	private int sunColor = 0xffffffff;
	
	/** The sun b. */
	private float sunR, sunG, sunB;
	
	/** The sun azimuth. */
	private float sunAzimuth = 0.5f;
	
	/** The sun elevation. */
	private float sunElevation = 0.5f;
	
	/** The wind speed. */
	private float windSpeed = 0.0f;

	/** The camera azimuth. */
	private float cameraAzimuth = 0.0f;
	
	/** The camera elevation. */
	private float cameraElevation = 0.0f;
	
	/** The fov. */
	private float fov = 1.0f;

	/** The exponents. */
	private float[] exponents;
	
	/** The tan. */
	private float[] tan;
	
	/** The sky colors. */
	private BufferedImage skyColors;
	
	/** The sky pixels. */
	private int[] skyPixels;
	
	/** The Constant r255. */
	private final static float r255 = 1.0f/255.0f;

	/** The height. */
	private float width, height;

	/**
	 * Instantiates a new sky filter.
	 */
	public SkyFilter() {
		if ( skyColors == null ) {
			skyColors = ImageUtils.createImage( Toolkit.getDefaultToolkit().getImage( getClass().getResource("SkyColors.png") ).getSource() );
		}
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * Gets the amount.
	 * 
	 * @return the amount
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
	 * Sets the scale.
	 * 
	 * @param scale
	 *            the new scale
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}

	/**
	 * Gets the scale.
	 * 
	 * @return the scale
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Sets the stretch.
	 * 
	 * @param stretch
	 *            the new stretch
	 */
	public void setStretch(float stretch) {
		this.stretch = stretch;
	}

	/**
	 * Gets the stretch.
	 * 
	 * @return the stretch
	 */
	public float getStretch() {
		return stretch;
	}

	/**
	 * Sets the t.
	 * 
	 * @param t
	 *            the new t
	 */
	public void setT(float t) {
		this.t = t;
	}

	/**
	 * Gets the t.
	 * 
	 * @return the t
	 */
	public float getT() {
		return t;
	}

	/**
	 * Sets the fOV.
	 * 
	 * @param fov
	 *            the new fOV
	 */
	public void setFOV(float fov) {
		this.fov = fov;
	}

	/**
	 * Gets the fOV.
	 * 
	 * @return the fOV
	 */
	public float getFOV() {
		return fov;
	}

	/**
	 * Sets the cloud cover.
	 * 
	 * @param cloudCover
	 *            the new cloud cover
	 */
	public void setCloudCover(float cloudCover) {
		this.cloudCover = cloudCover;
	}

	/**
	 * Gets the cloud cover.
	 * 
	 * @return the cloud cover
	 */
	public float getCloudCover() {
		return cloudCover;
	}

	/**
	 * Sets the cloud sharpness.
	 * 
	 * @param cloudSharpness
	 *            the new cloud sharpness
	 */
	public void setCloudSharpness(float cloudSharpness) {
		this.cloudSharpness = cloudSharpness;
	}

	/**
	 * Gets the cloud sharpness.
	 * 
	 * @return the cloud sharpness
	 */
	public float getCloudSharpness() {
		return cloudSharpness;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time
	 *            the new time
	 */
	public void setTime(float time) {
		this.time = time;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public float getTime() {
		return time;
	}

	/**
	 * Sets the glow.
	 * 
	 * @param glow
	 *            the new glow
	 */
	public void setGlow(float glow) {
		this.glow = glow;
	}

	/**
	 * Gets the glow.
	 * 
	 * @return the glow
	 */
	public float getGlow() {
		return glow;
	}

	/**
	 * Sets the glow falloff.
	 * 
	 * @param glowFalloff
	 *            the new glow falloff
	 */
	public void setGlowFalloff(float glowFalloff) {
		this.glowFalloff = glowFalloff;
	}

	/**
	 * Gets the glow falloff.
	 * 
	 * @return the glow falloff
	 */
	public float getGlowFalloff() {
		return glowFalloff;
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
	 * Sets the haziness.
	 * 
	 * @param haziness
	 *            the new haziness
	 */
	public void setHaziness(float haziness) {
		this.haziness = haziness;
	}

	/**
	 * Gets the haziness.
	 * 
	 * @return the haziness
	 */
	public float getHaziness() {
		return haziness;
	}

	/**
	 * Sets the sun elevation.
	 * 
	 * @param sunElevation
	 *            the new sun elevation
	 */
	public void setSunElevation(float sunElevation) {
		this.sunElevation = sunElevation;
	}

	/**
	 * Gets the sun elevation.
	 * 
	 * @return the sun elevation
	 */
	public float getSunElevation() {
		return sunElevation;
	}

	/**
	 * Sets the sun azimuth.
	 * 
	 * @param sunAzimuth
	 *            the new sun azimuth
	 */
	public void setSunAzimuth(float sunAzimuth) {
		this.sunAzimuth = sunAzimuth;
	}

	/**
	 * Gets the sun azimuth.
	 * 
	 * @return the sun azimuth
	 */
	public float getSunAzimuth() {
		return sunAzimuth;
	}

	/**
	 * Sets the sun color.
	 * 
	 * @param sunColor
	 *            the new sun color
	 */
	public void setSunColor(int sunColor) {
		this.sunColor = sunColor;
	}

	/**
	 * Gets the sun color.
	 * 
	 * @return the sun color
	 */
	public int getSunColor() {
		return sunColor;
	}

	/**
	 * Sets the camera elevation.
	 * 
	 * @param cameraElevation
	 *            the new camera elevation
	 */
	public void setCameraElevation(float cameraElevation) {
		this.cameraElevation = cameraElevation;
	}

	/**
	 * Gets the camera elevation.
	 * 
	 * @return the camera elevation
	 */
	public float getCameraElevation() {
		return cameraElevation;
	}

	/**
	 * Sets the camera azimuth.
	 * 
	 * @param cameraAzimuth
	 *            the new camera azimuth
	 */
	public void setCameraAzimuth(float cameraAzimuth) {
		this.cameraAzimuth = cameraAzimuth;
	}

	/**
	 * Gets the camera azimuth.
	 * 
	 * @return the camera azimuth
	 */
	public float getCameraAzimuth() {
		return cameraAzimuth;
	}

	/**
	 * Sets the wind speed.
	 * 
	 * @param windSpeed
	 *            the new wind speed
	 */
	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * Gets the wind speed.
	 * 
	 * @return the wind speed
	 */
	public float getWindSpeed() {
		return windSpeed;
	}

/** The mx. */
float mn, mx;
    
    /* (non-Javadoc)
     * @see com.jhlabs.image.PointFilter#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)
     */
    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
long start = System.currentTimeMillis();
		sunR = (float)((sunColor >> 16) & 0xff) * r255;
		sunG = (float)((sunColor >> 8) & 0xff) * r255;
		sunB = (float)(sunColor & 0xff) * r255;

mn = 10000;
mx = -10000;
		exponents = new float[(int)octaves+1];
		float frequency = 1.0f;
		for (int i = 0; i <= (int)octaves; i++) {
			exponents[i] = (float)Math.pow(2, -i);
			frequency *= lacunarity;
		}

		min = -1;
		max = 1;

//min = -1.2f;
//max = 1.2f;

		width = src.getWidth();
		height = src.getHeight();

		int h = src.getHeight();
		tan = new float[h];
		for (int i = 0; i < h; i++)
			tan[i] = (float)Math.tan( fov * (float)i/h * Math.PI * 0.5 );

		if ( dst == null )
			dst = createCompatibleDestImage( src, null );
		int t = (int)(63*time);
//		skyPixels = getRGB( skyColors, t, 0, 1, 64, skyPixels );
		Graphics2D g = dst.createGraphics();
		g.drawImage( skyColors, 0, 0, dst.getWidth(), dst.getHeight(), t, 0, t+1, 64, null );
		g.dispose();
		BufferedImage clouds = super.filter( dst, dst );
//		g.drawRenderedImage( clouds, null );
//		g.dispose();
long finish = System.currentTimeMillis();
System.out.println(mn+" "+mx+" "+(finish-start)*0.001f);
		exponents = null;
		tan = null;
		return dst;
	}
	
	/**
	 * Evaluate.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the float
	 */
	public float evaluate(float x, float y) {
		float value = 0.0f;
		float remainder;
		int i;
		
		// to prevent "cascading" effects
		x += 371;
		y += 529;
		
		for (i = 0; i < (int)octaves; i++) {
			value += Noise.noise3(x, y, t) * exponents[i];
			x *= lacunarity;
			y *= lacunarity;
		}

		remainder = octaves - (int)octaves;
		if (remainder != 0)
			value += remainder * Noise.noise3(x, y, t) * exponents[i];

		return value;
	}

	/* (non-Javadoc)
	 * @see com.jhlabs.image.PointFilter#filterRGB(int, int, int)
	 */
	public int filterRGB(int x, int y, int rgb) {

// Curvature
float fx = (float)x / width;
//y += 20*Math.sin( fx*Math.PI*0.5 );
		float fy = y / height;
		float haze = (float)Math.pow( haziness, 100*fy*fy );
//		int argb = skyPixels[(int)fy];
		float r = (float)((rgb >> 16) & 0xff) * r255;
		float g = (float)((rgb >> 8) & 0xff) * r255;
		float b = (float)(rgb & 0xff) * r255;

		float cx = width*0.5f;
		float nx = x-cx;
		float ny = y;
// FOV
//ny = (float)Math.tan( fov * fy * Math.PI * 0.5 );
ny = tan[y];
nx = (fx-0.5f) * (1+ny);
ny += t*windSpeed;// Wind towards the camera

//		float xscale = scale/(1+y*bias*0.1f);
		nx /= scale;
		ny /= scale * stretch;
		float f = evaluate(nx, ny);
float fg = f;//FIXME-bump map
		// Normalize to 0..1
//		f = (f-min)/(max-min);

		f = (f+1.23f)/2.46f;

//		f *= amount;
		int a = rgb & 0xff000000;
		int v;

		// Work out cloud cover
		float c = f - cloudCover;
		if (c < 0)
			c = 0;

		float cloudAlpha = 1 - (float)Math.pow(cloudSharpness, c);
//cloudAlpha *= amount;
//if ( cloudAlpha > 1 )
//	cloudAlpha = 1;
mn = Math.min(mn, cloudAlpha);
mx = Math.max(mx, cloudAlpha);

		// Sun glow
		float centreX = width*sunAzimuth;
		float centreY = height*sunElevation;
		float dx = x-centreX;
		float dy = y-centreY;
		float distance2 = dx*dx+dy*dy;
//		float sun = 0;
		//distance2 = (float)Math.sqrt(distance2);
distance2 = (float)Math.pow(distance2, glowFalloff);
		float sun = /*amount**/10*(float)Math.exp(-distance2*glow*0.1f);
//		sun = glow*10*(float)Math.exp(-distance2);

		// Sun glow onto sky
		r += sun * sunR;
		g += sun * sunG;
		b += sun * sunB;


//		float cloudColor = cloudAlpha *sun;
// Bump map
/*
		float nnx = x-cx;
		float nny = y-1;
		nnx /= xscale;
		nny /= xscale * stretch;
		float gf = evaluate(nnx, nny);
		float gradient = fg-gf;
if (y == 100)System.out.println(fg+" "+gf+gradient);
		cloudColor += amount * gradient;
*/
// ...
/*
		r += (cloudColor-r) * cloudAlpha;
		g += (cloudColor-g) * cloudAlpha;
		b += (cloudColor-b) * cloudAlpha;
*/
		// Clouds get darker as they get thicker
		float ca = (1-cloudAlpha*cloudAlpha*cloudAlpha*cloudAlpha) /** (1 + sun)*/ * amount;
		float cloudR = sunR * ca;
		float cloudG = sunG * ca;
		float cloudB = sunB * ca;

		// Apply the haziness as we move further away
		cloudAlpha *= haze;

		// Composite the clouds on the sky
		float iCloudAlpha = (1-cloudAlpha);
		r = iCloudAlpha*r + cloudAlpha*cloudR;
		g = iCloudAlpha*g + cloudAlpha*cloudG;
		b = iCloudAlpha*b + cloudAlpha*cloudB;

		// Exposure
		float exposure = gain;
		r = 1 - (float)Math.exp(-r * exposure);
		g = 1 - (float)Math.exp(-g * exposure);
		b = 1 - (float)Math.exp(-b * exposure);

		int ir = (int)(255*r) << 16;
		int ig = (int)(255*g) << 8;
		int ib = (int)(255*b);
		v = 0xff000000|ir|ig|ib;
		return v;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Texture/Sky...";
	}
	
}
