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
package r.core.graphics.gui;

import r.core.graphics.Rect;

// TODO: Auto-generated Javadoc
/**
 * The Interface ChildContainer.
 */
public interface ChildContainer
{
	
	/**
	 * Adds the child.
	 * 
	 * @param child
	 *            the child
	 */
	public void addChild(Widget child);
	
	/**
	 * Removehild.
	 * 
	 * @param child
	 *            the child
	 */
	public void removehild(Widget child);
	
	/**
	 * Update childs.
	 */
	public void updateChilds();
	
	/**
	 * Refresh childs.
	 */
	public void refreshChilds();
	
	/**
	 * Dispose childs.
	 */
	public void disposeChilds();
	
	/**
	 * Gets the x.
	 * 
	 * @return the x
	 */
	public int getX();
	
	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	public int getY();
	
	/**
	 * Gets the content rect.
	 * 
	 * @return the content rect
	 */
	public Rect getContentRect();
	
	/**
	 * Checks if is mouse over.
	 * 
	 * @return true, if is mouse over
	 */
	public boolean isMouseOver();
}
