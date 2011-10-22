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
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;

/**
 * The bitmap class, it manipulate an Image resource's data.
 * 
 * @author Ramiro
 * @version 1.0 08/09/2011
 */
public class Bitmap 
{
	
	/** Constant that gives the value of the left aling in the text */
	public static final int TEXT_ALING_LEFT   = 0;
	
	/** Constant that gives the center aling in the text */
	public static final int TEXT_ALING_CENTER = 1;
	
	/** Constant that gives the right aling of the text */
	public static final int TEXT_ALING_RIGHT  = 2;
	
	/** The width of the bitmap. */
	private int width;
	
	/** The height of the bitmap. */
	private int height;
	
	/** The image data stored of the bitmap. */
	private BufferedImage image;
	
	/** The references than use this bitmap. */
	private ArrayList<ZObject> references;
	
	/** The font used with the draw text methods. */
	public Font font; 
	
	/**
	 * Instantiates a new bitmap.
	 *
	 * @param width the width of the new bitmap
	 * @param height the height of the new bitmap
	 */
	public Bitmap(int width, int height)
	{
		
		image = Graphics.createCompatibleImage(width, height);
		this.width = width;
		this.height = height;
		references = new ArrayList<ZObject>();
	
	}
	
	/**
	 * Instantiates a new bitmap.
	 *
	 * @param filename the filename of the bitmap than is going to be loaded, can be an URL.<BR>
	 * 			<u>Example:</u><BR>
	 * 					"myImage.png"<BR>
	 * 					"http://www.mysite.com/images/myImage.png"
	 * 
	 */
	public Bitmap(String filename)
	{
		references = new ArrayList<ZObject>();
		image = Graphics.loadCompatibleImage(filename);
		this.width = image.getWidth();
		this.height = image.getHeight();		
	}		
	
	/**
	 * Instantiates a new bitmap.
	 * 
	 * @param img the buffered image data for the bitmap.
	 */
	public Bitmap(BufferedImage img)
	{
		references = new ArrayList<ZObject>();
		this.image  = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
		this.width  = img.getWidth();
		this.height = img.getHeight();
	}

	/**
	 * Returns the bitmap's width
	 *
	 * @return the width of the bitmap.
	 */
	public final int width()
	{
		return width;
	}
	
	/**
	 * Returns the bitmap's height
	 *
	 * @return the height of the bitmap.
	 */
	public final int height()
	{
		return height;
	}

	/**
	 * Gets the image data of a current rect given.
	 *
	 * @param srcRect the rect of the bitmap to get
	 * @return the image data
	 */
	public final BufferedImage getRectImage(Rect srcRect) {

		if (image != null)
			return image.getSubimage(srcRect.x,srcRect.y,srcRect.width,srcRect.height);
		return null;
	}	
	
	/**
	 * Gets the image data of the bitmap.
	 *
	 * @return the image data
	 */
	public final BufferedImage getImage() 
	{
		return image;
	}		
	
	/**
	 * Adds a reference to the bitmap.
	 *
	 * @param object the new reference.
	 */
	public final void addReference(ZObject object)
	{
		references.add(object);
	}
	
	/**
	 * Removes the reference of the bitmap.
	 *
	 * @param object the referenced object
	 */
	public final void removeReference(ZObject object)
	{
		references.remove(object);
	}	
	
	/**
	 * Refresh the bitmap and all of its components.
	 */
	public final void refresh()
	{
		Iterator<ZObject> iterator = references.iterator();
		while (iterator.hasNext()) iterator.next().refresh();
	}
	
	/**
	 * Clears the bitmap data. 
	 */
	public final void clear()
	{
		image.flush();
		refresh();
	}
	
	/**
	 * Clears an area of the bitmap specified by the rect.
	 *
	 * @param rect the Rect of the area to be deleted.
	 */
	public final void clearRect(Rect rect)
	{
		
		clearRect(rect.x,rect.y,rect.width,rect.height);
	}	

	/**
	 * Clears an area of the bitmap specified by a rect.
	 *
	 * @param x the x of the rect to delete
	 * @param y the y of the rect to delete
	 * @param width the width of the rect to delete
	 * @param height the height of the rect to delete
	 */
	public final void clearRect(int x, int y, int width, int height)
	{
		image.getGraphics().clearRect(x, y, width, height);
		refresh();
	}	
	
