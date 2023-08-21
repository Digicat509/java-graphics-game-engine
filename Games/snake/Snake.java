package snake;

import gameEngine.GameEngine;

public class Snake {
	static GameEngine game;
	static Player player;
	public static void main(String[] args)
	{
		Snake snake = new Snake();
	}
	public Snake()
	{
		game = new GameEngine();
		game.setTitle("Snake!");
		game.setScale(2f);
		new Background();
		player = new Player();
		new Apple();
		game.start();
	}
}
