package gameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class GameImage extends GameObject {
	double scale;
	public GameImage(URL i, int x, int y, double scale) {
		super(i);
		this.x = x;
		this.y = y;
		this.w = (int)(image.getWidth(null)*scale);
		this.h = (int)(image.getHeight(null)*scale);
		this.scale = scale;
		Handler.addHand.put(this, false);
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, (int)(image.getWidth(null)*scale), (int)(image.getHeight(null)*scale), null);
	}
}
