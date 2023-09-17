package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class AnimalControlEnemy extends Enemy {

	private long delay = 0;
	private static final int RANGE = 400;
	
	public AnimalControlEnemy(int x, int y) {
		super(x, y, 0);
		this.w = 20;
		this.h = 45;
		delay = System.currentTimeMillis()+2000;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, w, h);
		move();
	}
	@Override
	public void move() {
		float yVel = 0;
		int xDist = 0;
		int yDist = 0;
		if(delay - 200 < System.currentTimeMillis())
		{
			xDist = (int)(Platformer.player.x-x);
			yDist = (int)(Platformer.player.y-y);
			double framesTillImpact = (double)Math.abs(xDist)/5;
			yVel = (float)((yDist-.5*.5*Math.pow(framesTillImpact, 2))/framesTillImpact);
		}
		if(Platformer.player.x < x-Platformer.player.w && delay < System.currentTimeMillis() && Math.abs(xDist) < RANGE)
		{
			new Net((int)(x-10), (int)(y+10), -5, yVel);
			delay = System.currentTimeMillis()+1000;
		}
		if(Platformer.player.x > x+w && delay < System.currentTimeMillis() && Math.abs(xDist) < 35+w)
		{
			new Net((int)(x-10), (int)(y+10), 5, yVel);
			delay = System.currentTimeMillis()+1000;
		}
		GameObject o = this.hits();
		if(o == null)
			dy += Platformer.GRAVITY;
		else
		{
			dy = 0;
			y = o.y-h+1;
		}
		y += dy;
	}
}
