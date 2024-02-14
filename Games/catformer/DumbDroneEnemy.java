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
	
	public DumbDroneEnemy(int x, int y, boolean disabled) {
		super(x, y, 0, 30, disabled);
		w = 22;
		h = 12;
		try {
			this.setImage(ImageIO.read(getClass().getResource("assets/RollyPolly.png")));
		} catch (Exception e) {e.printStackTrace();}
	}
	public void draw(Graphics g) {
		g.drawImage(getImage(), (int)x, (int)y, null);
		if(!disabled)
			move();
	}
	@Override
	public void move() {
		super.move();
		
		y+=dy;
		
		GameObject o = this.hits();
		if(o != null)
		{
			if(o instanceof Player)
				((Player)o).damage(this);
			onGround = true;
			if(Platformer.player.x > this.x-RANGE && !rolling) {
				dx = -4;
				if(Platformer.player.x > this.x)
					dx = 4;
				rolling = true;
				try {
					this.setImage(ImageIO.read(getClass().getResource("assets/RollingPolly.png")));
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
		push();
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
