package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class DumbDroneEnemy extends Enemy {
	
	private float gravity = 0.5f;
	private boolean onGround = false;
	
	public DumbDroneEnemy(int x, int y) {
		super(x, y, 0);
		w = 15;
		h = 15;
	}
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect((int)x, (int)y, w, h);
		move();
	}
	@Override
	public void move() {
		y+=dy;
		
		GameObject o = this.hits();
		if(o != null)
		{
			onGround = true;
			if(Platformer.player.x < this.x && Platformer.player.x > this.x-100) {
				dx = -4;
			}
			dy = 0;
			if(o.y>= this.y) {
				y = o.y-h;
			}
		}
		else
		{
			onGround = false;
			dy += gravity;
		}
		x += dx;
		o = this.hits();
		if(!onGround && o != null)
			x = o.x+o.w;
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}
}
