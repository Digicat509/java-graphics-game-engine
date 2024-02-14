package catformer;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class AnimalControlEnemy extends Enemy {

	private long delay = 0;
	private static final int RANGE = 400;
	
	public AnimalControlEnemy(int x, int y, boolean disabled) {
		super(x, y, 0, 20, disabled);
		this.w = 20;
		this.h = 45;
		try {
			this.setImage(ImageIO.read(getClass().getResource("assets/AnimalControl.png")));
		} catch (Exception e) {e.printStackTrace();}
		delay = System.currentTimeMillis()+3000;
	}
	@Override
	public void draw(Graphics g) {
		if(Platformer.player != null && Platformer.player.x < x+w)
			g.drawImage(getImage(), (int)x, (int)y, w, h-1, null);
		else
			g.drawImage(getImage(), (int)x+w, (int)y, -w, h-1, null);
		if(!disabled)
			move();
	}
	@Override
	public void move() {
		super.move();
		float yVel = 0;
		int xDist = 0;
		int yDist = 0;
		if(Platformer.player != null && delay - 200 < System.currentTimeMillis())
		{
			xDist = (int)(Platformer.player.x+Platformer.player.w/2-x);
			yDist = (int)(Platformer.player.y+Platformer.player.h/2-y);
			double framesTillImpact = (double)Math.abs(xDist)/5;
			yVel = (float)((yDist-.5*.5*Math.pow(framesTillImpact, 2))/framesTillImpact);
		}
		if(Platformer.player != null && Platformer.player.x < x-Platformer.player.w && delay < System.currentTimeMillis() && Math.abs(xDist) < RANGE)
		{
			new Net((int)(x-10), (int)(y+10), -5, yVel);
			delay = System.currentTimeMillis()+1000;
		}
		if(Platformer.player != null && Platformer.player.x > x+w && delay < System.currentTimeMillis() && Math.abs(xDist) < Platformer.player.scrollDistance+35+w)
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
