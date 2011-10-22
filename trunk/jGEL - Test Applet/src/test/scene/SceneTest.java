package test.scene;

import java.awt.Color;

import r.core.graphics.Bitmap;
import r.core.graphics.Graphics;
import r.core.graphics.Rect;
import r.core.graphics.Sprite;
import r.core.graphics.TextBitmap;
import r.core.scene.SceneBase;

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
