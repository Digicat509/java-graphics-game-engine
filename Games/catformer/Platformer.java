package catformer;

import gameEngine.GameEngine;
import gameEngine.Text;

public class Platformer {
	static GameEngine game;
	static Level level;
	static Player player;
	static Text text;
	static int distance;
	public static final float GRAVITY = .5f;
	public static void main(String[] args)
	{
		Platformer platformer = new Platformer();
	}
	public Platformer()
	{
		game = new GameEngine();
		game.setTitle("Platformer!");
		game.setWidth((int)(game.getWidth()*1.5));
		game.setScale(3f);
		start();
		game.start();
	}
	public static void start()
	{
		level = new Level(Level.Stage.LEVEL1);
	}
	public static void updateDistance(int d)
	{
		distance = d;
		if(text != null)
			text.text = ""+distance+"m";
	}
}
