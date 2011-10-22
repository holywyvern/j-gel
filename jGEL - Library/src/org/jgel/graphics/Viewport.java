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
package org.jgel.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.PriorityQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Viewport.
 */
public final class Viewport extends ZObject 
{

	/** The height. */
	private int x, y, ox, oy, width, height;
	
	/** The objects. */
	private PriorityQueue<ZObject> objects;
	
	/** The data. */
	private BufferedImage data;
	
	/** The object array. */
	private ZObject[] objectArray;
	
	/** The g2d. */
	private Graphics2D g2d;
	
	/**
	 * Instantiates a new viewport.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public Viewport(int x, int y, int width, int height)
	{
		super();
		this.x = x;
		this.y = y;
		this.ox = 0;
		this.oy = 0;
		this.width = width;
		this.height = height;
		objects = new PriorityQueue<ZObject>(20, new ZObjectComparator());
		data = Graphics.createCompatibleImage(width, height);
		refresh();
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);	
		g2d = data.createGraphics();
	}
	
	/**
	 * Removes the z object.
	 *
	 * @param object the object
	 */
	public final void removeZObject(ZObject object)
	{
		objects.remove(object);
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);		
		refresh();
	}
	
	/**
	 * Adds the z object.
	 *
	 * @param object the object
	 */
	public final void addZObject(ZObject object)
	{
		objects.add(object);
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);		
		refresh();
	}	
	
	
	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#refreshData()
	 */
	protected final void refreshData() 
	{
		
		g2d.clearRect(0, 0, width, height);
		for (int i = 0; i < objectArray.length; i++) objectArray[i].drawContent(g2d);
	}
	
	/**
	 * Sets the oX.
	 *
	 * @param value the new oX
	 */
	public final void setOX(int value)
	{
		this.ox = value;
		refresh();
	}
	
	/**
	 * Sets the oY.
	 *
	 * @param value the new oY
	 */
	public final void setOY(int value)
	{
		this.oy = value;
		refresh();
	}	
	
	/**
	 * Gets the oX.
	 *
	 * @return the oX
	 */
	public final int getOX()
	{
		return this.ox;
	}
	
	/**
	 * Gets the oY.
	 *
	 * @return the oY
	 */
	public final int getOY()
	{
		return this.oy;
	}	

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D)
	 */
	protected final void draw(Graphics2D graphics)
	{
		graphics.drawImage(data, x, y, null);
	}

	/**
	 * Reorganize objects.
	 */
	public final void reorganizeObjects() 
	{
		objects = new PriorityQueue<ZObject>(objects);
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);		
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

	/**
	 * Update.
	 */
	public void update() 
	{
		refresh();
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D, rCore.graphics.Viewport)
	 */
	@Override
	protected void draw(Graphics2D g, Viewport viewport) 
	{
		int lastX = x;
		int lastY = y;
		x += viewport.getOX();
		y += viewport.getOY();
		draw(g);
		x = lastX;
		y = lastY;
	}
	
}
