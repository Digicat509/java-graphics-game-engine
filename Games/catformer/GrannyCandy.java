package catformer;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class GrannyCandy extends Enemy {

	private long timer = 0;
	
	public GrannyCandy(int x, int y, int dx, float dy) {
		super(x, y, dx);
		this.dy = dy;
		w = 10;
		h = 10;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/Candy.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.magenta);
		//g.drawRect((int)x, (int)y, w, h);
		g.drawImage(image, (int)x, (int)y, w, h, null);
		move();
	}
	@Override
	public void move() {
		super.move();
		
		GameObject o = this.hits();
		if(o != null && !(o instanceof Enemy))
		{
			dy = 0;
			dx = 0;
			if(o.y<= this.y) {
				y = o.y-h;
			}
			if(timer == 0)
				timer = System.currentTimeMillis()+50;
		}
		else if(o == null)
		{
			dy += Platformer.GRAVITY;
		}
		if(timer > 0 && timer < System.currentTimeMillis() && w < 30)
		{
			w += 5;
			h += 5;
			y -= 2.5;
			x -= 2.5;
			timer = System.currentTimeMillis()+50;
		}
		else if(timer > 0 && timer < System.currentTimeMillis())
			Platformer.game.getHandeler().remove(this);
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}

}
