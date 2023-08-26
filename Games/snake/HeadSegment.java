package snake;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

//The head of the snake it is special
//It is drawn differently and is always the first item in the snake list
public class HeadSegment extends Segment{
	public String nextMove;
	static Image upSnake;
	static Image leftSnake;
	static Image rightSnake;
	static Image downSnake;
	public HeadSegment(float x, float y, String direction)//constructor
	{
		super(x, y, direction);
		try{downSnake=ImageIO.read(getClass().getResource("downSnakeHead.png"));} catch(Exception e){}
		try{upSnake=ImageIO.read(getClass().getResource("upSnakeHead.png"));} catch(Exception e){}
		try{leftSnake=ImageIO.read(getClass().getResource("leftSnakeHead.png"));} catch(Exception e){}
		try{rightSnake=ImageIO.read(getClass().getResource("rightSnakeHead.png"));} catch(Exception e){}
	}
	// draws the head with the correct image for the direction it's going
	@Override
	public void draw(Graphics g)
	{
		if(direction.equals("up"))
			g.drawImage(upSnake, (int)x,(int)y,w,h, null);
		if(direction.equals("down"))
			g.drawImage(downSnake, (int)x,(int)y,w,h, null);
		if(direction.equals("left"))
			g.drawImage(leftSnake, (int)x,(int)y,w,h, null);
		if(direction.equals("right"))
			g.drawImage(rightSnake, (int)x,(int)y,w,h, null);
		move();
	}
	//checks if the snake hits anything it shouldn't and if the player inputs a movement command
	@Override
	public void move()
	{
		//if the player hits the edge
		if(x < 0 || x > Snake.game.getWidth()-w || y < 0 || y > (Snake.game.getHeight()-h))
		{
			Snake.game.getHandeler().clear();
			Snake.restart();
		}
		//if the player hits itself
		if(Snake.player.hits(this))
		{
			Snake.game.getHandeler().clear();
			Snake.restart();
		}
		//sets the movement booleans
		if(Snake.game.getInput().isKey(KeyEvent.VK_DOWN))
			nextMove = "down";
		else if(Snake.game.getInput().isKey(KeyEvent.VK_UP))
			nextMove = "up";
		else if(Snake.game.getInput().isKey(KeyEvent.VK_RIGHT))
			nextMove = "right";
		else if(Snake.game.getInput().isKey(KeyEvent.VK_LEFT))
			nextMove = "left";
		//changes the heads direction
		if(x%Segment.size == 0 && y%Segment.size == 0)
		{
			if(!Player.pressed)
			{
				if("down".equals(nextMove) && !("up".equals(direction)))
				{
					dx = 0;
					dy = speed;
					direction = "down";
					Player.pressed = true;
				}
				else if("up".equals(nextMove) && !("down".equals(direction)))
				{
					dx = 0;
					dy = -speed;
					direction = "up";
					Player.pressed = true;
				}
				else if("right".equals(nextMove) && !("left".equals(direction)))
				{
					dx = speed;
					dy = 0;
					direction = "right";
					Player.pressed = true;
				}
				else if("left".equals(nextMove) && !("right".equals(direction)))
				{
					dx = -speed;
					dy = 0;
					direction = "left";
					Player.pressed = true;
				}
			}
		}
		x += dx;
		y += dy;
	}
	// the head hit test is specially sized to avoid unwanted collisions
	public Rectangle getHeadBounds()
	{
		if(direction.equals("right"))
			return new Rectangle((int)(x+(w-2)), (int)(y+(h/2)), 2, 2);
		if(direction.equals("left"))
			return new Rectangle((int)x, (int)(y+(h/2)), 2, 2);
		if(direction.equals("up"))
			return new Rectangle((int)(x+(w/2)), (int)y, 2, 2);
		if(direction.equals("down"))
			return new Rectangle((int)(x+(w/2)), (int)(y+(h-2)), 2, 2);
		return new Rectangle(0, 0, 0, 0);
	}
}
