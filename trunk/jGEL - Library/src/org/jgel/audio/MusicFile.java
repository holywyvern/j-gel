package org.jgel.audio;

public interface MusicFile
{
	
	public void play();
	public void stop();
	public void pause();
	public void resume();
	public void fade(int time);
	
	public void setVolume(int volume);
	public void setPitch(int pitch);
	public void setPan(int pan);
	public int getVolume();
	public int getPitch();
	public int getPan();	
	
}
