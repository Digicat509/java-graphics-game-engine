package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Grid extends GameObject {
	public static final int GRIDSIZE = 25;
	public static int currGridSize = 25;
	public Grid()
	{
		super(5);
		Platformer.game.getHandeler().add(this, false);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		for(int x = 0; x < Platformer.game.getWidth(); x+= currGridSize)
			for(int y = 0; y < Platformer.game.getHeight(); y += currGridSize)
				g.drawRect(x, y, currGridSize, currGridSize);
	}
}
