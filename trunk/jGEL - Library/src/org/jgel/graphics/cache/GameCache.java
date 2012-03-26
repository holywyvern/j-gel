package org.jgel.graphics.cache;

import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import org.jgel.audio.*;
import org.jgel.graphics.Bitmap;

public class GameCache implements Serializable
{
	
	private static GameCache cache;
	public HashMap<String, SoundFile> sounds;
	public HashMap<String, MusicFile> musics;
	public HashMap<String, Bitmap> bitmaps;
	
	private GameCache()
	{
		sounds  = new HashMap<String, SoundFile>();
		musics  = new HashMap<String, MusicFile>();
		bitmaps = new HashMap<String, Bitmap>();
	}
	
	public static final void loadCache(String filename) 
	{
		if (cache == null) cache = new GameCache();
		File f = new File(filename);
		f.setReadable(true);
	}
	
	public static final void saveCache(String filename)
	{
		if (cache == null) cache = new GameCache();
	}
	
	
	public static final void saveMusic(String filename, MusicFile music)
	{
		if (cache == null) cache = new GameCache();
		cache.musics.put(filename, music);
	}
	
	public static final MusicFile loadMusic(String filename)
	{
		if (cache == null) cache = new GameCache();
		return cache.musics.get(filename);
	}
	
	public static final void saveSound(String filename, SoundFile sound)
	{
		if (cache == null) cache = new GameCache();
		cache.sounds.put(filename, sound);
	}
	
	public static final SoundFile loadSound(String filename)
	{
		if (cache == null) cache = new GameCache();
		return cache.sounds.get(filename);
	}	
	
	public static final void saveBitmap(String filename, Bitmap bitmap)
	{
		if (cache == null) cache = new GameCache();
		cache.bitmaps.put(filename, bitmap);
	}
	
	public static final Bitmap loadBitmap(String filename)
	{
		if (cache == null) cache = new GameCache();
		return cache.bitmaps.get(filename);
	}		
	
}
