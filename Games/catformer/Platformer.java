package catformer;


import javax.sound.sampled.Clip;

import gameEngine.GameEngine;
import gameEngine.Text;

public class Platformer {
	static GameEngine game;
	static Level level;
	static Player player;
	static Text text;
	static Sound sound;
	static int distance;
	public static final float GRAVITY = .5f;
	private Screen screen;
	
	public static void main(String[] args)
	{
		Platformer platformer = new Platformer();
	}
	public Platformer()
	{
		game = new GameEngine();
		game.setTitle("Catformer!");
		game.setWidth((int)(game.getWidth()*1.5));
		game.setScale(3f);
		game.title();
		screen = new Screen(game);
		
		screen.updateState(GameEngine.state.TITLE);
		//start();
		game.start();
	}
	public static void start()
	{
		//new Credits();
//<<<<<<< HEAD
//		level = new Level(Level.Stage.LEVEL1);
//		game.getHandeler().stopRender(1);
//=======
		if(sound == null)
			sound = new Sound();
		else
		{
			sound.audio.stop();
			sound.audio.setFramePosition(0);
		}
		level = new Level(Level.Stage.LEVEL2);
		game.getHandeler().stopRender(1);
		sound.audio.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public static void updateDistance(int d)
	{
		distance = d;
		if(text != null)
			text.text = ""+distance+"m";
	}
}
