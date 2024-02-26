package gameEngine;

import java.awt.Graphics;

public class Area extends GameObject {
	private boolean display; 
	public Area(int x, int y, int w, int h, boolean display) {
		super(Integer.MAX_VALUE);//Arbitrary High Number
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.display = display;
		Handler.addHand.put(this, false);
	}
	@Override
	public void draw(Graphics g) {
		if(display)
			g.fillRect((int)x, (int)y, w, h);
	}
}
