package gameDesign.gameEngine;

import java.awt.Graphics;
import java.util.*;

public class Handler{
	static ArrayList <GameObject> hand = new ArrayList <GameObject>();
	static ArrayList <GameObject> hitsHand = new ArrayList <GameObject>();
	//Constructor
	public Handler()
	{
		
	}
	//adds the game object to the rendering list and to the collision list if canCollide is true
	public void add(GameObject o, boolean canCollide)
	{
		hand.add(o);
		if(canCollide)
			hitsHand.add(o);
	}
	public void remove(GameObject o)
	{
		hand.remove(o);
	}
	//renders all game objects in hand
	public void render(Graphics g)
	{
		for(GameObject o: Handler.hand)
		{
			o.draw(g);
		}
	}
}