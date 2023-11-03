package catformer;

import java.awt.*;
import javax.imageio.ImageIO;
import gameEngine.GameObject;

public class DogEnemy extends Enemy{
	
	public DogEnemy(int x, int y) {
		super(x, y, 2, 10);
		w = 32;
		h = 20;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/Dog.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void move() {
		y+=dy;
		GameObject o = this.hits();
		
		if(o != null)
		{
			x+= dx;
			if(x < o.x) {
				x = o.x;
				dx*=-1;
			}
			else if((x+w) > (o.x+o.w)){
				x = o.x+o.w-w;
				dx*=-1;
			}
			dy = 0;
			if(o.y>= this.y) {
				y = o.y-h;
			}
		}
		else
		{
			dy += Platformer.GRAVITY;
		}
		
		push();
	}
	
	@Override
	public void draw(Graphics g) {
		if(dx < 0)
			g.drawImage(image, (int)x, (int)y, w, h, null);
		else
			g.drawImage(image, (int)x+w, (int)y, -w, h, null);
		move();
	}
}
