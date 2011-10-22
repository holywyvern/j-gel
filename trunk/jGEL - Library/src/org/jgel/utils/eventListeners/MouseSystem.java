package org.jgel.utils.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jgel.input.Mouse;

public class MouseSystem implements MouseListener
{

	   public void mousePressed( MouseEvent e ) 
	   {
		   	  Mouse.sendPressedButton(e.getButton());
		      e.consume();
	   }
	   public void mouseReleased( MouseEvent e )
	   {
			  Mouse.sendReleasedButton(e.getButton());
		      e.consume();
	   }
	   @Override
	   public void mouseClicked(MouseEvent arg0) {}
	   @Override
	   public void mouseEntered(MouseEvent arg0) {}
	   @Override
	   public void mouseExited(MouseEvent arg0)  {}

}
