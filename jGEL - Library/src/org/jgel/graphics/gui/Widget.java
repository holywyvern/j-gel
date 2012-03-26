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
package org.jgel.graphics.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.jgel.graphics.Rect;
import org.jgel.graphics.Size;
import org.jgel.input.Mouse;

// TODO: Auto-generated Javadoc
/**
 * The Class Widget.
 */
public abstract class Widget implements ChildContainer, org.jgel.graphics.gui.Drawable
{
	
	/** The y. */
	public int x, y;
	
	/** The oy. */
	public int ox, oy;
	
	/** The height. */
	private int width, height;
	
	/** The parent. */
	private ChildContainer parent;
	
	/** The children. */
	private ArrayList<Widget> children;
	
	/** The children array. */
	private Widget[] childrenArray;
	
	/** The children image. */
	private BufferedImage widgetImage, childrenImage;
	
	/** The mouse over. */
	private boolean mouseOver;
	
	/**
	 * Instantiates a new widget.
	 * 
	 * @param parent
	 *            the parent
	 */
	public Widget(ChildContainer parent)
	{
		this.parent = parent;
		parent.addChild(this);
		children = new ArrayList<Widget>();
		childrenArray = children.toArray(new Widget[children.size()]);
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
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#addChild(rCore.graphics.gui.Widget)
	 */
	public final void addChild(Widget child)
	{
		children.add(child);
		childrenArray = children.toArray(new Widget[children.size()]);
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#removehild(rCore.graphics.gui.Widget)
	 */
	public final void removehild(Widget child)
	{
		children.remove(child);
		childrenArray = children.toArray(new Widget[children.size()]);	
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#updateChilds()
	 */
	public final void updateChilds()
	{
		for (int i = 0; i < childrenArray.length; i++) childrenArray[i].update();
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#refreshChilds()
	 */
	public final void refreshChilds()
	{
		for (int i = 0; i < childrenArray.length; i++) childrenArray[i].refreshChilds();
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#disposeChilds()
	 */
	public final void disposeChilds()
	{
		dispose();
		for (int i = 0; i < childrenArray.length; i++) childrenArray[i].disposeChilds();
	}	
	
	/**
	 * Draw.
	 * 
	 * @param g
	 *            the g
	 */
	public final void draw(Graphics2D g)
	{
		if (widgetImage != null) g.drawImage(widgetImage, x, y, null);
		drawWidgetImages(g);
		if (childrenImage != null && childrenArray.length > 0)
		{
			Graphics2D g2 = childrenImage.createGraphics();
			for (int i = 0; i < childrenArray.length; i++) childrenArray[i].draw(g2);
			Rect rect = getContentRect();
			BufferedImage img = childrenImage.getSubimage(rect.x, rect.y, rect.width, rect.height);
			Size size = getWidgetBorder();
			g.drawImage(img, size.width + x, size.height + y, null);
		}
	}
	
	/**
	 * Update.
	 */
	public final void update()
	{
		mouseOver = (parent != null) ? (parent.isMouseOver() && Mouse.overRect(getX(), getY(), width, height)) : Mouse.overRect(getX(), getY(), width, height);
		updateChilds();
	}
	
	/**
	 * Refresh data.
	 */
	public final void refreshData()
	{
		widgetImage = getWidgetImage();
		childrenImage = getChildrenImage();
		for (int i = 0; i < childrenArray.length; i++) childrenArray[i].refreshData();
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#getX()
	 */
	public final int getX()
	{
		return (parent != null) ? x + parent.getX() + parent.getContentRect().x : x;
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#getY()
	 */
	public final int getY()
	{
		return (parent != null) ? y + parent.getY() + parent.getContentRect().y : y;
	}
	
	/* (non-Javadoc)
	 * @see rCore.graphics.gui.ChildContainer#isMouseOver()
	 */
	public final boolean isMouseOver()
	{
		return mouseOver;
	}
	
	/**
	 * Gets the widget border.
	 * 
	 * @return the widget border
	 */
	public abstract Size getWidgetBorder();
	
	/**
	 * Gets the children image.
	 * 
	 * @return the children image
	 */
	public abstract BufferedImage getChildrenImage();
	
	/**
	 * Gets the widget image.
	 * 
	 * @return the widget image
	 */
	public abstract BufferedImage getWidgetImage();
	
	/**
	 * Gets the children content.
	 * 
	 * @return the children content
	 */
	public abstract Graphics2D getChildrenContent();
	
	/**
	 * Draw widget.
	 * 
	 * @param g
	 *            the g
	 */
	public abstract void drawWidget(Graphics2D g);
	
	/**
	 * Dispose.
	 */
	public abstract void dispose();
	
	public void drawWidgetImages(Graphics2D g) {}
}
