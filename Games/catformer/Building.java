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
		g2d.fillRect((int)x, (int)y, w, h);
	}
}
