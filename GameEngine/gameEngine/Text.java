package gameEngine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Text extends GameObject{
	Rectangle rect;
	public String text;
	int textSize;
	String font;
	Color color;
	boolean centered;
	
	public Text(String s, int x, int y) {
		text = s;
		this.x = x;
		this.y = y;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, int x, int y, int textSize) {
		text = s;
		this.x = x;
		this.y = y;
		this.textSize = textSize;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, int x, int y, int textSize, String font) {
		text = s;
		this.x = x;
		this.y = y;
		this.textSize = textSize;
		this.font = font;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect) {
		text = s;
		this.rect = rect;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect, int textSize) {
		text = s;
		this.rect = rect;
		this.textSize = textSize;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect, int textSize, String font) {
		text = s;
		this.rect = rect;
		this.textSize = textSize;
		this.font = font;
		this.color = Color.white;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, int x, int y, Color color) {
		text = s;
		this.x = x;
		this.y = y;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, int x, int y, int textSize, Color color) {
		text = s;
		this.x = x;
		this.y = y;
		this.textSize = textSize;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, int x, int y, int textSize, String font, Color color) {
		text = s;
		this.x = x;
		this.y = y;
		this.textSize = textSize;
		this.font = font;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect, Color color) {
		text = s;
		this.rect = rect;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect, int textSize, Color color) {
		text = s;
		this.rect = rect;
		this.textSize = textSize;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect, int textSize, String font, Color color) {
		text = s;
		this.rect = rect;
		this.textSize = textSize;
		this.font = font;
		this.color = color;
		Handler.addHand.put(this, false);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		if(font != null)
			g.setFont(new Font(font, 0, g.getFont().getSize()));
		if(textSize > 0)
			g.setFont(new Font(g.getFont().getFontName(), 0, textSize));
		if(!centered)
		{
			FontMetrics metrics = g.getFontMetrics();
			if(rect != null) {
				x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
				y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
			}
			else {
				x = x - metrics.stringWidth(text) / 2;
				y = y - metrics.getHeight() / 2 + metrics.getAscent();
			}
			centered = true;
		}
		g.drawString(text, (int)x, (int)y);
	}
}
