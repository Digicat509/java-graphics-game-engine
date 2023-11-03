package catformer;

import gameEngine.GameObject;

public abstract class Enemy extends DangerThing  implements Entity {
	public Enemy(int x, int y, int dx, int damage)
	{
		super(damage);
		this.x = x;
		this.y = y;
		this.dx = dx;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void push()
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
