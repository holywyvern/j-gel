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
package r.core.math;

// TODO: Auto-generated Javadoc
/**
 * The Class RNG.
 */
public abstract class RNG
{

	/**
	 * Random.
	 * 
	 * @param minValue
	 *            the min value
	 * @param maxValue
	 *            the max value
	 * @return the int
	 */
	public static final int random(int minValue, int maxValue)
	{
		if (maxValue <= minValue) return 0;
		return Math.round((float)Math.random() *(maxValue - minValue) + minValue);
	}
	
	/**
	 * Random.
	 * 
	 * @param max
	 *            the max
	 * @return the int
	 */
	public static final int random(int max)
	{
		if (max <= 0) return 0;
		return Math.round((float)Math.random() * max);
	}	
	
	/**
	 * Random.
	 * 
	 * @param minValue
	 *            the min value
	 * @param maxValue
	 *            the max value
	 * @return the float
	 */
	public static final float random(float minValue, float maxValue)
	{
		if (maxValue <= minValue) return 0;
		return ((float)Math.random() *(maxValue - minValue) + minValue);
	}
	
	/**
	 * Random.
	 * 
	 * @param max
	 *            the max
	 * @return the float
	 */
	public static final float random(float max)
	{
		if (max <= 0) return 0;
		return ((float)Math.random() * max);
	}		
	
	/**
	 * Random.
	 * 
	 * @param minValue
	 *            the min value
	 * @param maxValue
	 *            the max value
	 * @return the long
	 */
	public static final long random(long minValue, long maxValue)
	{
		if (maxValue <= minValue) return 0;
		return Math.round(Math.random() *(maxValue - minValue) + minValue);
	}
	
	/**
	 * Random.
	 * 
	 * @param max
	 *            the max
	 * @return the long
	 */
	public static final long random(long max)
	{
		if (max <= 0) return 0;
		return Math.round(Math.random() * max);
	}		
	
	/**
	 * Random.
	 * 
	 * @param minValue
	 *            the min value
	 * @param maxValue
	 *            the max value
	 * @return the double
	 */
	public static final double random(double minValue, double maxValue)
	{
		if (maxValue <= minValue) return 0;
		return (Math.random() *(maxValue - minValue) + minValue);
	}
	
	/**
	 * Random.
	 * 
	 * @param max
	 *            the max
	 * @return the double
	 */
	public static final double random(double max)
	{
		if (max <= 0) return 0;
		return (Math.random() * max);
	}		
	
}
