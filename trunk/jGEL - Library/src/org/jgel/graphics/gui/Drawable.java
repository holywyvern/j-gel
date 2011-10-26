package org.jgel.graphics.gui;

import java.awt.Graphics;

public interface Drawable
{
	public void paint(Graphics g);
	public int getX();
	public int geyY();
	public void setX();
	public void setY();
	public int getWidth();
	public int getHeight();
	public int setHeight();
	public int setWidth();
	public void refresh();
}
