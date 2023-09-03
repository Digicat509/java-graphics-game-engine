package gameEngine;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Text extends GameObject{
	Rectangle rect;
	String text;
	int textSize;
	String font;
	public Text(String s, int x, int y)
	{
		text = s;
		this.x = x;
		this.y = y;
		Handler.addHand.put(this, false);
	}
	public Text(String s, int x, int y, int textSize)
	{
		text = s;
		this.x = x;
		this.y = y;
		this.textSize = textSize;
		Handler.addHand.put(this, false);
	}
	public Text(String s, int x, int y, int textSize, String font)
	{
		text = s;
		this.x = x;
		this.y = y;
		this.textSize = textSize;
		this.font = font;
		Handler.addHand.put(this, false);
	}
	public Text(String s, Rectangle rect)
	{
		text = s;
		this.rect = rect;
		Handler.addHand.put(this, false);
	}
	public Text(String s, Rectangle rect, int textSize)
	{
		text = s;
		this.rect = rect;
		this.textSize = textSize;
		Handler.addHand.put(this, false);
	}
	public Text(String s, Rectangle rect, int textSize, String font)
	{
		text = s;
		this.rect = rect;
		this.textSize = textSize;
		this.font = font;
		Handler.addHand.put(this, false);
	}
	@Override
	public void draw(Graphics g)
	{
		if(font != null)
			g.setFont(new Font(font, 0, g.getFont().getSize()));
		if(textSize > 0)
			g.setFont(new Font(g.getFont().getFontName(), 0, textSize));
		FontMetrics metrics = g.getFontMetrics();
		if(rect != null){
			int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
			int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		}
		else
		{
			x = x - metrics.stringWidth(text) / 2;
			y = y - metrics.getHeight() / 2 + metrics.getAscent();
		}
		g.drawString(text, (int)x, (int)y);
	}
}
