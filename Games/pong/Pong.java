package pong;

import gameEngine.GameEngine;

public class Pong {
	static GameEngine game;
	static int p1Score, p2Score;
	Ball ball;
	Player1 p1;
	Player2 p2;
	public static void main(String[] args)
	{
		Pong pong = new Pong();
	}
	public Pong()
	{
		game = new GameEngine();
		game.setTitle("Pong!");
		game.setScale(2f);
		new Background();
		ball = new Ball(1, 1);
		p1 = new Player1();
		p2 = new Player2();
		game.start();
	}
}
