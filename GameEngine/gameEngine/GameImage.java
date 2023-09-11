package gameEngine;

import java.awt.Graphics;
import java.awt.Image;

public class GameImage extends GameObject {
	public GameImage(int x, int y, String i) {
		super(0, i);
		this.x = x;
		this.y = y;
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, null);
	}
}
