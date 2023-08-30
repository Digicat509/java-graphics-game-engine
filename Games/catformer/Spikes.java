package catformer;

import java.awt.Color;
import java.awt.Graphics;

public class Spikes extends Platform{

	public Spikes(int x, int y, int w) {
		super(x, y, w, 10);
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.gray);
		g.drawRect((int)x, (int)y, w, h);
	}
}
