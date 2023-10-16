package catformer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.*; 
 

@SuppressWarnings("removal")
class Sound 
{
	//public static final AudioClip COIN = Applet.newAudioClip(Sound.class.getResource("assets/smb_coin.wav"));
	//public static final AudioClip MUSIC = Applet.newAudioClip(Sound.class.getResource("assets/platformer_music.wav"));

	
	AudioInputStream audioInputStream;
	Clip audio;
	Clip creditsAudio;
	public Sound() 
	{	
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/platformer_music.wav"));
		    audio = AudioSystem.getClip();
		    audio.open(audioInputStream);
		    FloatControl volume= (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f); // Reduce volume by 10 decibols.
		}catch (Exception e){e.printStackTrace();}
		
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/credits_music.wav"));
		    creditsAudio = AudioSystem.getClip();
		    creditsAudio.open(audioInputStream);
		    FloatControl volume= (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f); // Reduce volume by 10 decibols.
		}catch (Exception e){e.printStackTrace();}
	}
}
