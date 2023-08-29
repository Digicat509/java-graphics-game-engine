package Catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameEngine.Input;
import gameEngine.GameObject;

public class Cat extends GameObject{

	double gravity;
	public Cat() {
		super(1);
		x= 100;
		y=100;
		w=10;
		h=10;
		dy=10;
		gravity = 2;
		
		Catformer.game.getHandeler().add(this, true);
	}
	public void move() {
		if(Catformer.game.getInput().isKey(KeyEvent.VK_W)) {
			y-=dy;
			if(dy>0) {
				dy--;
			}
//			if (dy == 0) {
//				dy=10;
//			}
		}
		//else {
			y+=gravity;
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,w,h);
		move();
	}
}
