package org.jgel.audio;

public class MusicFile extends AudioBase
{
	public String name;
	public int volume;
	public int pitch;
	public int pan;
	public int pos;
	
	public MusicFile(String name)
	{
		this(name, 100, 100, 0, 0);
	}

	public MusicFile(String name, int volume)
	{
		this(name, volume, 100, 0, 0);
	}
	
	public MusicFile(String name, int volume, int pitch)
	{
		this(name, volume, pitch, 0, 0);
	}

	public MusicFile(String name, int volume, int pitch, int pos)
	{
		this(name, volume, pitch, pos, 0);
	}	
	
	public MusicFile(String name, int volume, int pitch, int pos, int pan)
	{
		
	}		
	
	public void play() 
	{
		
	}
	
	public void stop() 
	{
		
	}
	
	public void pause() 
	{
		
	}
	
	public void resume() 
	{
		
	}
	
	public void fade(int time) 
	{
		
	}


}
