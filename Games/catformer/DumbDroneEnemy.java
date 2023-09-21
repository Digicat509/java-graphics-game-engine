package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class DumbDroneEnemy extends Enemy {
	
	private boolean onGround = false;
	private static final int RANGE = 150;
	
	public DumbDroneEnemy(int x, int y) {
		super(x, y, 0);
		w = 22;
		h = 12;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/RollyPolly.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	public void draw(Graphics g) {
		//g.setColor(Color.MAGENTA);
		//g.fillRect((int)x, (int)y, w, h);
		g.drawImage(image, (int)x, (int)y, null);
		move();
	}
	@Override
	public void move() {
		y+=dy;
		
		GameObject o = this.hits();
		if(o != null)
		{
			onGround = true;
			if(Platformer.player.x < this.x && Platformer.player.x > this.x-RANGE) {
				dx = -4;
				try {
					this.image = ImageIO.read(getClass().getResource("assets/RollingPolly.png"));
					w = 15;
					h = 15;
				} catch (Exception e) {e.printStackTrace();}
			}
			dy = 0;
			if(o.y>= this.y) {
				y = o.y-h;
			}
		}
		else
		{
			onGround = false;
			dy += Platformer.GRAVITY;
		}
		HashSet<GameObject> arr = this.allHits();
		x += dx;
		HashSet<GameObject> narr = this.allHits();
		o = this.hits();
		if(o != null)
			x = o.x+o.w;
		else if(arr.size() > 0 && narr.size() > 0 && arr.containsAll(narr))
		{
			dx *= -1;
		}
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}
}
