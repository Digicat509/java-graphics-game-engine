package catformer;

import java.util.Objects;

import gameEngine.GameObject;

public abstract class Enemy extends DangerThing  implements Entity {
	
	protected boolean disabled = false;
	
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
				}
				else if(o instanceof Entity)
				{
					if(dx > 0)
						o.x = x+w;
					else if(dx < 0)
						o.x= x-o.w;
				}
			}
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
