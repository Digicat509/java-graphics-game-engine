package platformer;

import java.awt.Color;
import java.awt.Graphics;

public class Building extends Platform {

	public Building(int x, int y, int w) {
		super(x, y, w, Platformer.game.getHeight()-y);
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, w, h);
	}
}
