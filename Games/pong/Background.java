package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Background extends GameObject{
	public Background()
	{
		super(0);
		Pong.game.getHandeler().add(this, false);
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(314, 0, 3, Pong.game.getHeight());
		g.setFont(new Font("Courier New", 0, 30));
		g.drawString(""+Pong.p1Score, 275, 35);
		g.drawString(""+Pong.p2Score, 335, 35);
	}
}
