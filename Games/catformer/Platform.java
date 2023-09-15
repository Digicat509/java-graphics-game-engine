package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gameEngine.GameObject;

public class Platform extends GameObject {

	public Platform(int x, int y, int w, int h,double theta) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.theta = theta;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.blue);
		g2d.fillRect((int)x, (int)y, w, h);
	}
}
