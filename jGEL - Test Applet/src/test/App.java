package test;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jgel.applet.GameApplet;
import org.jgel.scene.SceneBase;
import test.scene.SceneTest;

public class App extends GameApplet {

	@Override
	public Image getGameIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initApplet() {
		// TODO Auto-generated method stub
	}

	@Override
	public SceneBase getInitialScene() {
		// TODO Auto-generated method stub
		return new SceneTest();
	}

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGameInitialWidth() {
		// TODO Auto-generated method stub
		return 320;
	}

	@Override
	public int getGameInitialHeight() {
		// TODO Auto-generated method stub
		return 240;
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 640, 480);
		JPanel panel = GameApplet.initEngineOnPanel(new App());
		frame.setLayout(null);
		panel.setLocation(64, 64);
		frame.add(panel);
		frame.setVisible(true);
	}
	
}
