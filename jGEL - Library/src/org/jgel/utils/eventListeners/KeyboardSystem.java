package org.jgel.utils.eventListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jgel.input.Keyboard;

public class KeyboardSystem implements KeyListener
{

	@Override
	public void keyPressed(KeyEvent e)
	{
		Keyboard.setKeyPress(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		Keyboard.setKeyRelease(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {  }

}
