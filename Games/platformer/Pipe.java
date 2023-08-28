package platformer;

import java.awt.Color;
import java.awt.Graphics;

public class Pipe extends Platform {

	public Pipe(int x, int y, int w) {
		super(x, y, w, 10);
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, w, h);
	}
}
