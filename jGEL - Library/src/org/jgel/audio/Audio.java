package org.jgel.audio;

import org.jgel.audio.files.MidiFile;
import org.jgel.audio.files.Mp3File;
import org.jgel.audio.files.OggFile;
import org.jgel.audio.files.WMAFile;
import org.jgel.audio.files.WavFile;
import org.jgel.utils.file.FileInfo;

public abstract class Audio
{
	public static final void bgmPlay(String filename, int volume, int pitch, int pan)
	{
		
	}
	
	public static final void bgsPlay(String filename, int volume, int pitch, int pan)
	{
		
	}	
	
	public static final void mePlay(String filename, int volume, int pitch, int pan)
	{
		
	}	
	
	public static final void sePlay(String filename, int volume, int pitch, int pan)
	{
		
	}	
	
	public static final MusicFile loadMusicFile(String name, int volume, int pitch, int pan)
	{
		FileInfo fileInfo = new FileInfo(name, '/', '.');
		if (fileInfo.extension().toUpperCase() == ".MID")
		{
			return new MidiFile(name,volume, pitch, pan);
		} else if (fileInfo.extension().toUpperCase() == ".MP3")
		{
			return new Mp3File(name,volume, pitch, pan);
		} else if (fileInfo.extension().toUpperCase() == ".OGG")
		{
			return new OggFile(name,volume, pitch, pan);
		} else if (fileInfo.extension().toUpperCase() == ".WAV")
		{
			return new WavFile(name,volume, pitch, pan);
		} else if (fileInfo.extension().toUpperCase() == ".WMA")
		{
			return new WMAFile(name,volume, pitch, pan);
		}
		return null;
	}
	
	public static final SoundFile loadSoundFile(String name, int volume, int pitch, int pan)
	{
		FileInfo fileInfo = new FileInfo(name, '/', '.');
		if (fileInfo.extension().toUpperCase() == ".MID")
		{
			return new MidiFile(name,volume, pitch, pan);
		} else if (fileInfo.extension().toUpperCase() == ".OGG")
		{
			return new OggFile(name,volume, pitch, pan);
		} else if (fileInfo.extension().toUpperCase() == ".WAV")
		{
			return new WavFile(name,volume, pitch, pan);
		}
		return null;
	}	
	
	public static final void bgmStop()
	{
		
	}
	
	public static final void seStop()
	{
		
	}
	
	
	public static final void bgmFade(int time)
	{
		
	}
	
}
