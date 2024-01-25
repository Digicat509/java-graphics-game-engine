package gameEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;

public class TextBar extends GameObject {
	String str = "";
	Text text;
	public TextBar(int x, int y)
	{
		this.x = x;
		this.y = y;
		w = 200;
		h = 30;
		layers = 5;
		Handler.addHand.put(this, false);
	}
	public void add(String s)
	{
		if(s.matches("[\\w]"))
			str += s;
		else if(s.matches("\b"))
		{
			if(str.length() > 0)
				str = str.length() > 1 ? str.substring(0, str.length()-1) : "";
		}
	}
	public String enterString()
	{
		String s = str;
		Handler.removeHand.add(text);
		str = "";
		return s;
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect((int)x, (int)y, w, h);
		if(text == null)
			text = new Text(str, new Rectangle((int)x, (int)y, w, h)); 
		else if(str.length() > 0)
			text.setText(str);
	}
}
