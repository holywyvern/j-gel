package org.jgel.audio;

import java.io.Serializable;

public abstract class AudioBase implements Serializable
{
	public String name;
	public int volume;
	public int pitch;
	public int pan;
	public int pos;
	
	
	public abstract void play();
	public abstract void stop();
	
}
