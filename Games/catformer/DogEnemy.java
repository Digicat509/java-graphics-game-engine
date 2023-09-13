package catformer;

import java.awt.*;
import javax.imageio.ImageIO;
import gameEngine.GameObject;

public class DogEnemy extends GameObject{
	
	private boolean onGround = false;
	private float gravity = 0.5f;
	
	public DogEnemy(int x, int y) {
		this.x = x;
		this.y = y;
		w = 20;
		h = 20;
		dx = 5;
		dy = 0;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void move() {
		GameObject o = this.hits();
		
		if(o != null)
		{
			if(x > o.x && (x+w) < (o.x+o.w) )
				x+=dx;
			else {
				dx*=-1;
			}
			
			if(o.y>= this.y) {
				y = o.y-h;
				dy = 0;
				
				System.out.println("TOUCH");

			}
			//System.out.println("NO TOUCH");

		}
		else
		{
			System.out.println("GRAVITY");
			dy += gravity;
		}
		y+=dy;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, w, h);
		move();
	}
}
