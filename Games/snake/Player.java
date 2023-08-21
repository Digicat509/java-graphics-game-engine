package snake;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.*;

import gameEngine.GameObject;

public class Player extends GameObject{
	ArrayList <Segment> list = new ArrayList <Segment>();//the snake
	static boolean pressed;
	static int frameCounter = 1;
	int snakeLength;
	public Player()//constructor makes a 3 segment snake
	{
		Snake.game.getHandeler().add(this, true);
		list.add(new HeadSegment(210, 210, "right"));
		for(int i = 1; i <= 2; i++)
		{
			list.add(new Segment(210-(Segment.size*i), 210, "right"));
		}
		snakeLength = 3;
	}
	// adds a new segment to the end of the snake
	public void addSegment()
	{
		if(list.get(list.size()-1).direction.equals("right"))
			list.add(new Segment(list.get(list.size()-1).x-Segment.size, list.get(list.size()-1).y, list.get(list.size()-1).dx, list.get(list.size()-1).dy, "right"));
		else if(list.get(list.size()-1).direction.equals("left"))
			list.add(new Segment(list.get(list.size()-1).x+Segment.size, list.get(list.size()-1).y, list.get(list.size()-1).dx, list.get(list.size()-1).dy, "left"));
		else if(list.get(list.size()-1).direction.equals("up"))
			list.add(new Segment(list.get(list.size()-1).x, list.get(list.size()-1).y+Segment.size, list.get(list.size()-1).dx, list.get(list.size()-1).dy, "up"));
		else if(list.get(list.size()-1).direction.equals("down"))
			list.add(new Segment(list.get(list.size()-1).x, list.get(list.size()-1).y-Segment.size, list.get(list.size()-1).dx, list.get(list.size()-1).dy, "down"));
		snakeLength++;
	}
	@Override
	public void draw(Graphics g)// renders the whole snake from the list
	{
		for(int i = list.size()-1; i >= 0; i--)
		{
			list.get(i).draw(g);
		}
		move();
	}
	public void move()// goes through the list and updates the direction of each segment
	{
		pressed = false;
		if(list.get(1).x % Segment.size == 0 && list.get(1).y % Segment.size == 0)
		{
			for(int i = list.size()-1; i > 0; i--)
			{
				list.get(i).dx = list.get(i-1).dx;
				list.get(i).dy = list.get(i-1).dy;
				if(list.get(i).dx > 0)
					list.get(i).direction = "right";
				else if(list.get(i).dx < 0)
					list.get(i).direction = "left";
				else if(list.get(i).dy > 0)
					list.get(i).direction = "down";
				else if(list.get(i).dy < 0)
					list.get(i).direction = "up";
			}
		}
	}
	@Override
	public boolean hits(GameObject other)// checks if any part of the  snake hits anything
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getBounds().intersects((Rectangle2D)other.getBounds()))
			{
				return true;
			}
		}
		return false;
	}
	public boolean hits(HeadSegment other)// checks if the snake's head hits the snake
	{
		for(int i = 1; i < list.size(); i++)
		{
			if(list.get(i).getBounds().intersects(other.getHeadBounds()))
			{
				return true;
			}
		}
		return false;
	}
}