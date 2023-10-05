package catformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BuildingBlock extends Platform{

	public BuildingBlock(int x, int y, int w, int h) {
		super(x, y, 55, 55, 0);
		try {
			image = ImageIO.read(getClass().getResource("assets/TestTile.png"));
		} catch (IOException e) {}
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, (int)x, (int)y, 55, 55, null);
	}

	
}
