package test.scene;

import java.awt.Color;

import org.jgel.graphics.Bitmap;
import org.jgel.graphics.Graphics;
import org.jgel.graphics.Rect;
import org.jgel.graphics.Sprite;
import org.jgel.graphics.TextBitmap;
import org.jgel.scene.SceneBase;

public class SceneTest extends SceneBase {

	private Sprite spr;
	private TextBitmap txtSprite;
	@Override
	protected void terminate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void preTerminate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void postStart() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void start() {
		spr = new Sprite();
		txtSprite = new TextBitmap();
		Bitmap bmp = new Bitmap(96,96);
		spr.setBitmap(bmp);
		bmp.gradientFillRect(new Rect(0,0,96,96),new Color(128,36,45), new Color(156,255,0));
		spr.ox = spr.width() / 2;
		spr.oy = spr.height() / 2;
		spr.x = Graphics.width() / 4;
		spr.y = Graphics.height() / 4;
	}

	@Override
	protected void update() {
		txtSprite.text = String.format("FPS = %03d/%03d",Graphics.getRealFrameRate(), Graphics.getFrameRate());
		spr.setAngle(spr.getAngle() + 1);
	}

}
