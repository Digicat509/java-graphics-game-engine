package catformer;

import java.awt.*;

public class Pipe extends Platform {
	public Pipe(int x, int y, int w) {
		super(x, y, w, 10, 0);
	}
	public Pipe(int x, int y, int w, int theta) {
		super(x, y, w, 10, theta);
	}
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.gray);
		g2d.fillRect((int)x, (int)y, w, h);
	}
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25+", "+w/25;
	}
}
