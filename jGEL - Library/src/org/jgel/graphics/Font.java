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

import java.awt.Color;
import java.awt.GraphicsEnvironment;

/**
 * The Font class, contains all the data of the fonts and Drawing Styles.
 * 
 * @author Ramiro
 * @version 1.0 08/09/2011
 */
public final class Font 
{
	
	/** The default name. */
	public static String defaultName  =  java.awt.Font.SANS_SERIF;
	
	/** The default size. */
	public static int    defaultSize  = 24;
	
	/** The default color. */
	public static Color  defaultColor          = Color.WHITE;
	
	/** The default shadow color. */
	public static Color  defaultShadowColor    = Color.GRAY;
	
	/** The default underline color. */
	public static Color  defaultUnderlineColor = Color.WHITE;	
	
	/** The default strike color. */
	public static Color  defaultStrikeColor    = Color.BLACK;
	
	/** The default border color. */
	public static Color  defaultBorderColor    = Color.BLACK;
	
	/** The default shadow. */
	public static boolean defaultShadow    = true;
	
	/** The default underline. */
	public static boolean defaultUnderline = false;
	
	/** The default border. */
	public static boolean defaultBorder    = true;
	
	/** The default strike. */
	public static boolean defaultStrike    = false;
	
	/** The default bold. */
	public static boolean defaultBold   = false;
	
	/** The default italic. */
	public static boolean defaultItalic = false;
	
	/** The name. */
	public String name;
	
	/** The size. */
	public int size;
	
	/** The border color. */
	public Color color, shadowColor, underlineColor, strikeColor, borderColor;
	
	/** The italic. */
	public boolean shadow, underline, strike, border, bold, italic;
	
	/**
	 * Instantiates a new font.
	 */
	public Font()
	{
		this(defaultName, defaultSize);
	}
	
	/**
	 * Instantiates a new font.
	 *
	 * @param name the name
	 */
	public Font(String name)
	{
		this(name, defaultSize);
	}
	
	/**
	 * Instantiates a new font.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public Font(String name, int size)
	{
		this(name, size, defaultColor);
	}	
	
	/**
	 * Instantiates a new font.
	 *
	 * @param name the name
	 * @param size the size
	 * @param color the color
	 */
	public Font(String name, int size, Color color)
	{
		this.name = name;
		this.size = size;
		this.color = color;
		this.shadowColor = defaultShadowColor;
		this.underlineColor = defaultUnderlineColor;
		this.strikeColor    = defaultStrikeColor;
		this.borderColor    = defaultBorderColor;
		this.shadow = defaultShadow;
		this.underline = defaultUnderline;
		this.strike = defaultStrike;
		this.border = defaultBorder;
		this.bold   = defaultBold;
		this.italic = defaultItalic;
	}
	
	/**
	 * Checks if the font exists
	 * 
	 * @param font the name of the font
	 * @return true, if the font exist. 
	 */
	public static boolean exits(String font)
	{
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 0; i < fonts.length; i++)
		if (fonts[i].equalsIgnoreCase(font)) return true;
		return false;
	}
}
