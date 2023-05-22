package assignment1.Sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ClickSound {
	public void playClickSound(String namewav)
	{
		try
		{
			File music = new File(namewav);
			
			if(music.exists())
			{
				AudioInputStream Input = AudioSystem.getAudioInputStream(music);
				Clip clip = AudioSystem.getClip();
				clip.open(Input);
				clip.start();
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
