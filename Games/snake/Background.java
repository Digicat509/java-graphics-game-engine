package snake;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Background extends GameObject{
	public Background()
	{
		super(0);
		Snake.game.getHandeler().add(this, false);
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(new Color(42, 217, 2));
		g.fillRect(0,0,Snake.game.getWidth(),Snake.game.getHeight());
		g.setColor(new Color(15, 198, 17));
		for(int i = 0; i < 16; i++)
			for(int j = 0; j < 16; j++)
			{
				if(i%2 == 0 && j%2 == 0)
				{
					g.setColor(new Color(15, 198, 17));
					g.fillRect(30*i, 30*j, 30, 30);
				}
				else if(i%2 == 0)
				{
					g.setColor(new Color(42, 217, 2));
					g.fillRect(30*i, 30*j, 30, 30);
				}
				else if(j%2 == 0)
				{
					g.setColor(new Color(42, 217, 2));
					g.fillRect(30*i, 30*j, 30, 30);
				}
				else
				{
					g.setColor(new Color(15, 198, 17));
					g.fillRect(30*i, 30*j, 30, 30);
				}
			}
	}
}
