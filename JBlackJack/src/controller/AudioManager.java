package controller;

import java.io.*;
import javax.sound.sampled.*;

public class AudioManager 
{
	private static AudioManager instance;

	public static AudioManager getInstance() 
	{
		if (instance == null)
			instance = new AudioManager();
		return instance;
	}

	private AudioManager() 
	{

	}

	public void play(String filename, boolean loop) 
	{
		try 
		{
			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			
			if(loop)
			{
				//abbassa il volume del sottofondo
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25.0f);
                //ripeti in loop
				clip.loop(Clip.LOOP_CONTINUOUSLY); 	
			}	
			
			clip.start();
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		} 
		catch (UnsupportedAudioFileException e1) 
		{
			e1.printStackTrace();
		} 
		catch (LineUnavailableException e1) 
		{
			e1.printStackTrace();
		}
	}
}
