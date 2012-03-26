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

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

/**
 * The Plane class. it is a Wapping image on the screen or inside a Viewport.
 * It can be scalled and moved.
 * @author Ramiro
 * @version 1.0 08/09/2011
 */
public class Plane extends ZObject {

	/** The image. */
	private BufferedImage image;
	
	/** The bitmap. */
	private Bitmap bitmap;
	
	/** The coordinate of the movement */
	public int ox, oy;
	
	/** The zoom y. */
	private float zoomX, zoomY;
	
	/** The opacity. */
	private int opacity;

	/** The filter. */
	private BufferedImageOp	filter;

	/** The org h. */
	private int	orgW, orgH;
	/**
	 * Instantiates a new plane.
	 */
	public Plane()
	{
		super();
		initializeVariables();
	}
	
	/**
	 * Instantiates a new plane.
	 *
	 * @param viewport the viewport of the plane
	 */
	public Plane(Viewport viewport)
	{
		super(viewport);
		initializeVariables();
	}
	
	/**
	 * Initialize variables.
	 */
	private final void initializeVariables()
	{
		zoomX = 1;
		zoomY = 1;		
		opacity = 255;
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#refreshData()
	 */
	protected final void refreshData() 
	{
		if (bitmap != null)
		{
			BufferedImage bmp = bitmap.getImage();
			image = Graphics.createCompatibleImage(Math.round(zoomX * bmp.getWidth()),Math.round(zoomY * bmp.getHeight()));
			Graphics2D g = (Graphics2D)image.getGraphics();
			g.drawImage(bmp,
					0,0,image.getWidth(),image.getHeight(),
					0,0,bmp.getWidth(),bmp.getHeight(), null);
			if (filter != null)
			{
				BufferedImage img = Graphics.createCompatibleImage(image.getWidth(), image.getHeight());
				g = (Graphics2D)img.getGraphics();
				g.drawImage(image, filter, 0, 0);
				image = img;
			}

			int w = this.getDrawingWidth();
			int h = this.getDrawingHeight();
			int imgW = image.getWidth();
			int imgH = image.getHeight();
			int tw = w / imgW + 2;
			int th = h / imgH + 2;
			orgW  = imgW;
			orgH  = imgH;
			BufferedImage img = Graphics.createCompatibleImage(tw * image.getWidth(), th * image.getHeight());
			g = (Graphics2D) img.getGraphics();
			for (int i = 0; i < tw; i++)
			for (int j = 0; j < th; j++)	
			{
				g.drawImage(image, imgW * i, imgH * j, null);
			}			
			image = img;
			
		}
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D)
	 */
	protected final void draw(Graphics2D g)
	{
		ox %= orgW;
		oy %= orgH;
		if (zoomX <= 0 || zoomY <= 0 || bitmap == null || opacity == 0) return;
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity / 255.0f)); 		
		g.drawImage(image, - ox, - oy, null);
	}

	/**
	 * Sets the bitmap.
	 *
	 * @param value the new bitmap
	 */
	public final void setBitmap(Bitmap value)
	{
		bitmap = value;
		refresh();
	}
	
	/**
	 * Gets the bitmap.
	 *
	 * @return the bitmap
	 */
	public final Bitmap getBitmap()
	{
		refresh();
		return bitmap;
	}
	
	/**
	 * Sets the opacity.
	 *
	 * @param value the new opacity
	 */
	public final void setOpacity(int value)
	{
		opacity = value > 255 ? 255 : (value < 0 ? 0 : value);
	}
	
	/**
	 * Gets the opacity.
	 *
	 * @return the opacity
	 */
	public final int getOpacity()
	{
		return opacity;
	}	

	/**
	 * Gets the zoom x.
	 *
	 * @return the zoom x
	 */
	public final float getZoomX()
	{
		return zoomX;
	}
	
	/**
	 * Gets the zoom y.
	 *
	 * @return the zoom y
	 */
	public final float getZoomY()
	{
		return zoomY;
	}	
	
	/**
	 * Sets the zoom x.
	 *
	 * @param value the new zoom x
	 */
	public final void setZoomX(float value)
	{
		zoomX = value;
		refresh();
	}	
	
	/**
	 * Sets the zoom y.
	 *
	 * @param value the new zoom y
	 */
	public final void setZoomY(float value)
	{
		zoomX = value;
		refresh();
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D, rCore.graphics.Viewport)
	 */
	@Override
	protected void draw(Graphics2D g, Viewport viewport)
	{
		int lastOX = ox;
		int lastOY = oy;
		ox += viewport.getOX();
		oy += viewport.getOY();
		draw(g);
		ox = lastOX;
		oy = lastOY;
	}		
	
	/**
	 * Update.
	 */
	public final void update()
	{
		
	}		
	
	/**
	 * Sets the filter.
	 * 
	 * @param filter
	 *            the new filter
	 */
	public final void setFilter(BufferedImageOp filter)
	{
		
		this.filter = filter;
		refresh();
	}	
	
	/**
	 * Gets the filter.
	 * 
	 * @return the filter
	 */
	public final BufferedImageOp getFilter()
	{
		refresh();
		return this.filter;
	}		
	
}
