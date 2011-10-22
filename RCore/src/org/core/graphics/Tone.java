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
package r.core.graphics;

// TODO: Auto-generated Javadoc
/**
 * The Class Tone.
 */
public class Tone
{

	/** The gray. */
	private int red, green, blue, gray;
	
	/**
	 * Instantiates a new tone.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 */
	public Tone(int red, int green, int blue)
	{
		this(red, green, blue, 0);
	}
	
	/**
	 * Instantiates a new tone.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 * @param gray
	 *            the gray
	 */
	public Tone(int red, int green, int blue, int gray)
	{
		this.red   = (red   > 255) ? 255 : ((red   < -255) ? -255 : red);
		this.green = (green > 255) ? 255 : ((green < -255) ? -255 : green);
		this.blue  = (blue  > 255) ? 255 : ((blue  < -255) ? -255 : blue);
		this.gray  = (gray  > 255) ? 255 : ((gray  <    0) ?    0 : gray);
	}	
	
	/**
	 * Gets the red.
	 * 
	 * @return the red
	 */
	public final int getRed()
	{
		return this.red;
	}

	/**
	 * Gets the green.
	 * 
	 * @return the green
	 */
	public final int getGreen()
	{
		return this.green;
	}	
	
	/**
	 * Gets the blue.
	 * 
	 * @return the blue
	 */
	public final int getBlue()
	{
		return this.blue;
	}	
	
	/**
	 * Gets the gray.
	 * 
	 * @return the gray
	 */
	public final int getGray()
	{
		return this.gray;
	}	
	
	/**
	 * Sets the red.
	 * 
	 * @param value
	 *            the new red
	 */
	public final void setRed(int value)
	{
		this.red   = (red   > 255) ? 255 : ((red   < -255) ? -255 : red);
	}
	
	/**
	 * Sets the green.
	 * 
	 * @param value
	 *            the new green
	 */
	public final void setGreen(int value)
	{
		this.green = (green > 255) ? 255 : ((green < -255) ? -255 : green);
	}	
	
	/**
	 * Sets the blue.
	 * 
	 * @param value
	 *            the new blue
	 */
	public final void setBlue(int value)
	{
		this.blue  = (blue  > 255) ? 255 : ((blue  < -255) ? -255 : blue);
	}	
	
	/**
	 * Sets the gray.
	 * 
	 * @param value
	 *            the new gray
	 */
	public final void setGray(int value)
	{
		this.gray  = (gray  > 255) ? 255 : ((gray  <    0) ?    0 : gray);
	}	
	
	/**
	 * Sets the.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 */
	public final void set(int red, int green, int blue)
	{
		this.red   = (red   > 255) ? 255 : ((red   < -255) ? -255 : red);
		this.green = (green > 255) ? 255 : ((green < -255) ? -255 : green);
		this.blue  = (blue  > 255) ? 255 : ((blue  < -255) ? -255 : blue);
	}		
	
	/**
	 * Sets the.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 * @param gray
	 *            the gray
	 */
	public final void set(int red, int green, int blue, int gray)
	{
		this.red   = (red   > 255) ? 255 : ((red   < -255) ? -255 : red);
		this.green = (green > 255) ? 255 : ((green < -255) ? -255 : green);
		this.blue  = (blue  > 255) ? 255 : ((blue  < -255) ? -255 : blue);
		this.gray  = (gray  > 255) ? 255 : ((gray  <    0) ?    0 : gray);
	}	
	
}
