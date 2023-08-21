package game2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Block extends GameObject{
	private int green, fontSize = 40, xShift = 40;
	int value, row, col;
	boolean valueSizeChanged = false;
	public Block(int r, int c)
	{
		this.row = r;
		this.col = c;
		int rand = (int)(Math.random()*2);
		if(rand == 0)
		{
			value = 2;
		}
		else
		{
			value = 4;
		}
		w = 112;
		h = 112;
		Game2048.game.getHandeler().add(this, false);
	}
	public void draw(Graphics g)
	{
		x = 120*row+2;
		y = 120*col+2;
		green = (int)(222/(Math.pow(value, 0.25)));
		g.setColor(new Color(255, green, 68));
		g.fillRect((int)x, (int)y, w, h);
		g.setColor(Color.black);
		if(valueSizeChanged)
		{
			boolean canKeepGoing = true;
			int modValue = 10;
			fontSize = 40;
			xShift = 40;
			while(canKeepGoing)
			{
				int temp = value/modValue;
				if(temp != 0)
				{
					//fontSize -= 5;
					xShift -= 10;
					modValue *= 10;
				}
				else
				{
					canKeepGoing = false;
				}
			}
			valueSizeChanged = false;
		}
		g.setFont(new Font("Courier New", 0, fontSize));
		g.drawString(""+value, (int)x+xShift, (int)y+68);
	}
}