	/**
	 * Fills a rect of the bitmap with the specified color
	 *
	 * @param rect the rect to fill
	 * @param color the color to fill the rect
	 */
	public final void fillRect(Rect rect,Color color)
	{
		fillRect(rect.x,rect.y,rect.width,rect.height, color);
	}	

	/**
	 * Fills a rect of the bitmap with the specified color.
	 *
	 * @param x the x of the rect
	 * @param y the y of the rect
	 * @param width the width of the rect
	 * @param height the height of the color
	 * @param color the color to fill the rect
	 */
	public final void fillRect(int x, int y, int width, int height, Color color)
	{
		image.getGraphics().setColor(color);
		image.getGraphics().fillRect(x, y, width, height);
		refresh();
	}		

	/**
	 * Fills a rect with a gradient between two colors
	 *
	 * @param rect the rect to fill
	 * @param c1 the first color
	 * @param c2 the last color
	 */
	public final void gradientFillRect(Rect rect,Color c1, Color c2)
	{
		gradientFillRect(rect.x,rect.y,rect.width,rect.height, c1, c2, 0);
	}	

	/**
	 * Fills a rect with a gradient between two colors.
	 *
	 * @param rect the rect to fill
	 * @param c1 the first color of the gradient
	 * @param c2 the last color of the gradient
	 * @param angle the angle of drawing (counter clockwise, in hex)
	 */
	public final void gradientFillRect(Rect rect,Color c1, Color c2, float angle)
	{
		gradientFillRect(rect.x,rect.y,rect.width,rect.height, c1, c2, angle);
	}		
	
	/**
	 * Fills a rect with a gradient between two colors.
	 *
	 * @param x the x of the rect
	 * @param y the y of the rect
	 * @param width the width of the rect
	 * @param height the height of the rect
	 * @param c1 the first color
	 * @param c2 the last color
	 */
	public final void gradientFillRect(int x, int y, int width, int height, Color c1, Color c2)
	{
		gradientFillRect(x, y, width, height, c1, c2, 0);
	}	
	
	/**
	 * Fills a gradient rect between two colors.
	 *
	 * @param x the x of the rect
	 * @param y the y of the rect
	 * @param width the width of the rect
	 * @param height the height of the rect
	 * @param c1 the first color
	 * @param c2 the last color
	 * @param angle the angle of the gradient (counter clockwise, in hex)
	 */
	public final void gradientFillRect(int x, int y, int width, int height, Color c1, Color c2, float angle)
	{
		int rx = width / 2;
		int ry = height / 2;
		GradientPaint gradient = getGradientPaint(rx,ry,angle,c1,c2);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setPaint(gradient);
		g.fillRect(x, y, width, height);
		refresh();
	}		
	
	/**
	 * Gets the gradient paint.
	 *
	 * @param rx the rx
	 * @param ry the ry
	 * @param angle the angle
	 * @param c1 the c1
	 * @param c2 the c2
	 * @return the gradient paint
	 */
	private final GradientPaint getGradientPaint(int rx,int ry,float angle, Color c1, Color c2)
	{
		float rad = (float)(angle * Math.PI / 180.0f);
		long sx = Math.round(rx - rx * Math.cos(rad));
		long ex = Math.round(rx + rx * Math.cos(rad));
		long sy = Math.round(ry + ry * Math.sin(rad));
		long ey = Math.round(ry - ry * Math.sin(rad));
		return new GradientPaint(sx,sy,c1,ex,ey,c2);
	}
	
	/**
	 * Draws the text inside a determined rect. it will try to resize if the text can't fit inside the rect.
	 *
	 * @param rect the rect of the text to draw, if the text cant enter, it will resize to that rect.
	 * @param text the text to draw
	 */
	public final void drawText(Rect rect, String text)
	{
		drawText(rect.x,rect.y,rect.width,rect.height,text, Bitmap.TEXT_ALING_LEFT);
	}

	/**
	 * Draws the text inside a determined rect. it will try to resize if the text can't fit inside the rect.
	 *
	 * @param rect the rect of the text to draw, if the text cant enter, it will resize to that rect.
	 * @param text the text to draw
	 * @param aling the aling of the text
	 */
	public final void drawText(Rect rect, String text, int aling)
	{
		drawText(rect.x,rect.y,rect.width,rect.height,text, aling);
	}	
	
