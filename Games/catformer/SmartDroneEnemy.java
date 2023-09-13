package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class SmartDroneEnemy extends Enemy {
	
	private float gravity = 0.5f;
	
	public SmartDroneEnemy(int x, int y) {
		super(x, y, 5);
		w = 15;
		h = 15;
	}
	public void draw(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, w, h);
		move();
	}
	@Override
	public void move() {
		y+=dy;
		GameObject o = this.hits();
		
		if(o != null)
		{
			if(Platformer.player.x < this.x && Platformer.player.x > this.x-100) {
				x -= dx;
			}
			if(Platformer.player.x > this.x && Platformer.player.x < this.x+this.w+100) {
				x += dx;
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
