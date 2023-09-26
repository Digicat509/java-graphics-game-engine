package catformer;

import java.awt.*;

public class Building extends Platform {

	public Building(int x, int y, int w) {
		super(x, y, w, Platformer.game.getHeight()-y, 0);
	}
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.red);
		for(int i = 0; i < w; i += 10)//draws 10X10 rects change this to tiles when tile png is made
			for(int j = 0; j <= h; j += 10)
				g2d.fillRect((int)x+i, (int)y+j, 10, 10);
	}
	
}
