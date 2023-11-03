package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import gameEngine.GameObject;

public class Block extends GameObject{
	public Block(int x, int y)
	{
		super(1);
		this.x = x;
		this.y = y;
		w = Grid.currGridSize;
		h =	Grid.currGridSize;
		Platformer.game.getHandeler().add(this, true);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x*Grid.currGridSize, (int)y*Grid.currGridSize, Grid.currGridSize, Grid.currGridSize);
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
		return ""+this.getClass()+" "+x/Grid.GRIDSIZE+", "+y/Grid.GRIDSIZE;
	}
}
