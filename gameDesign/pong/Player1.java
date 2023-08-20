package gameDesign.pong;

import java.awt.Color;
import java.awt.Graphics;

import gameDesign.gameEngine.GameObject;

public class Player1 extends GameObject
{
	public Player1()
	{
		super(1);
		w = 5;
		h = 50;
		x = 30;
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
		if(Pong.game.getKeyboard().w && y > 0)
		{
			y -= 1;
		}
		if(Pong.game.getKeyboard().s && y+h < Pong.game.getHeight()-40)
		{
			y += 1;
		}
	}
}