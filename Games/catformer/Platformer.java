package catformer;

import gameEngine.GameEngine;

public class Platformer {
	static GameEngine game;
	static Level level;
	static Player player;
	public static void main(String[] args)
	{
		Platformer platformer = new Platformer();
	}
	public Platformer()
	{
		game = new GameEngine();
		game.setTitle("Platformer!");
		game.setScale(2f);
		start();
		game.start();
	}
	public static void start()
	{
		level = new Level(Level.Stage.INFINITE);
	}
}