	/**
	 * Draws the text inside a determined rect. it will try to resize if the text can't fit inside the rect.
	 *
	 * @param x the x of the rect
	 * @param y the y of the rect
	 * @param width the width of the rect
	 * @param height the height of the rect
	 * @param text the text to draw
	 */
	public final void drawText(int x, int y, int width, int height, String text)
	{
		drawText(x,y,width,height,text, Bitmap.TEXT_ALING_LEFT);
	}	
	
	/**
	 * Draws the text inside a determined rect. it will try to resize if the text can't fit inside the rect.
	 *
	 * @param x the x of the rect
	 * @param y the y of the rect
	 * @param width the width of the rect
	 * @param height the height of the rect
	 * @param text the text to draw
	 * @param aling the aling of the text
	 */
	public final void drawText(int x, int y, int width, int height, String text, int aling)
	{
		BufferedImage txtImg = getTextBitmap(text);
		
		Graphics2D g = (Graphics2D)image.getGraphics();
		int minW = Math.min(width, txtImg.getWidth()); 
		int minH = Math.min(height, txtImg.getHeight()); 
		
		int px, py;
		
		switch (aling)
		{
		case (Bitmap.TEXT_ALING_CENTER):
			px = (width - minW) / 2 + x;
			break;
		case (Bitmap.TEXT_ALING_LEFT):
			px = x;
			break;
		case (Bitmap.TEXT_ALING_RIGHT):
			px = width - minW + x;
			break;
		default:
			px = x;
		}
		py = (height - minH) / 2 + y;
		g.drawImage(txtImg, px, py, px+minW, py+minH, 0, 0, txtImg.getWidth(), txtImg.getHeight(), null);
		refresh();
	}		
	
	/**
	 * Gets the size of the text based on the current font.
	 *
	 * @param text the text
	 * @return the rect with width and height determining the size of the text.
	 */
	public final Rect textSize(String text)
	{
		if (font == null) font = new Font();
		java.awt.Font f = new java.awt.Font(font.name, font.size, font.size);
		java.awt.Graphics g = image.getGraphics();
		g.setFont(f);
		FontMetrics metrics = g.getFontMetrics(f);
		return new Rect(0,0,metrics.stringWidth(text),font.size);
	}
	
	/**
	 * Gets the text bitmap.
	 *
	 * @param text the text
	 * @return the text bitmap
	 */
	private final BufferedImage getTextBitmap(String text)
	{
		if (font == null) font = new Font();
		int styles = java.awt.Font.PLAIN;
		if (font.bold)   styles = styles & java.awt.Font.BOLD;
		if (font.italic) styles = styles & java.awt.Font.ITALIC;
		java.awt.Font f = new java.awt.Font(font.name, styles, font.size);
		Rect size = textSize(text);
		BufferedImage textImage = Graphics.createCompatibleImage(size.width + 4, size.height + 4);
		Graphics2D g = (Graphics2D)textImage.getGraphics();
		g.setFont(f);
		int halfSize = size.height * 3 / 4;
		if (font.shadow)
		{
			g.setColor(font.shadowColor);
			if (font.underline)
			{
				g.fillRect(2,textImage.getHeight() - 2, textImage.getWidth(), 1);
			}
			
			if (font.strike)
			{
				g.fillRect(2, size.height / 2 + 1, textImage.getWidth() - 4, 1);
			}
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawString(text, 3, 3 + halfSize);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}		
		if (font.border)
		{
			g.setColor(font.borderColor);
			g.drawString(text, 0, 1 + halfSize);
			g.drawString(text, 1, halfSize);
			g.drawString(text, 0, halfSize);
			g.drawString(text, 2, halfSize);
			g.drawString(text, 0, 2 + halfSize);
			g.drawString(text, 2, 2 + halfSize);
			g.drawString(text, 2, 1 + halfSize);
			g.drawString(text, 1, 2 + halfSize);			
		}
		
		g.setColor(font.color);			
		g.drawString(text, 1, 1 + halfSize);
		
		if (font.underline)
		{
			g.setColor(font.underlineColor);
			g.fillRect(1,textImage.getHeight() - 3, textImage.getWidth(), 1);
		}
		
		if (font.strike)
		{
			g.setColor(font.strikeColor);
			g.fillRect(1, size.height / 2, textImage.getWidth() - 4, 1);
		}		
		
		return textImage;
	}
	
