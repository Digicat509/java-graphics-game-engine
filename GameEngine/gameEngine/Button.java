package gameEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;

public class Button extends GameObject{
	
	Text text;
	String val;
	RectangularHitbox clickBox;
	Runnable action;
	Color color;
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
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Button(int x, int y, int w, int h, Runnable m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		action = m;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Button(int x, int y, int w, int h, URL image, Runnable m) {
		super(image);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		action = m;
		Handler.addHand.put(this, false);
	}
	
	public Button(String v, int x, int y, int w, int h, Color color, Runnable m) {
		val = v;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		text = new Text(val, new Rectangle(x, y, w, h), 20, color);
		action = m;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Button(int x, int y, int w, int h, Color color, Runnable m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		action = m;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Button(String v, int x, int y, int w, int h, Color color, URL image, Runnable m) {
		super(image);
		val = v;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		text = new Text(val, new Rectangle(x, y, w, h), 20, color);
		action = m;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Button(String v, int x, int y, int w, int h, String font, Runnable m) {
		val = v;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		text = new Text(val, new Rectangle(x, y, w, h), 20, font);
		action = m;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Button(String v, int x, int y, int w, int h, String font, Color color, URL image, Runnable m) {
		super(image);
		val = v;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		clickBox = new RectangularHitbox((int)x, (int)y, w, h);
		text = new Text(val, new Rectangle(x, y, w, h), 20, font, color);
		action = m;
		this.color = color;
		Handler.addHand.put(this, false);
	}

	@Override
	public void draw(Graphics g) {
		if(getImage() == null)
		{
			g.setColor(color);
			g.drawRect((int)x, (int)y, w, h);
		}
		else
		{
			g.drawImage(getImage(), (int)x, (int)y, w, h, null);
		}
	}
	
	public void clickBox(int mx, int my) {
		if(clickBox.contains(mx, my) && System.currentTimeMillis() > timer) {
			action.run();
			timer = System.currentTimeMillis() + 300;
		}
	}
}
