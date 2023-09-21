package catformer;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class GrannyCandy extends Enemy {

	private long timer = 0;
	private boolean candy = true;
	
	public GrannyCandy(int x, int y, int dx, float dy) {
		super(x, y, dx);
		this.dy = dy;
		w = 19;
		h = 11;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/Candy.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.magenta);
		if(!candy)
			g.fillRect((int)x, (int)y, w, h);
		else
			g.drawImage(image, (int)x, (int)y, w, h, null);
		move();
	}
	@Override
	public void move() {
		if(timer == 0)
		{
			x += dx;
			y += dy;
			GameObject o = this.hits();
			if(o != null && !(o instanceof Enemy))
			{
				y = o.y-h;
				dy = 0;
				dx = 0;
				if(timer == 0)
					timer = System.currentTimeMillis()+50;
			}
			else if(o == null)
			{
				dy += Platformer.GRAVITY;
			}
		}
		if(timer > 0 && timer < System.currentTimeMillis() && candy)
		{
			w += (19.0/11)*2;
			h += (11.0/19)*2;
			y -= (19.0/11);
			x -= (11.0/19);
			timer = System.currentTimeMillis()+50;
			candy = false;
		}
		else if(timer > 0 && timer < System.currentTimeMillis() && w < 30 && h < 30)
		{
			x += (w-h)/2;
			w = h;
			w += 2;
			h += 2;
			y -= 2;
			x -= 1;
			timer = System.currentTimeMillis()+50;
		}
		else if(timer > 0 && timer < System.currentTimeMillis())
			Platformer.game.getHandeler().remove(this);
		if(y > Platformer.game.getHeight())
			Platformer.game.getHandeler().remove(this);
	}

}
