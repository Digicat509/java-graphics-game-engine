package catformer;

import java.awt.*;
import javax.imageio.ImageIO;
import gameEngine.GameObject;
import gameEngine.Area;

public class DogEnemy extends Enemy{
	
	Area edgeChecker;
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
		super.collisionEffects(o);
		if(o != null && !(o instanceof Portal))
		{
			x+= dx;
			Platformer.game.getHandeler().remove(edgeChecker);
			edgeChecker = new Area((int)(dx < 0 ? x+dx : x+w+3), (int)y+h+5, 2, 2, false);
			if(!edgeChecker.hitsAny()) {
				dx *= -1;
			}
			dy = 0;
			if(o.y>= this.y) {
				y = o.y-h;
			}
			o = this.hits();
			if(o instanceof Platform)
			{
				if(dx > 0 && o.x <= x+w)
					updatePosition(1, (int)(o.x-x-w));
				else if(dx < 0 && x <= o.x+o.w)
					updatePosition(1, (int)(o.x+o.w-x));
				dx *= -1;
			}
		}
		else
		{
			dy += Platformer.GRAVITY;
		}
		
		if(dy>15)
			dy=15;
		if(dy<-15) 
			dy=-15;
		
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
