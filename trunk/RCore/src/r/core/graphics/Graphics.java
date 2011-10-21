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

import java.awt.AlphaComposite;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.jhlabs.image.BoxBlurFilter;
import com.jhlabs.image.GradientWipeFilter;

import r.core.applet.GameApplet;

/**
 * The Graphics class. Used yo Manipulate all the data of the Graphics.
 * 
 * @author Ramiro
 * @version 1.0 08/09/2011
 */
public abstract class Graphics 
{

	/**
	 * The Quality values of the Graphics configurations.
	 */
	public static enum Quality
	{
		
		/** The lowest quality, tries to speed up the graphics as much as possible. */
		VERY_LOW, 
		/** The low configuration, tries to keep anti aliasing in the rotated images */
		LOW, 
		/** The standart quality, desactivates time consumming process. */
		MID, 
		/** The above standart quality. It desactivets the most time consume process. */
		HIGH, 
		/** The highest quality. gives quality over speed priority */
		VERY_HIGH, 
		/** The default configuration of the drivers (Recommended) */
		DEFAULT
	}
	
	/**
	 * The transition kinds.
	 */
	private static enum Transition
	{
		
		/** The effect is just fading the screen */
		NONE, 
		/** The effect does a blur effect over the last freezed image */
		BLUR, 
		/** This effects overlays an image with other using a mask */
		WIPE, 
		/** Lets you to make custom transitions. */
		CUSTOM
	}	
	
	/** The frame used only to check. */
	private static Container frame;
	
	/** The objects than are drawed on screen. */
	private static PriorityQueue<ZObject> objects;
	
	/** The data than the graphics are drawing. */
	private static Graphics2D data;
	
	/** The width of the graphics area. */
	private static int width;
	/** The height of the graphics area. */
	private static int height;
	
	/** The frames per second. */
	private static int fps;
	
	/** The ticks. */
	private static int ticks;
	
	/** The next_tick. */
	private static long next_tick;
	
	/** The frame count. */
	private static long frameCount;
	
	/** The buffer. */
	private static BufferedImage buffer, bufferedTransition;
	
	/** The real fps. */
	private static long realFPS;
	
	/** The max frame skips. */
	private static int maxFrameSkips;
	
	/** The need object update. */
	private static boolean needObjectUpdate;
	
	/** The frame skip tolerance. */
	private static int frameSkipTolerance;
	
	/** Initialize. */
	
	private static GraphicsConfiguration gc;
	
	/** The object array. */
	private static ZObject[] objectArray;
	
	/** The g2d. */
	private static Graphics2D g2d; 
	
	/** The is freezed. */
	private static boolean isFreezed;
	
	/** The freeze image. */
	private static BufferedImage freezeImage;
	
	/** The original transition time. */
	private static int transitionTime, originalTransitionTime;
	
	/** The transition image. */
	private static BufferedImage transitionImage;
	
	/** The transition mode. */
	private static Transition transitionMode;
	
	/** The transition effect. */
	private static TransitionEffect transitionEffect;
	
	private static HashMap<String, BufferedImage> cache;
	
