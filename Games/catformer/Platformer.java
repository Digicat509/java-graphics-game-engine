package catformer;

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
		new Player(0, 0);
		new Building(0, 200, 50);
		new Pipe(40, 300, 50);
		new Building(90, 250, 50);
		new Spikes(200, 200, 50, 20);
		game.start();
	}
}
