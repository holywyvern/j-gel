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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

// TODO: Auto-generated Javadoc
/**
 * The Class Sprite.
 */
public class Sprite extends ZObject {

	/** The src rect. */
	private Rect srcRect = new Rect(0,0,0,0);
	
	/** The angle. */
	private float zoomX, zoomY, angle;
	
	/** The bitmap. */
	private Bitmap bitmap;
	
	/** The bitmap image. */
	private BufferedImage bitmapImage;
	
	/** The oy. */
	public int ox, oy;
	
	/** The y. */
	public int x, y;
	
	/** The mirror y. */
	private boolean mirrorX, mirrorY;
	
	/** The opacity. */
	private int opacity;
	
	/** The mov time. */
	private int movX, movY, movTime;

	/** The zoom view y. */
	public float zoomViewX, zoomViewY;
	
	/** The filter. */
	private BufferedImageOp	filter;
	
	/**
	 * Instantiates a new sprite.
	 */
	public Sprite()
	{
		super();
		initializeVariables();
	}
	
	/**
	 * Instantiates a new sprite.
	 *
	 * @param viewport the viewport
	 */
	public Sprite(Viewport viewport)
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
		angle = 0;
		x = 0;
		y = 0;	
		ox = 0;
		oy = 0;
		mirrorX = false;
		mirrorY = false;
		opacity = 255;
		movX = 0;
		movY = 0;
		movTime = 0;
		zoomViewX = zoomViewY = 1;
	}
	
	/**
	 * Width.
	 *
	 * @return the int
	 */
	public final int width()
	{
		return srcRect.width;
	}
	
	/**
	 * Height.
	 *
	 * @return the int
	 */
	public final int height()
	{
		return srcRect.height;
	}	
	
	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#refreshData()
	 */
	protected final void refreshData() 
	{
		if (zoomX <= 0 || zoomY <= 0 || bitmap == null || opacity == 0) return;
		int zoomW = Math.round(width() * zoomX);
		int zoomH = Math.round(height() * zoomY);
		bitmapImage = Graphics.createCompatibleImage(zoomW,zoomH);
		java.awt.Graphics2D g = (Graphics2D)bitmapImage.getGraphics();
		g.drawImage(bitmap.getImage(),
					mirrorX ? zoomW : 0,
					mirrorY ? zoomH : 0,
					mirrorX ? 0 : zoomW,
					mirrorY ? 0 : zoomH,
					srcRect.x, srcRect.y, srcRect.x+srcRect.width, srcRect.y+srcRect.height,
					null);	
		if (filter != null)
		{
			BufferedImage img =Graphics.createCompatibleImage(bitmapImage.getWidth(), bitmapImage.getHeight());
			g = (Graphics2D)img.getGraphics();
			g.drawImage(bitmapImage, filter, 0, 0);
			bitmapImage = img;
		}		
		
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D)
	 */
	protected final void draw(Graphics2D g) 
	{
		if (zoomX <= 0 || zoomY <= 0 || bitmap == null || opacity == 0) return;
		AffineTransform at = new AffineTransform();
		at.scale(zoomViewX, zoomViewY);
		at.rotate(angle, ox * zoomX, oy * zoomY);
		at.translate(x * Math.cos(angle) + y * Math.sin(angle) ,y * Math.cos(angle) - x * Math.sin(angle));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity / 255.0f));  
		g.drawImage(bitmapImage, at, null);
	}

	/**
	 * Src rect.
	 *
	 * @return the rect
	 */
	public final Rect srcRect()
	{
		refresh();
		return srcRect;
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
		zoomY = value;
		refresh();
	}		
	
	/**
	 * Sets the bitmap.
	 *
	 * @param value the new bitmap
	 */
	public final void setBitmap(Bitmap value)
	{
		if (this.bitmap != null) this.bitmap.removeReference(this);
		this.bitmap = value;
		this.bitmap.addReference(this);
		this.srcRect.set(0, 0, this.bitmap.width(), this.bitmap.height());
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
	 * Gets the angle.
	 *
	 * @return the angle
	 */
	public final float getAngle()
	{
		return (float)(angle * 180.0f / Math.PI);
	}
	
	/**
	 * Sets the angle.
	 *
	 * @param value the new angle
	 */
	public final void setAngle(float value)
	{
		angle = (float)(value * Math.PI / 180.0f);
	}	
	
	/**
	 * Gets the mirror x.
	 *
	 * @return the mirror x
	 */
	public final boolean getMirrorX()
	{
		return mirrorX;
	}
	
	/**
	 * Gets the mirror y.
	 *
	 * @return the mirror y
	 */
	public final boolean getMirrorY()
	{
		return mirrorY;
	}	
	
	/**
	 * Sets the mirror x.
	 *
	 * @param value the new mirror x
	 */
	public final void setMirrorX(boolean value)
	{
		if (mirrorX != value)
		{
			mirrorX = value;
			refresh();
		}
	}
	
	/**
	 * Sets the mirror y.
	 *
	 * @param value the new mirror y
	 */
	public final void setMirrorY(boolean value)
	{
		if (mirrorY != value)
		{
			mirrorY = value;
			refresh();
		}
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
	 * Update.
	 */
	public final void update()
	{
		updateMovement();
	}
	
	/**
	 * Update movement.
	 */
	private final void updateMovement()
	{
		if (movTime > 0)
		{
			x = (x * (movTime - 1) + movX) / movTime; 
			y = (y * (movTime - 1) + movY) / movTime; 
			movTime--;
		}
	}
	
	/**
	 * Sets the movement.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param time
	 *            the time
	 */
	public final void setMovement(int x, int y, int time)
	{
		movX = x;
		movY = y;
		movTime = time;
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
