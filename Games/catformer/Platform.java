package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gameEngine.GameObject;

public class Platform extends GameObject {

	public Platform(int x, int y, int w, int h,double theta) {
		super(1);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.theta = theta;
		Platformer.game.getHandeler().add(this, true);
	}
}
