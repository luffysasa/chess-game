package assignment1.Sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
	public void playMusic(String namewav)
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
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
