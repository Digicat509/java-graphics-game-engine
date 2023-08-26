package gameEngine;

import java.awt.*;

public abstract class GameObject implements Comparable<GameObject>
{
	public float x,y,dx,dy;
	public int w,h;
	protected int layers;
	public GameObject()
	{
		this(1);
	}
	public GameObject(int layers)
	{
		this.layers = layers;
	}
	public int compareTo(GameObject other)
	{
		return (this.layers-other.layers);
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
			if(!this.equals(o))
			{
				if((this.getBounds()).intersects((Rectangle)o.getBounds()))
				{
					return true;
				}
			}
		}
		return false;
	}
	public boolean hits()
	{
		for(GameObject o: Handler.hitsHand)
		{
			if(this.layers > 0);
				if(!this.equals(o))
					if(layers >= o.layers)
						if((this.getBounds()).intersects((Rectangle)o.getBounds()))
							return true;
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
