package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class TutorialBoss extends Enemy {

	private long delay = 0;
	private static final int RANGE = 400;
	public static int speed = 3;
	
	public TutorialBoss(int x, int y) {
		super(x, y, speed, 20);
		w = 30;
		h = 30;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.magenta);
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
			new GrannyCandy((int)(x-10), (int)(y+10), -5, yVel);
			delay = System.currentTimeMillis()+1000;
		}
		if(Platformer.player.x > x+w && delay < System.currentTimeMillis() && Math.abs(xDist) < 35+w)
		{
			new GrannyCandy((int)(x-10), (int)(y+10), 5, yVel);
			delay = System.currentTimeMillis()+1000;
		}
		y+=dy;
		GameObject o = this.hits();
		
		if(o != null && !(o instanceof Enemy))
		{
			x+= dx;
			if(x < o.x) {
				x = o.x;
				dx*=-1;
			}
			else if((x+w) > (o.x+o.w)){
				x = o.x+o.w-w;
				dx*=-1;
			}
			dy = 0;
			if(o.y>= this.y) {
				y = o.y-h;
			}
		}
		else if(o == null)
		{
			dy += Platformer.GRAVITY;
		}
	}
}
