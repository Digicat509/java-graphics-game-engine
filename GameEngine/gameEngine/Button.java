package gameEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.function.Consumer;

public class Button extends GameObject{
	
	Text text;
	String val;
	RectangularHitbox clickBox;
	Runnable action;
	
	public Button(String v, int x, int y, int w, int h, Runnable m) {
		val = v;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		text = new Text(val, new Rectangle(x, y, w, h), 20);
		action = m;
		Handler.addHand.put(this, false);
		System.out.println(x+" "+y);
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("Rect");
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, w, h);
	}
	
	public void clickBox(int mx, int my) {
		if(clickBox.contains(mx, my)) {
			System.out.println("TRUE");
			action.run();
		}
	}
}
