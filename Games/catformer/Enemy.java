package catformer;

import java.awt.event.KeyEvent;
import java.util.Objects;

import gameEngine.GameObject;
import gameEngine.Timer;

public abstract class Enemy extends DangerThing  implements Entity {
	
	protected boolean disabled;
	
	public Enemy(int x, int y, int dx, int damage, boolean disabled)
	{
		super(damage);
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.disabled = disabled;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void push()
	{
		if(!(this instanceof Projectile))
		{
			GameObject o = this.hits();
			if(o != null)
			{
				if(o instanceof Player)
				{
					int move;
					if(dx > 0 && o.x < x+w)
						move = (int)(x+w-o.x);
					else if(dx < 0 && o.x+o.w > x)
						move = (int)(x-o.x-o.w);
					else
						move = 0;
					((Player)o).updatePosition(1, move);
					((Player)o).damage(this);
					GameObject temp = o.hits();
					if(temp instanceof Platform) {
						if(dx < 0 && o.x < x+w)
							move = (int)(temp.x+temp.w-o.x);
						else if(dx > 0 && o.x+o.w > temp.x)
							move = (int)(temp.x-o.x-o.w);
						((Player)o).updatePosition(1, move);
					}
				}
				else if(o instanceof Entity)
				{
					if(dx > 0)
						o.x = x+w;
					else if(dx < 0)
						o.x= x-o.w;
					GameObject temp = o.hits();
					if(temp instanceof Platform) {
						if(dx < 0)
							o.x = temp.x+temp.w;
						else if(dx > 0)
							o.x= temp.x-o.w;
					}
				}
			}
		}
	}
	
	@Override
	public void move() {
		GameObject o = this.hits();
		collisionEffects(o);
		if(o instanceof Entity && !(o instanceof Projectile))
		{
			if(dx > 0)
				updatePosition(1, (int)(o.x-x-w));
			else if(dx < 0)
				updatePosition(1, (int)(o.x+o.w-x));
//			dx = 0;
		}
		else if(o instanceof Platform)
		{
			float temp = dx;
//			dx = 0;
			if(temp > 0 && o.x <= x+w)
				updatePosition(1, (int)(o.x-x-w));
			else if(temp < 0 && x <= o.x+o.w)
				updatePosition(1, (int)(o.x+o.w-x));
		}
	}
	
	public void collisionEffects(GameObject o)
	{
		if(o instanceof DangerThing)
		{
			if(o instanceof Net)
				Platformer.game.getHandeler().remove(o);
		}
		
		if(o instanceof Portal)
		{
			int move = (int)(((Portal)o).oPortal.x+((Portal)o).oPortal.w/2-this.w/2)-(int)x;
			this.y = (((Portal)o).oPortal.y-this.h);
			dy = -dy;
			y += dy;
			updatePositionFinal(1, move);
		}
	}
	
	protected void updatePosition(int direction)
	{
		x += direction*dx;
	}
	
	protected void updatePosition(int direction, int amount)
	{
		x += direction*amount;		
	}
	
	protected void updatePositionFinal(int direction)
	{
		x += direction*dx;
		GameObject o = this.hits();
		if(o != null && (o instanceof Platform || o instanceof Box))
		{
			double changeX;
			if(dx < 0)
				changeX = (o.x+o.w-x);
			else if(dx > 0)
				changeX = (o.x-x-w);
			else 
				changeX = 0;
			x += changeX;
		}
	}
	
	protected void updatePositionFinal(int direction, int amount)
	{
		x += direction*amount;
		GameObject o = this.hits();
		if(o != null && (o instanceof Platform || o instanceof Box))
		{
			double changeX;
			if(direction*amount < 0)
				changeX = (o.x+o.w-x);
			else if(direction*amount > 0)
				changeX = (o.x-x-w);
			else 
				changeX = 0;
			x += changeX;
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Enemy ? x == ((Enemy)obj).x && y == ((Enemy)obj).y: super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
