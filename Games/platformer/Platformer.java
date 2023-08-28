package platformer;

import gameEngine.GameEngine;

public class Platformer {
	static GameEngine game;
	public static void main(String[] args)
	{
		Platformer platformer = new Platformer();
	}
	public Platformer()
	{
		game = new GameEngine();
		game.setTitle("Platformer!");
		game.setScale(2f);
		new Background();
		game.start();
	}
}
