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

// TODO: Auto-generated Javadoc
/**
 * The Interface TransitionEffect.
 */
public interface TransitionEffect
{
	
	/**
	 * Sets the bitmaps.
	 * 
	 * @param lastStateBitmap
	 *            the last state bitmap
	 * @param newStateBitmap
	 *            the new state bitmap
	 */
	public void setBitmaps(Bitmap lastStateBitmap, Bitmap newStateBitmap);
	
	/**
	 * Sets the timer.
	 * 
	 * @param time
	 *            the new timer
	 */
	public void setTimer(int time);
	
	/**
	 * Update transition.
	 * 
	 * @param count
	 *            the count
	 */
	public void updateTransition(int count);	
	
	/**
	 * Finalize transition.
	 */
	public void finalizeTransition();
}
