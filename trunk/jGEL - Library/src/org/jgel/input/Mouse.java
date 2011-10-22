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
package org.jgel.input;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;

import org.jgel.graphics.Graphics;
import org.jgel.graphics.Rect;
import org.jgel.applet.GameApplet;
// TODO: Auto-generated Javadoc
/**
 * The Class Mouse.
 */
public abstract class Mouse
{

	/**
	 * The Enum MouseState.
	 */
	private static enum MouseState
	{
		
		/** The UP. */
		UP, 
 /** The FCLICK. */
 FCLICK, 
 /** The CLICK. */
 CLICK, 
 /** The DOWN. */
 DOWN, 
 /** The RELEASED. */
 RELEASED, 
 /** The FRELEASE. */
 FRELEASE
	}
	
	/** The frame count. */
	private static int x, y, frameCount = 0;
	
	
	
	/** The right state. */
	private static MouseState leftState, rightState = MouseState.UP;
	
	/**
	 * Left press.
	 *
	 * @return true, if successful
	 */
	public static final boolean leftPress()
	{
		return (leftState == MouseState.FCLICK || leftState == MouseState.CLICK || leftState == MouseState.DOWN);
	}
	
	/**
	 * Right press.
	 *
	 * @return true, if successful
	 */
	public static final boolean rightPress()
	{
		return (rightState == MouseState.FCLICK || rightState == MouseState.CLICK || rightState == MouseState.DOWN);
	}	
	
	/**
	 * Left trigger.
	 *
	 * @return true, if successful
	 */
	public static final boolean leftTrigger()
	{
		return (leftState == MouseState.FCLICK || leftState == MouseState.CLICK);
	}
	
	/**
	 * Right trigger.
	 *
	 * @return true, if successful
	 */
	public static final boolean rightTrigger()
	{
		return (rightState == MouseState.FCLICK || rightState == MouseState.CLICK);
	}		
	
	/**
	 * Left repeat.
	 *
	 * @return true, if successful
	 */
	public static final boolean leftRepeat()
	{
		return ((leftState == MouseState.FCLICK || leftState == MouseState.CLICK || leftState == MouseState.DOWN) && frameCount == 0);
	}
	
	/**
	 * Right repeat.
	 *
	 * @return true, if successful
	 */
	public static final boolean rightRepeat()
	{
		return ((rightState == MouseState.FCLICK || rightState == MouseState.CLICK || rightState == MouseState.DOWN) && frameCount == 0);
	}	
	
	/**
	 * Left release.
	 *
	 * @return true, if successful
	 */
	public static final boolean leftRelease()
	{
		return (leftState == MouseState.FRELEASE || leftState == MouseState.RELEASED);
	}
	
	/**
	 * Right release.
	 *
	 * @return true, if successful
	 */
	public static final boolean rightRelease()
	{
		return (rightState == MouseState.FRELEASE || rightState == MouseState.RELEASED);
	}	
	
	/**
	 * Sroll.
	 *
	 * @return the int
	 */
	public static final int sroll()
	{
		return 0;
	}
	
	/**
	 * X.
	 *
	 * @return the int
	 */
	public static final int x()
	{
		return Mouse.x;
	}
	
	/**
	 * Y.
	 *
	 * @return the int
	 */
	public static final int y()
	{
		return Mouse.y;
	}	
	
	/**
	 * Update.
	 */
	public synchronized static final void update()
	{
		frameCount = (frameCount + 1) % 4;
		Point appletPos = GameApplet.getPositionOnScreen();
		Point mousePos  = MouseInfo.getPointerInfo().getLocation();
		Mouse.x = (mousePos.x - appletPos.x);
		Mouse.y = (mousePos.y - appletPos.y);
		switch (leftState)
		{
			case FCLICK:
				leftState = MouseState.CLICK;
				break;
			case CLICK:
				leftState = MouseState.DOWN;
				break;
			case FRELEASE:
				leftState = MouseState.RELEASED;
				break;
			case RELEASED:
				leftState = MouseState.UP;
				break;
		}
		switch (rightState)
		{
			case FCLICK:
				rightState = MouseState.CLICK;
				break;
			case CLICK:
				rightState = MouseState.DOWN;
				break;
			case FRELEASE:
				rightState = MouseState.RELEASED;
				break;
			case RELEASED:
				rightState = MouseState.UP;
				break;
		}		
	}
	
	/**
	 * On screen.
	 * 
	 * @return true, if successful
	 */
	public static final boolean onScreen()
	{
		return ((Mouse.x >= 0) && (Mouse.y >= 0) && (Mouse.x <= Graphics.width()) && (Mouse.y <= Graphics.height()));
	}
	
	/**
	 * Over rect.
	 * 
	 * @param rect
	 *            the rect
	 * @return true, if successful
	 */
	public static final boolean overRect(Rect rect)
	{
		return overRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	/**
	 * Over rect.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return true, if successful
	 */
	public static final boolean overRect(int x, int y, int width, int height)
	{
		return ((Mouse.x >= x) && (Mouse.y >= y) && (Mouse.x <= x + width) && (Mouse.y <= y + height));
	}

	/**
	 * Send pressed button.
	 * 
	 * @param button
	 *            the button
	 */
	public static final void sendPressedButton(int button)
	{
		if (button == MouseEvent.BUTTON1)
		{
			leftState = MouseState.FCLICK;
		}
		else if (button == MouseEvent.BUTTON2)
		{
			rightState = MouseState.FCLICK;
		}
	}	
	
	/**
	 * Send released button.
	 * 
	 * @param button
	 *            the button
	 */
	public static final void sendReleasedButton(int button)
	{
		if (button == MouseEvent.BUTTON1)
		{
			leftState = MouseState.FRELEASE;
		}
		else if (button == MouseEvent.BUTTON2)
		{
			rightState = MouseState.FRELEASE;
		}
	}		
	
	/**
	 * Initialize.
	 */
	public static synchronized final void initialize()
	{
		leftState = MouseState.UP;
		rightState = MouseState.UP;
	}
	
}
