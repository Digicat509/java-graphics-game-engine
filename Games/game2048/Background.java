package game2048;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Background extends GameObject{
	public Background() {
		super(0);
		Game2048.game.getHandeler().add(this, false);
	}
	public void draw(Graphics g)
	{
		g.setColor(new Color(217, 253, 153));
		g.fillRect(0,0,Game2048.game.getWidth(),Game2048.game.getHeight());
		g.setColor(new Color(213, 250, 88));
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				g.fillRect(120*i-2, 120*j-2, 116, 116);
	}
}
