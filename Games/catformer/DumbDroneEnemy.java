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
	private boolean rolling = false;
	
	public DumbDroneEnemy(int x, int y) {
		super(x, y, 0, 30);
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
			if(Platformer.player.x > this.x-RANGE && !rolling) {
				dx = -4;
				if(Platformer.player.x > this.x)
					dx = 4;
				rolling = true;
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
		if(arr.size() < narr.size()) {
			x -= dx;
			if(arr.size() < narr.size() && onGround)
			{
				dx *= -1;
			}
		}
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}
}
