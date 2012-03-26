package org.jgel.managers;

import java.util.Stack;

import org.jgel.graphics.Bitmap;
import org.jgel.graphics.Graphics;
import org.jgel.scene.SceneBase;

import com.jhlabs.image.BlurFilter;
import com.jhlabs.image.NoiseFilter;

public final class SceneManager
{
	private static Stack<SceneBase> stack;
	private static SceneBase scene;
	private static Bitmap backgroundImage;
	
	private SceneManager()
	{
		
	}
	
	public static final void goTo(SceneBase scene)
	{
		if (stack == null) stack = new Stack<SceneBase>();
		stack.clear();
		SceneManager.scene = scene;
		scene.main();
		
	}
	
	public static final void goStack(SceneBase scene)
	{
		if (stack == null) stack = new Stack<SceneBase>();
		stack.push(SceneManager.scene);
		SceneManager.scene = scene;
		if (scene != null) scene.main();		
	}
	
	public static final void returnScene()
	{
		if (stack == null) stack = new Stack<SceneBase>();
		if (stack.size() > 0) { SceneManager.scene = stack.pop(); }  else SceneManager.scene = null;
		if (scene != null) scene.main();	
	}
	
	public static final SceneBase scene()
	{
		return scene;
	}
	
	public static final void run(SceneBase firstScene)
	{
		goTo(firstScene);
	}
	
	public static final boolean isScene(SceneBase scene)
	{
		return (scene == SceneManager.scene);
	}
	
	public static final void makeBackgroundBitmap()
	{
		backgroundImage = Graphics.screenshot();
		backgroundImage.applyFilter(new BlurFilter() );
		backgroundImage.applyFilter(new NoiseFilter());
	}
	
	public static final Bitmap backgroundBitmap()
	{
		return backgroundImage;
	}
	
}
