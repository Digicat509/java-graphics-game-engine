package pong;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;
import gameEngine.Keyboard;

public class Player2 extends GameObject
{
	public Player2()
	{
		super(1);
		w = 5;
		h = 50;
		x = 575;
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
		if(Keyboard.up && y > 0)
		{
			y -= 1;
		}
		if(Keyboard.down && y+h < Pong.game.getHeight()-40)
		{
			y += 1;
		}
	}
}
