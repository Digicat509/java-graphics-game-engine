package catformer;

import java.awt.*;

public class Pipe extends Platform {

	public Pipe(int x, int y, int w) {
		super(x, y, w, 10, 0);
	}
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(rotation);
		g2d.setColor(Color.gray);
		g2d.fillRect((int)x, (int)y, w, h);
	}
}