	/**
	 * Initialize.
	 * 
	 * @param width
	 *            the width of the screen
	 * @param height
	 *            the height of the screen
	 * @param frame
	 *            the frame to draw over.
	 */
	public static synchronized final void initialize(int width, int height, Container frame)
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gc = gd.getDefaultConfiguration();		
		objects = new PriorityQueue<ZObject>(20, new ZObjectComparator());
		Graphics.width = width;
		Graphics.height = height;
		fps = 60;
		ticks = 1000 / fps;
		next_tick = Calendar.getInstance().getTimeInMillis();
		frameCount = 0;
		buffer = gc.createCompatibleImage(width, height);
		realFPS = 0;
		Graphics.frame = frame;
		maxFrameSkips = 0;
		frameSkipTolerance = 50;
		needObjectUpdate = false;
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);		
		g2d = buffer.createGraphics();
		isFreezed = false;
		cache = new HashMap<String, BufferedImage>();
	}
	
	/**
	 * Creates a compatible image with the system.
	 * 
	 * @param width
	 *            the width of the image
	 * @param height
	 *            the height of the image
	 * @return the image data corresponding to a new compatible image.
	 */
	public static final BufferedImage createCompatibleImage(int width, int height)
	{
		BufferedImage img = gc.createCompatibleImage(width, height,Transparency.TRANSLUCENT);
		return img;
	}
	
	/**
	 * Loads an image and makes it compatible with the system.
	 * 
	 * @param filename
	 *            the filename to load, can be an URL.
	 * @return the image data associated to the loaded data.
	 */
	public static final BufferedImage loadCompatibleImage(String filename)
	{
		BufferedImage b = cache.get(filename);
		if (b != null) return b;
		BufferedImage image, tempImage;
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			try {
				image = ImageIO.read(new URL(GameApplet.getCodeBase2(), filename));				
			} catch (Exception e1) {
				try {
					image = ImageIO.read(new URL(filename));
				} catch (Exception e2) {
					image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
					System.err.println(String.format("Can't open or load Image %s", filename));
				}
			}
		}		
		tempImage = createCompatibleImage(image.getWidth(),image.getHeight());
		tempImage.getGraphics().drawImage(image, 0, 0, null);
		cache.put(filename, tempImage);
		return tempImage;
		
	}
	
	/**
	 * Gets the max frame skip.
	 * 
	 * @return the max frame skip
	 */
	public static final int getMaxFrameSkip()
	{
		return maxFrameSkips;
	}
	
	/**
	 * Sets the max frame skip.
	 * 
	 * @param value
	 *            the new max frame skip
	 */
	public static synchronized final void setMaxFrameSkip(int value)
	{
		 maxFrameSkips = value;
	}	
	
	/**
	 * Gets the skip tolerance time.
	 * 
	 * @return the skip tolerance time
	 */
	public final int getSkipToleranceTime()
	{
		return frameSkipTolerance;
	}
	
	/**
	 * Sets the skip tolerance time.
	 * 
	 * @param value
	 *            the new skip tolerance time
	 */
	public synchronized final void setSkipToleranceTime(int value)
	{
		frameSkipTolerance = value;
	}
	
	/**
	 * Initialize the graphics engine.
	 * 
	 * @param width
	 *            the width of the drawing area
	 * @param height
	 *            the height of the drawing area
	 * @param applet
	 *            the applet to run inside
	 */
	public synchronized static final void initialize(int width, int height, GameApplet applet)
	{
		initialize(width, height, (JFrame)null);
		data = (Graphics2D)applet.getGraphics();
	}		
	
	/**
	 * Gets the frame rate desired.
	 *
	 * @return the frame rate
	 */
	public static final int getFrameRate()
	{
		return fps;
	}
	
	/**
	 * Gets the real frame rate.
	 *
	 * @return the real frame rate
	 */
	public static final long getRealFrameRate()
	{
		return realFPS;
	}
	
	/**
	 * Sets the frame rate desired.
	 *
	 * @param value the new frame rate
	 */
	public synchronized static final void setFrameRate(int value)
	{
		fps = value;
		ticks = 1000 / fps;
	}
	
	/**
	 * Resizes the screen.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public synchronized static final void resize(int width, int height)
	{
		Graphics.width = width;
		Graphics.height = height;
		if (frame != null) frame.setSize(width, height);
		data.setColor(java.awt.Color.BLACK);
		data.fillRect(0, 0, width, height);		
	}
	
	/**
	 * Updates the graphics engine.
	 */
	public synchronized static final void update()
	{
		if (isFreezed) return;
		if (needObjectUpdate)
		{
			reorganizeObjects();
			needObjectUpdate = false;
		}
		if (frame != null && data == null)
		{
			if (frame instanceof JFrame)
				data = (Graphics2D)((JFrame) frame).getContentPane().getGraphics();
			else
				data = (Graphics2D)frame.getGraphics();
		}
		next_tick += ticks;
		data.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); 
		long first_tick = Calendar.getInstance().getTimeInMillis();	
		g2d.clearRect(0, 0, width, height);
		for (int i = 0; i < objectArray.length; i++) objectArray[i].drawContent(g2d);
		data.drawImage(buffer, 0, 0, null);
		regulateFrameSpeed(first_tick);
		frameCount++;
	}
	
	/**
	 * Regulates the frame speed.
	 * 
	 * @param first_tick
	 *            the first tick when the frame started
	 */
	private synchronized static final void regulateFrameSpeed(long first_tick)
	{
		long wait = 0;
		long new_tick = Calendar.getInstance().getTimeInMillis();
		try {
			wait = next_tick - new_tick;
			next_tick = new_tick;
			if (wait > 0) Thread.sleep(wait);
			else if (wait < 0)
			{
				
				int skipped = 0;
				while (skipped < maxFrameSkips && wait < - frameSkipTolerance)
				{
					long curr_tick = Calendar.getInstance().getTimeInMillis();
					GameApplet.getCurrentScene().updateFrameSkip();
					wait += Calendar.getInstance().getTimeInMillis() - curr_tick;
					skipped++;
				}
				
			}
		} catch (InterruptedException e) {
		}
		new_tick = Calendar.getInstance().getTimeInMillis();
		next_tick = new_tick;
		if ((new_tick - first_tick) != 0 && (frameCount % fps) == 0) realFPS = 1000 / (new_tick - first_tick);		
	}
	
	
	
	/**
	 * @return the width of the graphics area
	 */
	public static final int width()
	{
		return width;
	}
	
	
	/**
	 * Height.
	 *
	 * @return the height if the graphics area.
	 */
	public static final int height()
	{
		return height;
	}
	
	/**
	 * Removes an object asosiated to the graphics.
	 * This method is called automatically when you dispose a ZObject.
	 *
	 * @param object the object to remove
	 */
	public synchronized static final void removeZObject(ZObject object)
	{
		objects.remove(object);
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);		
	}
	
	/**
	 * Adds a new Object to draw.
	 * This method is called automatically when the object is created.
	 * 
	 * @param object the object to add
	 */
	public synchronized static final void addZObject(ZObject object)
	{
		objects.add(object);
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);		
	}

	/**
	 * Reorganize the objects by its depth.
	 */
	public synchronized static final void reorganizeObjects() 
	{
		objects = new PriorityQueue<ZObject>(objects);
		objectArray = new ZObject[objects.size()];
		objectArray = objects.toArray(objectArray);
	}

	/**
	 * Finish the graphics engine.
	 */
	public synchronized static void terminate() 
	{
		if (objects != null)
		{
			Iterator<ZObject> iterator = objects.iterator();
			while (iterator.hasNext()) iterator.next().dispose();
		}
	}
	
	/**
	 * Takes an screenshot of the current area draw.
	 * 
	 * @return the bitmap containing an screenshot.
	 */
	public static Bitmap screenshot()
	{
		return new Bitmap(buffer);
	}
	
	/**
	 * Adds the advice to reorganize the objects.
	 */
	public synchronized static final void reorganize()
	{
		needObjectUpdate = true;
	}
	
	/**
	 * Prepares a normal transition only if the Graphics are freezed.
	 */
	public synchronized static final void transition()
	{
		transition(10, "");
	}
	
	/**
	 * Prepares a normal transition with the current time only if the Graphics are freezed.
	 * 
	 * @param time
	 *            the duration of the transition.
	 */
	public synchronized static final void transition(int time)
	{
		transition(time, "");
	}	
	
	/**
	 * Prepares a normal transition with the current time only if the Graphics are freezed.
	 * It will use the transition as a Mask to make a Wipe Effect.
	 * 
	 * @param time
	 *            the time of the transition
	 * @param transition
	 *            the filename of the mask to aply durning the transition
	 */
	public synchronized static final void transition(int time, String transition)
	{
		if (!isFreezed) return;
		isFreezed = false;
		bufferedTransition = Graphics.createCompatibleImage(width, height);
		Graphics2D g2d = bufferedTransition.createGraphics();
		for (int i = 0; i < objectArray.length; i++) objectArray[i].drawContent(g2d);
		originalTransitionTime = transitionTime = time;
		if (transition != null && transition != "")
		{
			transitionImage = Graphics.loadCompatibleImage(transition);
			transitionMode = Transition.WIPE;
		}
		else
		{
			transitionImage = null;
			transitionMode = Transition.NONE;
		}
	}	
	
	/**
	 * Performs a blur transition in the specified amount of time.
	 * 
	 * @param time
	 *            the time of the transition
	 * @param blurTransition
	 *            sets to true to make the transition to make the blur effect
	 */
	public synchronized static final void transition(int time, boolean blurTransition)
	{
		if (!isFreezed) return;
		if (blurTransition)
		{
			isFreezed = false;
			bufferedTransition = Graphics.createCompatibleImage(width, height);
			Graphics2D g2d = bufferedTransition.createGraphics();
			for (int i = 0; i < objectArray.length; i++) objectArray[i].drawContent(g2d);
			originalTransitionTime = transitionTime = time;			
			transitionMode = Transition.BLUR;
			transitionImage = null;
		}
		else transition(time);
	}
	
	/**
	 * performs a transition with a custom effect.
	 * 
	 * @param time
	 *            the time of the transition
	 * @param transition
	 *            the transition effect used
	 */
	public synchronized static final void transition(int time, TransitionEffect transition)
	{
		if (!isFreezed) return;
		isFreezed = false;
		bufferedTransition = Graphics.createCompatibleImage(width, height);
		Graphics2D g2d = bufferedTransition.createGraphics();
		for (int i = 0; i < objectArray.length; i++) objectArray[i].drawContent(g2d);		
		transitionEffect = transition;
		transitionEffect.setBitmaps(new Bitmap(freezeImage), new Bitmap(bufferedTransition));
		transitionMode = Transition.CUSTOM;
		originalTransitionTime = transitionTime = time;	
		transitionEffect.setTimer(time);
	}
	
	/**
	 * Updates the current transition.
	 */
	public synchronized static void updateTransition()
	{
		if (frame != null && data == null)
		{
			if (frame instanceof JFrame)
				data = (Graphics2D)((JFrame) frame).getContentPane().getGraphics();
			else
				data = (Graphics2D)frame.getGraphics();
		}
		long first_tick = Calendar.getInstance().getTimeInMillis();	
		float t = transitionTime / (float)originalTransitionTime;
		switch (transitionMode)
		{
			case NONE:
				g2d.clearRect(0, 0, width, height);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - t)); 
				g2d.drawImage(bufferedTransition, 0, 0, null);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t));  
				g2d.drawImage(freezeImage, 0, 0, null);
				data.drawImage(buffer, 0, 0, null);				
				break;
			case BLUR:
				BoxBlurFilter filter = new BoxBlurFilter();
				g2d.clearRect(0, 0, width, height);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); 
				g2d.drawImage(bufferedTransition, 0, 0, null);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t));  
				filter.setRadius((1 - t) * 100);
				filter.setIterations(Math.round((1 - t) * 2));
				g2d.drawImage(freezeImage,filter, 0, 0);
				data.drawImage(buffer, 0, 0, null);					
				break;
			case WIPE:
				GradientWipeFilter wipeFilter = new GradientWipeFilter();
				wipeFilter.setMask(transitionImage);
				wipeFilter.setDensity(1 - t);
				g2d.clearRect(0, 0, width, height);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); 
				g2d.drawImage(bufferedTransition, 0, 0, null);		
				g2d.drawImage(freezeImage,wipeFilter, 0, 0);
				data.drawImage(buffer, 0, 0, null);					
				break;
			case CUSTOM:
				transitionEffect.updateTransition(transitionTime);
				update();
				if (transitionTime == 1) transitionEffect.finalizeTransition();
				break;
		}
	
		regulateFrameSpeed(first_tick);
		transitionTime--;			
	}
	
	/**
	 * Checks if the graphics need a transition.
	 * 
	 * @return true, if successful
	 */
	public static boolean needTransition()
	{
		return (transitionTime > 0);
	}
	
	
	/**
	 * Freezes the screen. It is a needed step before making a transition.
	 */
	public synchronized static final void freeze()
	{
		isFreezed = true;
		Graphics2D g2d = buffer.createGraphics();
		g2d.clearRect(0, 0, width, height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); 
		for (int i = 0; i < objectArray.length; i++) objectArray[i].drawContent(g2d);
		freezeImage = Graphics.createCompatibleImage(width, height);
		freezeImage.createGraphics().drawImage(buffer, 0, 0, null);
	}
	
	/**
	 * Checks if is the graphics are freezed.
	 * 
	 * @return true, if ithe graphics are freezed.
	 */
	public static final boolean isFreezed()
	{
		return isFreezed;
	}
	
	/**
	 * Sets the quality of the graphics.
	 * 
	 * @param quality
	 *            the new quality
	 */
	public static void setQuality(Quality quality)
	{
		if (g2d == null) return;
		switch (quality)
		{
			case VERY_LOW:
				RenderingHints[] rh = 
				{
					new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF),
					new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED ),
					new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED),
					new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE),
					new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF),
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED),
					new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
				};			
				for (int i = 0; i < rh.length; i++) g2d.setRenderingHints(rh[i]);
				break;
			case LOW:
				RenderingHints[] rh2 = 
				{
						new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
						new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED),
						new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED),
						new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE),
						new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF),
						new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED),
						new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
				};			
				for (int i = 0; i < rh2.length; i++) g2d.setRenderingHints(rh2[i]);				
				break;
			case MID:
				RenderingHints[] rh3 = 
				{
						new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
						new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY),
						new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY),
						new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE),
						new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF),
						new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED),
						new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
				};			
				for (int i = 0; i < rh3.length; i++) g2d.setRenderingHints(rh3[i]);				
				break;
			case HIGH:
				RenderingHints[] rh4 = 
				{
					new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
					new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY),
					new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY),
					new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE),
					new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF),
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED),
					new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
				};				
				for (int i = 0; i < rh4.length; i++) g2d.setRenderingHints(rh4[i]);				
				break;
			case VERY_HIGH:
				RenderingHints[] rh5 = 
				{
					new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
					new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY),
					new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY),
					new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE),
					new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON),
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY),
					new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
				};			
				for (int i = 0; i < rh5.length; i++) g2d.setRenderingHints(rh5[i]);				
				break;
			default:
				RenderingHints[] rh6 = 
				{
					new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT),
					new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT),
					new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_DEFAULT),
					new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DEFAULT),
					new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT),
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT),
					new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)
				};			
				for (int i = 0; i < rh6.length; i++) g2d.setRenderingHints(rh6[i]);				
				break;
		}
	}

}
