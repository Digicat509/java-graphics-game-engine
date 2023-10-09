package catformer;

public class Enemy extends DangerThing{
	public Enemy(int x, int y, int dx, int damage)
	{
		super(damage);
		this.x = x;
		this.y = y;
		this.dx = dx;
		Platformer.game.getHandeler().add(this, true);
	}
}
