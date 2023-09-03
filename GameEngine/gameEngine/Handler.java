package gameEngine;

import java.awt.Graphics;
import java.util.*;
import java.util.Map.Entry;

public class Handler {
	static PriorityQueue <GameObject> hand = new PriorityQueue <GameObject>();
	static ArrayList <GameObject> hitsHand = new ArrayList <GameObject>();
	static HashMap <GameObject, Boolean> addHand = new HashMap <GameObject, Boolean>();
	private boolean markForClear = false;
	//Constructor
	public Handler() {
		
	}
	//adds the game object to the rendering list and to the collision list if canCollide is true
	public void add(GameObject o, boolean canCollide) {
		addHand.put(o, canCollide);
	}
	public void remove(GameObject o) {
		hand.remove(o);
	}
	public void clear() {
		markForClear = true;
	}
	//renders all game objects in hand
	public void render(Graphics g) {
		if(markForClear) {
			hand.clear();
			hitsHand.clear();
			markForClear = false;
		}
		for(Entry<GameObject, Boolean> e: addHand.entrySet()) {
			hand.add(e.getKey());
			if(e.getValue())
				hitsHand.add(e.getKey());
		}
		addHand.clear();
		for(GameObject o: hand) {
			o.draw(g);
		}
	}
}