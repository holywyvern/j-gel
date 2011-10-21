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
package r.core.applet;

import r.core.graphics.Graphics;
import r.core.input.Keyboard;
import r.core.input.Mouse;
import r.core.scene.SceneBase;
import r.core.utils.eventListeners.KeyboardSystem;
import r.core.utils.eventListeners.MouseSystem;

import java.applet.Applet;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class GameApplet.
 */
public abstract class GameApplet extends Applet 
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2412542006038018181L;

	/** The scene. */
	public static SceneBase scene;
	
	/** The applet. */
	private static GameApplet applet;
	
	/** The frame. */
	private Container frame;
	
	/**
	 * Inits the engine.
	 * 
	 * @param scene
	 *            the scene
	 * @param f
	 *            the f
	 */
	private final void initEngine(SceneBase scene, Container f)
	{
		KeyListener kl = new KeyboardSystem();
		MouseListener ml = new MouseSystem();
		
		applet = this;
		this.setIgnoreRepaint(true);
		GameApplet.scene = scene;
		this.frame = f;
		if (frame != null)
		{
			frame.addMouseListener(ml);
			frame.addKeyListener(kl);
		}
		else
		{
			applet.addMouseListener(ml);	
			applet.addKeyListener(kl);
		}
		new Thread()
		{
			public void run()
			{
				this.setPriority(MAX_PRIORITY);
				Mouse.initialize();
				Keyboard.initialize();
				if (frame == null) Graphics.initialize(getGameInitialWidth(), getGameInitialHeight(), applet);
				else Graphics.initialize(getGameInitialWidth(), getGameInitialHeight(), frame);
				Graphics.freeze();
				while (GameApplet.scene != null) GameApplet.scene.main();
			}
		}.start();

	}
	
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#stop()
	 */
	public final void stop()
	{
	}
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#destroy()
	 */
	public final void destroy()
	{
		Graphics.terminate();
	}
	
	/**
	 * Gets the code base2.
	 * 
	 * @return the code base2
	 */
	public static URL getCodeBase2()
	{
		return applet.getCodeBase();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public final void update(java.awt.Graphics g)
	{
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#repaint()
	 */
	public final void repaint()
	{
		
	}
	
	/**
	 * Paint component.
	 * 
	 * @param g
	 *            the g
	 */
	public final void paintComponent( Graphics g )
	{
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update (Graphics g)
	{
		
	}
	
	/**
	 * Inits the engine.
	 * 
	 * @param game
	 *            the game
	 * @return the j frame
	 */
	public static final JFrame initEngineOnFrame(GameApplet game)
	{
		game.setSize(game.getGameInitialWidth(),game.getGameInitialHeight());
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		JFrame frame = new JFrame("");
		game.initApplet();
		frame.setSize(game.getWidth(), game.getHeight());
		frame.setVisible(true);	
		frame.setResizable(false);
		game.initEngine(game.getInitialScene(), frame);
		frame.setBounds(center.x - game.getWidth() / 2, center.y - game.getHeight() / 2,game.getWidth(), game.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setTitle(game.getGameName());
		Image icon = game.getGameIcon();
		if (icon != null) frame.setIconImage(game.getGameIcon());
		return frame;
	}
	
	public static final JPanel initEngineOnPanel(GameApplet game)
	{
		JPanel panel = new JPanel();
		panel.setSize(game.getGameInitialWidth(), game.getGameInitialHeight());
		game.initApplet();
		game.initEngine(game.getInitialScene(), panel);
		return panel;
	}
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#init()
	 */
	public final void init()
	{
		setSize(getGameInitialWidth(),getGameInitialHeight());
		initApplet();
		initEngine(getInitialScene(), null);
	}
	
	/**
	 * Gets the game icon.
	 * 
	 * @return the game icon
	 */
	public abstract Image getGameIcon();
	
	/**
	 * Inits the applet.
	 */
	public abstract void initApplet();
	
	/**
	 * Gets the initial scene.
	 * 
	 * @return the initial scene
	 */
	public abstract SceneBase getInitialScene();
	
	/**
	 * Gets the game name.
	 * 
	 * @return the game name
	 */
	public abstract String getGameName();
	
	/**
	 * Gets the game initial width.
	 * 
	 * @return the game initial width
	 */
	public abstract int getGameInitialWidth();
	
	/**
	 * Gets the game initial height.
	 * 
	 * @return the game initial height
	 */
	public abstract int getGameInitialHeight();


	/**
	 * Update engine.
	 */
	public static final void updateEngine()
	{
		Graphics.update();
		Mouse.update();
	}
	
	/**
	 * Gets the current scene.
	 * 
	 * @return the current scene
	 */
	public static final SceneBase getCurrentScene()
	{
		return GameApplet.scene;
	}
	
	/**
	 * Gets the position on screen.
	 * 
	 * @return the position on screen
	 */
	public static final Point getPositionOnScreen()
	{
		try
		{
			if (applet.frame != null)
			{
				if (applet.frame instanceof JFrame) 
				{
					return ((JFrame)applet.frame).getContentPane().getLocationOnScreen();
				}
				else 
				{
					return applet.frame.getLocationOnScreen();
				}	
			}	
			return GameApplet.applet.getLocationOnScreen();
		} catch (Exception e)
		{
			return new Point(0,0);
		}
	}
	
	
}
