package gameEngine;

import java.awt.Graphics;
import java.awt.Image;

public class GameImage extends GameObject {
	private Image image;
	public GameImage(int x, int y, Image i) {
		this.x = x;
		this.y = y;
		image = i;
		Handler.addHand.put(this, false);
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, null);
	}
}
