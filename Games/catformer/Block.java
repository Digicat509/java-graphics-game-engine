package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class Block extends Platform{
	int gridX, gridY = 0;
	public Block(int x, int y)
	{
		super(x, y, Grid.currGridSize, Grid.currGridSize, 0);
		gridX = x;
		gridY = y;
		try {
			this.setImage(ImageIO.read(getClass().getResource("assets/TestTile.png")));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void draw(Graphics g) {
//		x = gridX*Grid.currGridSize;
//		y = gridY*Grid.currGridSize;
		g.drawImage(getImage(), (int)x, (int)y, w, h, null);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Block ? x == ((Block)obj).x && y == ((Block)obj).y: super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25;
	}
}
