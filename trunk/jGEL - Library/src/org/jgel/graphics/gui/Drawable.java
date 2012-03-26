package org.jgel.graphics.gui;

import java.awt.Graphics;

public interface Drawable
{
	public int x = 0;
	public int y = 0;
	
	public void draw(Graphics g);
	public int getWidth();
	public int getHeight();
	public int setHeight();
	public int setWidth();
	public void refresh();
}
