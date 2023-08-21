package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import gameEngine.GameObject;
import gameEngine.Handler;

public class Apple extends GameObject{
	static Image apple;
	public Apple()//constructor
	{
		Snake.game.getHandeler().add(this, true);
		try{apple=ImageIO.read(getClass().getResource("apple.png"));} catch(Exception e){}
		w = 20;
		h = 20;
		x = ((int)(Math.random()*(16)))*30+5;
		y = ((int)(Math.random()*(16)))*30+5;
		while(Snake.player.hits(this))
		{
			x = ((int)(Math.random()*(16)))*30+5;
			y = ((int)(Math.random()*(16)))*30+5;
		}
	}
	// when the apple hits the snake it moves to a place the snake is not
	public void teleport()
	{
		x = ((int)(Math.random()*(16)))*30+5;
		y = ((int)(Math.random()*(16)))*30+5;
		if(Snake.player.snakeLength < 256)
		{
			while(Snake.player.hits(this))
			{
				x = ((int)(Math.random()*(16)))*30+5;
				y = ((int)(Math.random()*(16)))*30+5;
			}
		}
		else
		{
			w = 0;
			h = 0;
		}
	}
	@Override
	public void draw(Graphics g)//draws the apple with the apple image in the package
	{
		if(this.hits(Snake.player.list.get(0)))
		{
			Snake.player.addSegment();
			teleport();
		}
		g.setColor(Color.red);
		g.drawImage(apple, (int)x, (int)y, w, h, null);
	}
}
