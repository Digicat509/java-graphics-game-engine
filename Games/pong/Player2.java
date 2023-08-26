package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameEngine.GameObject;

public class Player2 extends GameObject
{
	public Player2()
	{
		super(1);
		w = 5;
		h = 50;
		x = 600;
		y = 210;
		Pong.game.getHandeler().add(this, true);
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,w,h);
		move();
	}
	public void move()
	{
		if(Pong.game.getInput().isKey(KeyEvent.VK_UP) && y > 0)
		{
			y -= 3;
		}
		if(Pong.game.getInput().isKey(KeyEvent.VK_DOWN) && y+h < Pong.game.getHeight())
		{
			y += 3;
		}
	}
}
