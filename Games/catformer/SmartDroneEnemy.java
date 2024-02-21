package catformer;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class SmartDroneEnemy extends Enemy {
	
	private float gravity = 0.5f;
	private static final int RANGE = 200;
	private boolean facing = true;
	
	public SmartDroneEnemy(int x, int y, boolean disabled) {
		super(x, y, 3, 20, disabled);
		w = 28;
		h = 15;
		try {
			this.setImage(ImageIO.read(getClass().getResource("assets/Beetle.png")));
		} catch (Exception e) {e.printStackTrace();}
	}
	public void draw(Graphics g) {
		//g.setColor(Color.pink);
		//g.fillRect((int)x, (int)y, w, h);
		if(facing)
			g.drawImage(getImage(),(int)x, (int)y, w, h, null);
		else 
			g.drawImage(getImage(),(int)x+w, (int)y, -w, h, null);
		if(!disabled)
			move();
	}
	@Override
	public void move() {
		y+=dy;
		GameObject o = this.hits();
		super.collisionEffects(o);
		
		if(o != null && !(o instanceof Portal))
		{
			if(Platformer.player.x < this.x && Platformer.player.x > this.x-RANGE) {
				dx = -3;
				facing = true;
			}
			else if(Platformer.player.x > this.x && Platformer.player.x < this.x+this.w+RANGE) {
				dx = 3;
				facing = false;
			}
			else {
				dx = 0;
			}
			if(o instanceof Platform)
			{
				if(x < o.x) {
					x = o.x;
				}
				else if((x+w) > (o.x+o.w)){
					x = o.x+o.w-w;
				}
				dy = 0;
				if(o.y>= this.y) {
					y = o.y-h;
				}
			}
			x += dx;
		}
		else
		{
			dy += gravity;
		}
		
		if(dy>15)
			dy=15;
		if(dy<-15) 
			dy=-15;
		
		push();
	}
}
