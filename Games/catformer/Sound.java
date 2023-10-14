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
	public Sound() 
	{
		//Sound.COIN.play();
		//System.out.println("one");
		//Sound.MUSIC.play();
		//System.out.println("two");
		//String url = getClass().getResource("assets/smb_coin.wav").toString();
		//System.out.println("URL: "+url);
		
		
		try {
			
			File in = new File("C:\\Users\\alexa\\OneDrive\\Documents\\GitHub\\java-graphics-game-engine\\Games\\catformer\\assets\\platformer_music.wav");
			
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(in);
		    audio = AudioSystem.getClip();
		    audio.open(audioInputStream);
		    FloatControl volume= (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f); // Reduce volume by 10 decibols.
		}catch (Exception e){e.printStackTrace();}
	}
}
