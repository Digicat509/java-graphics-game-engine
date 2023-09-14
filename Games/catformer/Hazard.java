package catformer;

import gameEngine.GameObject;

public class Hazard extends GameObject {
	public Hazard(int x, int y, int w, int h) {
		super(2);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Platformer.game.getHandeler().add(this, true);
	}
}
