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
	public GameImage(URL i, int x, int y) {
		super(i);
		this.x = x;
		this.y = y;
		this.w = (int)(image.getWidth(null));
		this.h = (int)(image.getHeight(null));
		this.scale = 1;
		Handler.addHand.put(this, false);
	}
	public GameImage(URL i, int x, int y, int w, int h) {
		super(i);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Handler.addHand.put(this, false);
	}
	public GameImage(int layer, URL i, int x, int y, double scale) {
		super(layer, i);
		this.x = x;
		this.y = y;
		this.w = (int)(image.getWidth(null)*scale);
		this.h = (int)(image.getHeight(null)*scale);
		this.scale = scale;
		Handler.addHand.put(this, false);
	}
	public GameImage(int layer, URL i, int x, int y) {
		super(layer, i);
		this.x = x;
		this.y = y;
		this.w = (int)(image.getWidth(null));
		this.h = (int)(image.getHeight(null));
		this.scale = 1;
		Handler.addHand.put(this, false);
	}
	public GameImage(int layer, URL i, int x, int y, int w, int h) {
		super(layer, i);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Handler.addHand.put(this, false);
	}
}
