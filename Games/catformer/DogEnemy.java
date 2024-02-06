package catformer;

import java.awt.*;
import javax.imageio.ImageIO;
import gameEngine.GameObject;

public class DogEnemy extends Enemy{
	
	public DogEnemy(int x, int y, boolean disabled) {
		super(x, y, 2, 10, disabled);
		w = 32;
		h = 20;
		try {
			this.setImage(ImageIO.read(getClass().getResource("assets/Dog.png")));
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
			g.drawImage(getImage(), (int)x, (int)y, w, h, null);
		else
			g.drawImage(getImage(), (int)x+w, (int)y, -w, h, null);
		if(!disabled)
			move();
	}
	
	@Override
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25;
	}
}
