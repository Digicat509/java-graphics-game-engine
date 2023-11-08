package catformer;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class Net extends Enemy implements Projectile{

	public Net(int x, int y, int dx, float dy) {
		super(x, y, dx, 10);
		this.dy = dy;
		w = 10;
		h = 10;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/Net.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void move() {
		super.move();
		dy += Platformer.GRAVITY;
		GameObject o = this.hits();
		if(o instanceof Platform)
			Platformer.game.getHandeler().remove(this);
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}
}
