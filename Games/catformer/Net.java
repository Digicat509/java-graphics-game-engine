package catformer;

import java.awt.Color;
import java.awt.Graphics;

public class Net extends Enemy {

	public Net(int x, int y, int dx, float dy) {
		super(x, y, dx);
		this.dy = dy;
		w = 10;
		h = 10;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect((int)x, (int)y, w, h);
		move();
	}
	@Override
	public void move() {
		super.move();
		dy += Platformer.GRAVITY;
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}
}
