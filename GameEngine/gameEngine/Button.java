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
	private long timer = 0;
	
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
	}
	
	public Button(int x, int y, int w, int h, Runnable m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		action = m;
		Handler.addHand.put(this, false);
	}
	
	public Button(int x, int y, int w, int h, String image, Runnable m) {
		super(image);
		System.out.println(image);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		action = m;
		Handler.addHand.put(this, false);
	}

	@Override
	public void draw(Graphics g) {
		if(image == null)
		{
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, w, h);
		}
		else
		{
			g.drawImage(image, (int)x, (int)y, w, h, null);
		}
	}
	
	public void clickBox(int mx, int my) {
		if(clickBox.contains(mx, my) && System.currentTimeMillis() > timer) {
			action.run();
			timer = System.currentTimeMillis() + 200;
		}
	}
}
