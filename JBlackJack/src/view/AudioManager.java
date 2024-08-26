package view;

import java.io.*;
import javax.sound.sampled.*;

/**
 * classe per gestire la riproduzione di clip audio
 */
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

	/**
	 * metodo per riprodurre clip audio
	 * @param filename file wav da riprodurre
	 * @param loop booleano che decide se riprodurre in loop o no
	 */
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
                gainControl.setValue(-30.0f);
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
