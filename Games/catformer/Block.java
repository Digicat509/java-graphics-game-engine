package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import gameEngine.GameObject;

public class Block extends Platform{
	int gridX, gridY = 0;
	public Block(int x, int y)
	{
		super(x*Grid.currGridSize, y*Grid.currGridSize, Grid.currGridSize, Grid.currGridSize, 0);
		gridX = x;
		gridY = y;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
//		x = gridX*Grid.currGridSize;
//		y = gridY*Grid.currGridSize;
		g.fillRect((int)x, (int)y, Grid.currGridSize, Grid.currGridSize);
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
		return ""+this.getClass()+" "+x+", "+y;
	}
}
