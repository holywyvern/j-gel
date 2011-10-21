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
package r.core.scene;

import r.core.graphics.Graphics;
import r.core.input.Keyboard;
import r.core.input.Mouse;
import r.core.applet.GameApplet;

// TODO: Auto-generated Javadoc
/**
 * The Class SceneBase.
 */
public abstract class SceneBase 
{
	
	/** The LOCK. */
	private static Object LOCK = new Object();
	

	/**
	 * Main.
	 */
	public final void main()
	{
		synchronized (LOCK)
		{
			start();
			Mouse.update();
			Keyboard.update();
			performTransition();
			postStart();
			while (GameApplet.scene == this)
			{
				if (Graphics.needTransition()) Graphics.updateTransition();
				else
				{
					Mouse.update();
					Keyboard.update();
					Graphics.update();
					update();
				}
			}
			preTerminate();
			Graphics.freeze();
			Mouse.update();
			Keyboard.update();
			terminate();
		}
	}

	/**
	 * Terminate.
	 */
	protected abstract void terminate();
	
	/**
	 * Pre_terminate.
	 */
	protected abstract void preTerminate();
	
	/**
	 * Post_start.
	 */
	protected abstract void postStart();
	
	/**
	 * Start.
	 */
	protected abstract void start();
	
	/**
	 * Update.
	 */
	protected abstract void update();
	
	/**
	 * Perform_transition.
	 */
	protected void performTransition()
	{
		Graphics.transition(20);
	}
	
	/**
	 * Update frame skip.
	 */
	public final void updateFrameSkip()
	{
		update();
	}
	
	
}
