package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FloatingPlatform extends Platform {
	public FloatingPlatform(int x, int y, int w, int h)
	{
		super(x, y, w, h, 0);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.blue);
		g2d.fillRect((int)x, (int)y, w, h);
	}
}
