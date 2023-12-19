package catformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class BuildingBlock extends GameObject{

	public BuildingBlock(int x, int y, int w, int h, String path) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, (int)x, (int)y, w, h, null);
	}

	
}
