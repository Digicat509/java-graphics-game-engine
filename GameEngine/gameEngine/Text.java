package gameEngine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Text extends GameObject{
	Rectangle rect;
	private String text;
	int textSize;
	String font;
	Color color;
	boolean centered;
	int trueX, trueY;
	
	public Text(String s, int x, int y) {
		text = s;
		this.x = x;
		this.y = y;
		this.trueX = x;
		this.trueY = y;
		this.color = Color.white;
		textSize = 20;
		layers = 6;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, int x, int y, int textSize) {
		this(s, x, y);
		this.textSize = textSize;
	}
	
	public Text(String s, int x, int y, int textSize, String font) {
		this(s, x, y, textSize);
		this.font = font;
	}
	
	public Text(String s, Rectangle rect) {
		text = s;
		this.rect = rect;
		this.color = Color.white;
		textSize = 20;
		layers = 6;
		Handler.addHand.put(this, false);
	}
	
	public Text(String s, Rectangle rect, int textSize) {
		this(s, rect);
		this.textSize = textSize;
	}
	
	public Text(String s, Rectangle rect, int textSize, String font) {
		this(s, rect, textSize);
		this.font = font;
	}
	
	public Text(String s, int x, int y, Color color) {
		this(s, x, y);
		this.color = color;
	}
	
	public Text(String s, int x, int y, int textSize, Color color) {
		this(s, x, y, textSize);
		this.color = color;
	}
	
	public Text(String s, int x, int y, int textSize, String font, Color color) {
		this(s, x, y, textSize, font);
		this.color = color;
	}
	
	public Text(String s, Rectangle rect, Color color) {
		this(s, rect);
		this.color = color;
	}
	
	public Text(String s, Rectangle rect, int textSize, Color color) {
		this(s, rect, textSize);
		this.color = color;
	}
	
	public Text(String s, Rectangle rect, int textSize, String font, Color color) {
		this(s, rect, textSize, font);
		this.color = color;
	}
	public void setText(String text) {
		this.text = text;
		centered = false;
	}
	public String getText() {
		return text;
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
				x = trueX - metrics.stringWidth(text) / 2;
				y = trueY - metrics.getHeight() / 2 + metrics.getAscent();
			}
			centered = true;
		}
		g.drawString(text, (int)x, (int)y);
	}
}
