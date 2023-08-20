package pong;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class Ball extends GameObject{
	static Image Ball;
	public Ball(float dx, float dy)
	{
		super(1);
		try{Ball=ImageIO.read(getClass().getResource("ball.png"));} catch(Exception e){}
		x=305;
		y=210;
		w=10;
		h=10;
		this.dx=dx;
		this.dy=dy;
		int rand = (int)(Math.random()*4);
		if(rand == 1)
			dx *= -1f;
		if(rand == 2)
			dy *= -1f;
		if(rand == 3)
		{
			dx *= -1f;
			dy *= -1f;
		}
		Pong.game.getHandeler().add(this, true);
	}
	public void draw(Graphics g)
	{
		g.drawImage(Ball, (int)x, (int)y, (int)w, (int)h, null);
		move();
	}
	public void move()
	{
		if(x > Pong.game.getWidth()-w)
		{ 
			x = 305;
			y = 210;
			dx = .5f;
			dy = .5f;
			int rand = (int)(Math.random()*4);
			if(rand == 1)
				dx *= -1f;
			if(rand == 2)
				dy *= -1f;
			if(rand == 3)
			{
				dx *= -1f;
				dy *= -1f;
			}
		}
		if(x < 0)
		{
			x = 305;
			y = 210;
			dx = .5f;
			dy = .5f;
			int rand = (int)(Math.random()*4);
			if(rand == 1)
				dx *= -1f;
			if(rand == 2)
				dy *= -1f;
			if(rand == 3)
			{
				dx *= -1f;
				dy *= -1f;
			}
		}
		if(x > Pong.game.getWidth()-w || x < 0)
			dx *= -1;
		if( y > Pong.game.getHeight()-35-h || y < 0)
			dy *= -1;
		collide();
	}
	public boolean collide()
	{
		x+=dx;
		if(this.hitsAny())
		{
			x -= dx;
			dx *= -1;
			if(dx > 0)
				dx += .25;
			else
				dx -= .25;
			if(dy > 0)
				dy += .25;
			else
				dy -= .25;
			return true;
		}
		x -= dx;
		y += dy;
		if(this.hitsAny())
		{
			y -= dy;
			dy *= -1;
			if(dx > 0)
				dx += .25;
			else
				dx -= .25;
			if(dy > 0)
				dy += .25;
			else
				dy -= .25;
			return true;
		}
		x+=dx;
		return false;
	}
}
