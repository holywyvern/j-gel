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

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * The Class TextBitmap.
 */
public class TextBitmap extends ZObject
{

	/** The text. */
	public String text;
	
	/** The y. */
	public int x, y;
	
	/** The font. */
	public Font font;
	
	/**
	 * Instantiates a new text bitmap.
	 */
	public TextBitmap()
	{
		super();
		font = new Font(); 
		text = "";
	}

	/**
	 * Instantiates a new text bitmap.
	 * 
	 * @param viewport
	 *            the viewport
	 */
	public TextBitmap(Viewport viewport)
	{
		super(viewport);
		font = new Font(); 
		text = "";		
	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#refreshData()
	 */
	@Override
	protected void refreshData()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see rCore.graphics.ZObject#draw(java.awt.Graphics2D)
	 */
	@Override
	protected void draw(Graphics2D g)
	{
		if (font == null) font = new Font(); 
		if (text != null)
		{
			Rect size = textSize(text);
			int styles = java.awt.Font.PLAIN;
			if (font.bold)   styles = styles & java.awt.Font.BOLD;
			if (font.italic) styles = styles & java.awt.Font.ITALIC;
			java.awt.Font f = new java.awt.Font(font.name, styles, font.size);
			g.setFont(f);
			int halfSize = size.height * 3 / 4;
			if (font.shadow)
			{
				g.setColor(font.shadowColor);
				if (font.underline)
				{
					g.fillRect(x+2,y+size.height - 2, size.width, 1);
				}
				
				if (font.strike)
				{
					g.fillRect(x+2, y+size.height / 2 + 1, size.width - 4, 1);
				}
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawString(x+text, y+3, 3 + halfSize);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			}		
			if (font.border)
			{
				g.setColor(font.borderColor);
				g.drawString(text, x, y+1 + halfSize);
				g.drawString(text, x+1, y+halfSize);
				g.drawString(text, x, y+halfSize);
				g.drawString(text, x+2, y+halfSize);
				g.drawString(text, x, 2 + y+halfSize);
				g.drawString(text, x+2, 2 + y+halfSize);
				g.drawString(text, x+2, 1 + y+halfSize);
				g.drawString(text, x+1, 2 + y+halfSize);			
			}
			
			g.setColor(font.color);			
			g.drawString(text, x+1, y+1 + halfSize);
			
			if (font.underline)
			{
				g.setColor(font.underlineColor);
				g.fillRect(x+1,y+size.height - 3, size.width, 1);
			}
			
			if (font.strike)
			{
				g.setColor(font.strikeColor);
				g.fillRect(x+1, y+size.height / 2, size.width - 4, 1);
			}	
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
	 * Text size.
	 * 
	 * @param text
	 *            the text
	 * @return the rect
	 */
	public final Rect textSize(String text)
	{
		if (font == null) font = new Font();
		BufferedImage image = Graphics.createCompatibleImage(Graphics.width(),Graphics.height());
		java.awt.Font f = new java.awt.Font(font.name, font.size, font.size);
		java.awt.Graphics g = image.getGraphics();
		g.setFont(f);
		FontMetrics metrics = g.getFontMetrics(f);
		return new Rect(0,0,metrics.stringWidth(text),font.size);
	}	
	
}
