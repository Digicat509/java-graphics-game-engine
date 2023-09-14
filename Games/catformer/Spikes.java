package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Spikes extends Hazard{

	public Spikes(int x, int y, int w) {
		super(x, y, w, 10);
	}
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect((int)x, (int)y, w, h);
	}
}
