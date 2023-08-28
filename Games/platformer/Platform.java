package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameEngine.GameObject;

public class Platform extends GameObject {

	public Platform(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, w, h);
	}
}
