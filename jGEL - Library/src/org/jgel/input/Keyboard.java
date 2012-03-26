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

// TODO: Auto-generated Javadoc
/**
 * The Class Keyboard.
 */
public abstract class Keyboard 
{

	/* Frame count for reppeat */ 
	/** The frame count. */
	private static int frameCount = 0;
	
	/* Letter keys */
	/** The Constant A. */
	public static final int A = 65;
	
	/** The Constant B. */
	public static final int B = 66;
	
	/** The Constant C. */
	public static final int C = 67;
	
	/** The Constant D. */
	public static final int D = 68;
	
	/** The Constant E. */
	public static final int E = 69;
	
	/** The Constant F. */
	public static final int F = 70;
	
	/** The Constant G. */
	public static final int G = 71;
	
	/** The Constant H. */
	public static final int H = 72;
	
	/** The Constant I. */
	public static final int I = 73;
	
	/** The Constant J. */
	public static final int J = 74;
	
	/** The Constant K. */
	public static final int K = 75;
	
	/** The Constant L. */
	public static final int L = 76;
	
	/** The Constant M. */
	public static final int M = 77;
	
	/** The Constant N. */
	public static final int N = 78;
	
	/** The Constant O. */
	public static final int O = 79;
	
	/** The Constant P. */
	public static final int P = 80;
	
	/** The Constant Q. */
	public static final int Q = 81;
	
	/** The Constant R. */
	public static final int R = 82;
	
	/** The Constant S. */
	public static final int S = 83;
	
	/** The Constant T. */
	public static final int T = 84;
	
	/** The Constant U. */
	public static final int U = 85;
	
	/** The Constant V. */
	public static final int V = 86;
	
	/** The Constant W. */
	public static final int W = 87;
	
	/** The Constant X. */
	public static final int X = 88;
	
	/** The Constant Y. */
	public static final int Y = 89;
	
	/** The Constant Z. */
	public static final int Z = 90;
	
	/* Arrow keys */
	/** The Constant UP. */
	public static final int UP    = 38;
	
	/** The Constant DOWN. */
	public static final int DOWN  = 40;
	
	/** The Constant LEFT. */
	public static final int LEFT  = 37;
	
	/** The Constant RIGHT. */
	public static final int RIGHT = 39;
	
	/* Number keys */
	/** The Constant NUM0. */
	public static final int NUM0 = 48;
	
	/** The Constant NUM1. */
	public static final int NUM1 = 49;
	
	/** The Constant NUM2. */
	public static final int NUM2 = 50;
	
	/** The Constant NUM3. */
	public static final int NUM3 = 51;
	
	/** The Constant NUM4. */
	public static final int NUM4 = 52;
	
	/** The Constant NUM5. */
	public static final int NUM5 = 53;
	
	/** The Constant NUM6. */
	public static final int NUM6 = 54;
	
	/** The Constant NUM7. */
	public static final int NUM7 = 55;
	
	/** The Constant NUM8. */
	public static final int NUM8 = 56;
	
	/** The Constant NUM9. */
	public static final int NUM9 = 57;
	
	/* NumberPad keys */
	/** The Constant NUMPAD0. */
	public static final int NUMPAD0 = 45;
	
	/** The Constant NUMPAD1. */
	public static final int NUMPAD1 = 35;
	
	/** The Constant NUMPAD2. */
	public static final int NUMPAD2 = 40;
	
	/** The Constant NUMPAD3. */
	public static final int NUMPAD3 = 34;
	
	/** The Constant NUMPAD4. */
	public static final int NUMPAD4 = 37;
	
	/** The Constant NUMPAD5. */
	public static final int NUMPAD5 = 12;
	
	/** The Constant NUMPAD6. */
	public static final int NUMPAD6 = 39;
	
	/** The Constant NUMPAD7. */
	public static final int NUMPAD7 = 36;
	
	/** The Constant NUMPAD8. */
	public static final int NUMPAD8 = 38;
	
	/** The Constant NUMPAD9. */
	public static final int NUMPAD9 = 33;
	
	/* Function keys */
	/** The Constant F1. */
	public static final int F1  = 112;
	
	/** The Constant F2. */
	public static final int F2  = 113;
	
	/** The Constant F3. */
	public static final int F3  = 114;
	
	/** The Constant F4. */
	public static final int F4  = 115;
	
	/** The Constant F5. */
	public static final int F5  = 116;
	
	/** The Constant F6. */
	public static final int F6  = 117;
	
	/** The Constant F7. */
	public static final int F7  = 118;
	
	/** The Constant F8. */
	public static final int F8  = 119;
	
	/** The Constant F9. */
	public static final int F9  = 120;
	
	/** The Constant F10. */
	public static final int F10 = 121;
	
	/** The Constant F11. */
	public static final int F11 = 122;
	
	/** The Constant F12. */
	public static final int F12 = 123;
	
	/* Misc keys */
	/** The Constant BACKSPACE. */
	public static final int BACKSPACE   = 8;
	
	/** The Constant TAB. */
	public static final int TAB         = 9;
	
