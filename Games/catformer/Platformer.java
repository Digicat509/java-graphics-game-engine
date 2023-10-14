package catformer;


import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import catformer.Level.Stage;
import gameEngine.GameEngine;
import gameEngine.GameEngine.State;
import gameEngine.Text;

public class Platformer {
	static GameEngine game;
	static Level level;
	static Player player;
	static Text text;
	static Sound sound;
	static int distance;
	public static final float GRAVITY = .5f;
	static Screen screen;
	static int levelNum = 0;
	
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
		sound = new Sound();
		screen = new Screen();
		screen.updateState(State.TITLE);
		game.start();
	}
	public static void start(Stage stage)
	{
		game.getHandeler().clear();
		if(stage != null)
			level = new Level(stage);
		else
		{
			level = new Level(Stage.get(levelNum));
		}
		game.getHandeler().stopRender(1);
	}
	public static void updateDistance(int d)
	{
		distance = d;
		if(text != null)
			text.text = ""+distance+"m";
	}
}
