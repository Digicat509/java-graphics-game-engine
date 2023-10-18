package catformer;

import java.util.ArrayList;

import javax.sound.sampled.Clip;

import catformer.Level.Stage;
import gameEngine.Button;
import gameEngine.GameObject;
import gameEngine.GameEngine.State;

public class LevelPortal extends GameObject{
	
	Stage stage;
	boolean last = false;
	public LevelPortal(int x, int y, Stage s, boolean l){
		super(1, "assets/Portal.png");
		this.x = x;
		this.y = y;
		w = 75*2;
		h = 35*2;
		last = l;
		stage = s;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void updateStage()
	{
		if(last == false)
			Platformer.screen.updateState(State.PLAYING, stage);
		else
			Platformer.screen.updateState(State.CREDITS);
//		System.out.println("LEVEL");
//		Platformer.sound.audio.stop();
//		Platformer.sound.creditsAudio.stop();
//		Platformer.game.getHandeler().clear();
//		Platformer.game.getHandeler().add(Platformer.screen, false);
//		Platformer.start(stage);
//		Platformer.sound.audio.setFramePosition(0);
//		Platformer.sound.audio.loop(Clip.LOOP_CONTINUOUSLY);

	}
	
}
