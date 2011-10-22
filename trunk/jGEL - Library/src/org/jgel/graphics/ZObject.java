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

// TODO: Auto-generated Javadoc
/**
 * The Class ZObject.
 */
public abstract class ZObject 
{

	/** The z. */
	private int z = 0;
	
	/** The viewport. */
	private Viewport viewport;
	
	/** The need refresh. */
	private boolean needRefresh;
	
	/** The visible. */
	private boolean visible;
	
	/**
	 * Instantiates a new z object.
	 */
	public ZObject()
	{
		viewport = null;
		visible = true;
		Graphics.addZObject(this);
		refresh();
	}	
	
	/**
	 * Instantiates a new z object.
	 *
	 * @param viewport the viewport
	 */
	public ZObject(Viewport viewport)
	{
		viewport.addZObject(this);
		visible = true;
		this.viewport = viewport;
		refresh();
	}	
	
	/**
	 * Refresh.
	 */
	public final void refresh()
	{
		if (viewport != null) viewport.refresh();
		needRefresh = true;
	}
	
	/**
	 * Dispose.
	 */
	public final void dispose()
	{
		if (this.viewport != null)
			viewport.removeZObject(this);
		else
			Graphics.removeZObject(this);
	}
	
	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public final int getZ()
	{
		return z;
	}
	
	
	/**
	 * Sets the z.
	 *
	 * @param value the new z
	 */
	public final void setZ(int value)
	{
		z = value;
		if (viewport != null) viewport.reorganizeObjects();
		else Graphics.reorganize();
		refresh();
	}

	/**
	 * Gets the depth.
	 *
	 * @return the depth
	 */
	public final int getDepth()
	{
		return getZ();
	}
	
	
	/**
	 * Sets the depth.
	 *
	 * @param value the new depth
	 */
	public final void setDepth(int value)
	{
		setZ(value);
	}	
	
	/**
	 * Draw content.
	 *
	 * @param g the g
	 */
	public final void drawContent(Graphics2D g)
	{
		if (!visible) return;
		if (needRefresh) 
		{
			refreshData();
			needRefresh = false;
		}
		draw(g);
	}

	/**
	 * Gets the drawing width.
	 *
	 * @return the drawing width
	 */
	protected final int getDrawingWidth()
	{
		return (viewport != null) ? viewport.width() : Graphics.width();
	}
	
	/**
	 * Gets the drawing height.
	 *
	 * @return the drawing height
	 */
	protected final int getDrawingHeight()
	{
		return (viewport != null) ? viewport.height() : Graphics.height();
	}	
	
	/**
	 * Refresh data.
	 */
	protected abstract void refreshData();
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 */
	protected abstract void draw(Graphics2D g);

	/**
	 * Draw content.
	 *
	 * @param g the g
	 * @param viewport the viewport
	 */
	public final void drawContent(Graphics2D g, Viewport viewport) 
	{
		if (!visible) return;
		if (needRefresh) 
		{
			refreshData();
			needRefresh = false;
		}
		if (visible) draw(g, viewport);
	}

	/**
	 * Checks if is visible.
	 * 
	 * @return true, if is visible
	 */
	public final boolean isVisible()
	{
		return visible;
	}
	
	/**
	 * Sets the visile.
	 * 
	 * @param value
	 *            the new visile
	 */
	public final void setVisile(boolean value)
	{
		visible = value;
	}	
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param viewport the viewport
	 */
	protected abstract void draw(Graphics2D g, Viewport viewport);

}
