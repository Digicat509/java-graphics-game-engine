package platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameEngine.GameObject;

public class Player extends GameObject{
	public Player(int x, int y)
	{
		super(2);
		this.x = x;
		this.y = y;
		w = 20;
		h = 20;
		dx = 0;
		dy = 0;
		Platformer.game.getHandeler().add(this, true);
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(new Color(213, 250, 88));
		g.fillRect((int)x, (int)y, w, h);
		move();
	}
	public void move()
	{
		dx = 0;
		if(Platformer.game.getInput().isKey(KeyEvent.VK_W))
		{
			dy = -5;
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_D))
		{
			dx += 1;
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_A))
		{
			dx -= 1;
		}
		y += dy;
		x += dx;
		if(!this.hits())
		{
			dy += .5;
		}
	}
}
