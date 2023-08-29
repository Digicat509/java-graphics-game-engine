package Catformer;

import gameEngine.GameEngine;


public class Catformer {
	static GameEngine game;
	Cat cat;
	
	public static void main(String[] args)
	{
		Catformer game = new Catformer();
		System.out.println("hello");
	}
	
	public Catformer() {
		game = new GameEngine();
		game.setTitle("Insert Title Here");
		game.setScale(2f);
		cat = new Cat();
		game.start();
		
	}
}