	/**
	 * Performs a block transfer from the src_bitmap box src_rect (Rect) to the specified bitmap coordinates (x, y).
	 *
	 * @param x the x position of the area
	 * @param y the y position of the area
	 * @param bitmap the source bitmap to blit
	 * @param rect the rect of the source bitmap to blit
	 */
	public void blt(int x, int y,Bitmap bitmap, Rect rect)
	{
		blt(x, y, bitmap, rect, 255);
	}
	
	/**
	 * Performs a block transfer from the src_bitmap box src_rect (Rect) to the specified bitmap coordinates (x, y).
	 * opacity can be set from 0 to 255.
	 *
	 * @param x the x position of the area
	 * @param y the y position of the area
	 * @param bitmap the source bitmap to blit
	 * @param rect the rect of the source bitmap to blit
	 * @param opacity the opacity of the source bitmap
	 */
	public void blt(int x, int y,Bitmap bitmap, Rect rect,int opacity)
	{
		opacity = (opacity > 255) ? 255 : ((opacity < 0) ? 0 : opacity);
		Graphics2D g = (Graphics2D)image.getGraphics();
		BufferedImage img = bitmap.getRectImage(rect);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity / 255.0f)); 
		g.drawImage(img, x, y, x+rect.width, y+rect.height, 0, 0, rect.width, rect.height, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));  
		refresh();
	}	
	
	/**
	 * Performs a block transfer from the  bitmap box srcRect to the specified bitmap box destRect.
	 *
	 * @param dstRect the destination rect
	 * @param bitmap the bitmap
	 * @param srcRect the source rect
	 */
	public void stretchBlt(Rect dstRect,Bitmap bitmap, Rect srcRect)
	{
		stretchBlt(dstRect, bitmap, srcRect, 255);
	}
	
	/**
	 * Performs a block transfer from the  bitmap box srcRect to the specified bitmap box destRect.
	 * opacity can be set from 0 to 255.
	 *
	 * @param dstRect the destination rect
	 * @param bitmap the bitmap
	 * @param srcRect the source rect
	 * @param opacity the opacity of the source bitmap
	 */
	public void stretchBlt(Rect dstRect,Bitmap bitmap, Rect srcRect,int opacity)
	{
		opacity = (opacity > 255) ? 255 : ((opacity < 0) ? 0 : opacity);
		Graphics2D g = (Graphics2D)image.getGraphics();
		BufferedImage img = bitmap.getImage();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity / 255.0f)); 
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(img, dstRect.x, dstRect.y, dstRect.x+dstRect.width, dstRect.y+dstRect.height,
				srcRect.x, srcRect.y, srcRect.x+srcRect.width, srcRect.y+srcRect.height, null);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		refresh();
	}		
	
	/**
	 * Sets the pixel at the coordinates (x, y).
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param color the color of the new pixel
	 */
	public final void setPixel(int x, int y, Color color)
	{
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(color);
		g.fillRect(x, y, 1, 1);
		refresh();
	}
	
	/**
	 * Gets the pixel at an specified coordinate (x, y).
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the Color of the pixel or Black if the pixel is outside the image.
	 */
	public final Color getPixel(int x, int y)
	{
		return new Color(image.getRGB(x, y),true);
	}
	
	/**
	 * Aplies a filter to the image data permanently.
	 * 
	 * @param filter the filter to apply.
	 */
	public final void applyFilter(BufferedImageOp filter)
	{
		
		BufferedImage img = Graphics.createCompatibleImage(width,height);
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.drawImage(image, filter, 0, 0);
		image = img;
	}
	
	/**
	 * Saves the image into the speified PNG file.
	 * 
	 * @param filename the name of the png file without extension.
	 */
	public final void toPNG(String filename)
	{
		File file = new File(filename + ".png");
		try
		{
			ImageIO.write(image, "png", file);
		} catch (IOException e)
		{
		}
	}
	
}
