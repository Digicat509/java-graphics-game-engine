package catformer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl; 

@SuppressWarnings("removal")
class Sound 
{
	//public static final AudioClip COIN = Applet.newAudioClip(Sound.class.getResource("assets/smb_coin.wav"));
	AudioInputStream audioInputStream;
	Clip audio;
	public Sound()
	{
		try {
//			InputStream is = this.getClass().getResourceAsStream("./platformer_music.wav");
//			if (is == null) {
//			  throw new IOException("Failed to find "+ "platformer_music");
//			}
//			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
			
			File in = new File("C:\\Users\\alexa\\OneDrive\\Documents\\GitHub\\java-graphics-game-engine\\Games\\catformer\\assets\\platformer_music.wav");
			
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(in);
		    audio = AudioSystem.getClip();
		    audio.open(audioInputStream);
		    FloatControl volume= (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f); // Reduce volume by 10 decibols.
		}catch (Exception e){e.printStackTrace();}
	}
}
