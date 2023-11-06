package gameEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class Handler {
	static ArrayList <GameObject> loadingHand = new ArrayList <GameObject>();
	static ArrayList <GameObject> hand = new ArrayList <GameObject>();
	static ArrayList <GameObject> hitsHand = new ArrayList <GameObject>();
	static HashMap <GameObject, Boolean> addHand = new HashMap <GameObject, Boolean>();
	static ArrayList<GameObject> removeHand = new ArrayList<GameObject>();
	private boolean markForClear = false;
	private boolean render = true;
	private long timer = 0;
	private GameEngine ge;
	//Constructor
	public Handler(GameEngine ge) {
		this.ge = ge;
	}
	//adds the game object to the rendering list and to the collision list if canCollide is true
	public void add(GameObject o, boolean canCollide) {
		addHand.put(o, canCollide);
	}
	public void remove(GameObject o) {
		removeHand.add(o);
	}
	public void clear() {
		markForClear = true;
	}
	public void forEach(Consumer<GameObject> c){
		hitsHand.forEach(c);
	}
	public void startRender() {
		render = true;
	}
	public void stopRender() {
		render = false;
	}
	public void stopRender(int time) {
		render = false;
		timer = System.currentTimeMillis() + 1000*time;
	}
	public boolean rendering() {
		return render;
	}
	//renders all game objects in hand
	public void render(Graphics g) {
		if(timer != 0 && System.currentTimeMillis() > timer) {
			render = true;
			timer = 0;
		}
		if(markForClear) {
			hand.clear();
			hitsHand.clear();
			markForClear = false;
		}
		for(GameObject o: removeHand)
		{
			hand.remove(o);
			hitsHand.remove(o);
		}
		removeHand.clear();
		boolean temp = !addHand.isEmpty();
		for(Entry<GameObject, Boolean> e: addHand.entrySet()) {
			hand.add(e.getKey());
			if(e.getValue())
				hitsHand.add(e.getKey());
		}
		if(temp)
			Collections.sort(hand);
		addHand.clear();
		if(render) {
			for(GameObject o: hand) {
				if(o.x+o.w >= -ge.getWidth()/4 && o.y+o.h >= -ge.getHeight()/4 && o.x <= ge.getWidth()*1.25 && o.y <= ge.getHeight()*1.25)
					o.draw(g);
			}
		}
		else
		{
			for(GameObject o: loadingHand) {
				o.draw(g);
			}
		}
	}
}