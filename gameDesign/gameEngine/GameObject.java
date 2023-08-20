package gameEngine;

import java.awt.*;

public abstract class GameObject
{
	protected float x,y,dx,dy;
	protected int w,h;
	protected int layers;
	public GameObject(int layers)
	{
		this.layers = layers;
	}
	public boolean hits(GameObject other)
	{
		if((this.getBounds()).intersects((Rectangle)other.getBounds()))
		{
			return true;
		}
		return false;
	}
	public boolean hitsAny()
	{
		for(GameObject o: Handler.hitsHand)
		{
			if((this.getBounds()).intersects((Rectangle)o.getBounds()))
			{
				return true;
			}
		}
		return false;
	}
	public boolean hits()
	{
		for(GameObject o: Handler.hitsHand)
		{
			if(this.layers > 0);
			{
				if(layers >= o.layers)
						if((this.getBounds()).intersects((Rectangle)o.getBounds()))
							return true;
			}
		}
		return false;
	}
	public Shape getBounds()
	{
		return new Rectangle((int)x, (int)y, w, h);
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,w,h);
		move();
	}
	public void move()
	{
		x += dx;
		y += dy;
	}
}
