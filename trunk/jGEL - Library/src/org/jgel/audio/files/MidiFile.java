package org.jgel.audio.files;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.jgel.applet.GameApplet;
import org.jgel.audio.MusicFile;
import org.jgel.audio.SoundFile;

public class MidiFile implements MusicFile, SoundFile
{
	private AudioClip clip;
	
	public MidiFile(String filename, int volume, int pitch, int pan)
	{
		
		try
		{
			File f = new File(filename);
			clip = Applet.newAudioClip(f.toURI().toURL());
		} catch (Exception e)
		{
			try
			{
				clip = Applet.newAudioClip(new URL(GameApplet.getCodeBase2(), filename));
			} catch (Exception e1)
			{
				try
				{
					clip = Applet.newAudioClip(new URL(filename));
				} catch (MalformedURLException e2)
				{
					clip = null;
				}
			}
		}
		if (clip != null)
		{
		}
	}
	
	@Override
	public void play()
	{
		clip.play();
	}

	@Override
	public void stop()
	{
		clip.stop();
	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void fade(int time)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setVolume(int volume)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setPitch(int pitch)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setPan(int pan)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getVolume()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPitch()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPan()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void loop()
	{
		// TODO Auto-generated method stub
		
	}

}
