package org.jgel.audio;

public interface SoundFile
{
	public void play();
	public void stop();
	
	public void setVolume(int volume);
	public void setPitch(int pitch);
	public void setPan(int pan);
	public int getVolume();
	public int getPitch();
	public int getPan();		
	
}
