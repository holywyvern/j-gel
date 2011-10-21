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
package r.core.graphics.particle;

import java.awt.Graphics2D;

import r.core.graphics.Viewport;
import r.core.graphics.ZObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ParticleAnimation.
 */
public abstract class ParticleAnimation extends ZObject
{

	/** The height. */
	private int width, height;
	
	/** The y. */
	public int x, y;
	
	/**
	 * Instantiates a new particle animation.
	 * 
	 * @param particles
	 *            the particles
	 * @param frameCicle
	 *            the frame cicle
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public ParticleAnimation(Particle[] particles, int frameCicle,int width, int height)
	{
		renderParticles(particles, frameCicle, width, height);
		this.width = width;
		this.height = height;
		this.x = this.y = 0;
	}

	/**
	 * Instantiates a new particle animation.
	 * 
	 * @param viewport
	 *            the viewport
	 * @param particles
	 *            the particles
	 * @param frameCicle
	 *            the frame cicle
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public ParticleAnimation(Viewport viewport,Particle[] particles, int frameCicle,int width, int height)
	{
		super(viewport);
		renderParticles(particles, frameCicle, width, height);
		this.width = width;
		this.height = height;	
		this.x = this.y = 0;
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#refreshData()
	 */
	@Override
	protected final void refreshData()
	{

	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D)
	 */
	@Override
	protected void draw(Graphics2D g)
	{
	
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D, rCore.graphics.Viewport)
	 */
	@Override
	protected void draw(Graphics2D g, Viewport viewport)
	{
		int lastX = x;
		int lastY = y;
		x -= viewport.getOX();
		y -= viewport.getOY();
		draw(g);
		x = lastX;
		y = lastY;
	}

	/**
	 * Render particles.
	 * 
	 * @param particles
	 *            the particles
	 * @param frameCicle
	 *            the frame cicle
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	private final void renderParticles(Particle[] particles,int frameCicle,int width, int height)
	{
		
	}
	
	/**
	 * Width.
	 * 
	 * @return the int
	 */
	public final int width()
	{
		return width;
	}
	
	/**
	 * Height.
	 * 
	 * @return the int
	 */
	public final int height()
	{
		return height;
	}
	
}
