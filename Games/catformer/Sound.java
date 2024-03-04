package catformer;

import javax.sound.sampled.*; 
 
class Sound 
{
	AudioInputStream audioInputStream;
	Clip audio;
	Clip creditsAudio;
	Clip bossAudio;
	Clip portalSound;
	Clip loudWoosh;
	Clip footsteps;
	Clip roboticIdentification;
	Clip roboticNoise;
	Clip robotWalk;
	public Sound() 
	{	
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/platformer_music.wav"));
		    audio = AudioSystem.getClip();
		    audio.open(audioInputStream);
		    FloatControl volume= (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f);
		}catch (Exception e){e.printStackTrace();}
		
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/credits_music.wav"));
		    creditsAudio = AudioSystem.getClip();
		    creditsAudio.open(audioInputStream);
		    FloatControl volume= (FloatControl) creditsAudio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f);
		}catch (Exception e){e.printStackTrace();}
		
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/boss_music.wav"));
		    bossAudio = AudioSystem.getClip();
		    bossAudio.open(audioInputStream);
		    FloatControl volume= (FloatControl) bossAudio.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f);
		}catch (Exception e){e.printStackTrace();}
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/portal_sound.wav"));
		    portalSound = AudioSystem.getClip();
		    portalSound.open(audioInputStream);
		    FloatControl volume= (FloatControl) portalSound.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(1.0f);
		}catch (Exception e){e.printStackTrace();}
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/loud_woosh.wav"));
		    loudWoosh = AudioSystem.getClip();
		    loudWoosh.open(audioInputStream);
		    FloatControl volume= (FloatControl) loudWoosh.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(-10.0f);
		}catch (Exception e){e.printStackTrace();}
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/footsteps.wav"));
		    footsteps = AudioSystem.getClip();
		    footsteps.open(audioInputStream);
		    FloatControl volume= (FloatControl) footsteps.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(-10.0f); 
		}catch (Exception e){e.printStackTrace();}
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/robotic_glitch.wav"));
		    roboticIdentification = AudioSystem.getClip();
		    roboticIdentification.open(audioInputStream);
		    FloatControl volume= (FloatControl) roboticIdentification.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(-3.0f); 
		}catch (Exception e){e.printStackTrace();}
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/robotic_insect_buzz.wav"));
		    roboticNoise = AudioSystem.getClip();
		    roboticNoise.open(audioInputStream);
		    FloatControl volume= (FloatControl) roboticNoise.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(-3.0f); 
		}catch (Exception e){e.printStackTrace();}
		try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("assets/robot_walk.wav"));
		    robotWalk = AudioSystem.getClip();
		    robotWalk.open(audioInputStream);
		    FloatControl volume= (FloatControl) robotWalk.getControl(FloatControl.Type.MASTER_GAIN);
		    volume.setValue(-15.0f); 
		}catch (Exception e){e.printStackTrace();}
	}
	public void stopAll() {
		audio.stop();
		creditsAudio.stop();
		bossAudio.stop();
		portalSound.stop();
		footsteps.stop();
		roboticIdentification.stop();
		robotWalk.stop();
	}
}
