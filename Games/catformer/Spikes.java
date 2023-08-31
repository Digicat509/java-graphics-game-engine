package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Spikes extends Platform{

	public Spikes(int x, int y, int w, double theta) {
		super(x, y, w, 10, theta);
	}
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(rotation);
		g2d.setColor(Color.gray);
		g2d.fillRect((int)x, (int)y, w, h);
		g2d.rotate(-rotation);//<----- this does not work idk whats wrong
	}
}