	/** The Constant ENTER. */
	public static final int ENTER       = 10;
	
	/** The Constant SHIFT. */
	public static final int SHIFT       = 16;
	
	/** The Constant ALT. */
	public static final int ALT         = 18;
	
	/** The Constant CTRL. */
	public static final int CTRL        = 17;
	
	/** The Constant LEFT_SHIFT. */
	public static final int LEFT_SHIFT  = 160;
	
	/** The Constant RIGHT_SHIFT. */
	public static final int RIGHT_SHIFT = 161;
	
	/** The Constant LEFT_CNTRL. */
	public static final int LEFT_CNTRL  = 162;
	
	/** The Constant RIGHT_CNTRL. */
	public static final int RIGHT_CNTRL = 163;
	
	/** The Constant LEFT_ALT. */
	public static final int LEFT_ALT    = 164;
	
	/** The Constant RIGHT_ALT. */
	public static final int RIGHT_ALT   = 165;
	
	/** The Constant ESC. */
	public static final int ESC         = 27;
	
	/** The Constant SPACE. */
	public static final int SPACE       = 32;
		
	/** The Constant PAGE_UP. */
	public static final int PAGE_UP   = 33;
	
	/** The Constant PAGE_DOWN. */
	public static final int PAGE_DOWN = 34;
	
	/** The Constant END. */
	public static final int END       = 35;
	
	/** The Constant HOME. */
	public static final int HOME      = 36;
	
	/** The Constant INSERT. */
	public static final int INSERT    = 45;
	
	/** The Constant DELETE. */
	public static final int DELETE    = 46;
	
	/**
	 * The Enum KeyState.
	 */
	private static enum KeyState
	{
		
		/** The PRESSED. */
		PRESSED, 
 /** The FRELEASED. */
 FRELEASED, 
 /** The RELEASED. */
 RELEASED,
/** The FTRIGGER. */
FTRIGGER, 
 /** The TRIGGER. */
 TRIGGER, 
 /** The UP. */
 UP
	}
	
	/** The keys. */
	private static KeyState[] keys = new KeyState[256];
	
	
	/**
	 * Trigger.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public static final boolean isTrigger(int key)
	{
		if (key < 0 || key > keys.length) return false;
		return (keys[key] == KeyState.FTRIGGER || keys[key] == KeyState.TRIGGER);
	}
	
	/**
	 * Repeat.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public static final boolean isRepeat(int key)
	{
		if (key < 0 || key >= keys.length) return false;
		return ((keys[key] == KeyState.FTRIGGER || keys[key] == KeyState.TRIGGER || keys[key] == KeyState.PRESSED) && (frameCount == 0));
	}	
	
	/**
	 * Press.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public static final boolean isPress(int key)
	{
		if (key < 0 || key >= keys.length) return false;
		return (keys[key] == KeyState.FTRIGGER || keys[key] == KeyState.TRIGGER || keys[key] == KeyState.PRESSED);
	}	
	
	/**
	 * Released.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public static final boolean isReleased(int key)
	{
		if (key < 0 || key >= keys.length) return false;
		return (keys[key] == KeyState.FRELEASED || keys[key] == KeyState.RELEASED);
	}	
	
	/**
	 * Dir4.
	 *
	 * @return the int
	 */
	public static final int dir4()
	{
		if (Keyboard.isPress(UP))         { return 2; }
		else if (Keyboard.isPress(DOWN))  { return 8; }
		else if (Keyboard.isPress(LEFT))  { return 4; }
		else if (Keyboard.isPress(RIGHT)) { return 6; }
		return 0;
	}
	
	/**
	 * Dir8.
	 *
	 * @return the int
	 */
	public static final int dir8()
	{
		return 0;
	}	
	
	/**
	 * Initialize.
	 */
	public static final void initialize()
	{
		keys = new KeyState[256];
		for (int i = 0; i < keys.length; i++)
		{
			keys[i] = KeyState.UP;
		}
	}
	
	/**
	 * Update.
	 */
	public static final void update()
	{
		frameCount = (frameCount + 1) % 4;
		for (int i = 0; i < keys.length; i++)
		{
			switch (keys[i])
			{
				case FTRIGGER:
					keys[i] = KeyState.TRIGGER;
					break;
				case TRIGGER:
					keys[i] = KeyState.PRESSED;
					break;
				case FRELEASED:
					keys[i] = KeyState.RELEASED;
					break;
				case RELEASED:
					keys[i] = KeyState.UP; 
					break;
			}
		}
	}
	
	/**
	 * Sets the key press.
	 * 
	 * @param keyID
	 *            the new key press
	 */
	public static final void setKeyPress(int keyID)
	{
		if (keyID > keys.length) return;
		if (keys[keyID] == KeyState.UP) keys[keyID] = KeyState.FTRIGGER;
	}
	
	/**
	 * Sets the key release.
	 * 
	 * @param keyID
	 *            the new key release
	 */
	public static final void setKeyRelease(int keyID)
	{
		if (keyID > keys.length) return;
		keys[keyID] = KeyState.FRELEASED;
	}	
	
}
