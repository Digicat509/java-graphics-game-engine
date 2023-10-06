package catformer;

public class Hazard extends DangerThing {
	public Hazard(int x, int y, int w, int h, int damage) {
		super(damage);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Platformer.game.getHandeler().add(this, true);
	}
}
