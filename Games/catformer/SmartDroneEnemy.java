package catformer;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class SmartDroneEnemy extends Enemy {
	
	private float gravity = 0.5f;
	private static final int RANGE = 200;
	private boolean facing = true;
	
	public SmartDroneEnemy(int x, int y) {
		super(x, y, 3, 20);
		w = 28;
		h = 15;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/Beetle.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	public void draw(Graphics g) {
		//g.setColor(Color.pink);
		//g.fillRect((int)x, (int)y, w, h);
		if(facing)
			g.drawImage(image,(int)x, (int)y, w, h, null);
		else 
			g.drawImage(image,(int)x+w, (int)y, -w, h, null);
		move();
	}
	@Override
	public void move() {
		y+=dy;
		GameObject o = this.hits();
		
		if(o != null)
		{
			if(Platformer.player.x < this.x && Platformer.player.x > this.x-RANGE) {
				x -= dx;
				facing = true;
			}
			if(Platformer.player.x > this.x && Platformer.player.x < this.x+this.w+RANGE) {
				x += dx;
				facing = false;
			}
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
		else
		{
			dy += gravity;
		}
	}
}
