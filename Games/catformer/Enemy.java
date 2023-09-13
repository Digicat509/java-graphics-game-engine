package catformer;

import gameEngine.GameObject;

public class Enemy extends GameObject{
	public Enemy(int x, int y, int dx)
	{
		super(2);
		this.x = x;
		this.y = y;
		this.dx = dx;
		Platformer.game.getHandeler().add(this, true);
	}
}
