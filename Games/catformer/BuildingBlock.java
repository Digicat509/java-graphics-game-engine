package catformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class BuildingBlock extends GameObject{

	public BuildingBlock(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		try {
			image = ImageIO.read(getClass().getResource("assets/TestTile.png"));
		} catch (IOException e) {}
		Platformer.game.getHandeler().add(this, false);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, (int)x, (int)y, 55, 55, null);
	}

	
}
