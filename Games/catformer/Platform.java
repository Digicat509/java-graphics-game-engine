package catformer;

import gameEngine.GameObject;

public class Platform extends GameObject {

	public Platform(int x, int y, int w, int h,double theta) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rotation = theta;
		Platformer.game.getHandeler().add(this, true);
	}
}
