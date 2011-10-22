/*=============================================================================
 * ** RCore
-------------------------------------------------------------------------------
 * An easy to use java Game library
-------------------------------------------------------------------------------
 Copyright (c) @year, @author
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
=============================================================================*/
package org.jgel.graphics.particle;

import java.awt.image.BufferedImage;

import org.jgel.graphics.Bitmap;
import org.jgel.math.RNG;

// TODO: Auto-generated Javadoc
/**
 * The Class Particle.
 */
public abstract class Particle 
{
	
	/** The opacity. */
	public int[] xMov, yMov, opacity;
	
	/** The bitmap. */
	public BufferedImage bitmap;
	
	/**
	 * Instantiates a new particle.
	 * 
	 * @param minLife
	 *            the min life
	 * @param maxLife
	 *            the max life
	 * @param bitmap
	 *            the bitmap
	 */
	public Particle(int minLife, int maxLife, Bitmap bitmap)
	{
		int life = RNG.random(minLife, maxLife);
		xMov = new int[life];
		yMov = new int[life];
		opacity = new int[life];
		this.bitmap = bitmap.getImage();
		for (int i = 0; i < life; i++)
		{
			opacity[i] = 255 - i * 255 / life;
		}
		setupMovement();
	}
	
	/**
	 * Setup movement.
	 */
	protected abstract void setupMovement();
	
	
}
