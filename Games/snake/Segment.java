package snake;

import java.awt.*;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class Segment extends GameObject{
	public static int size = 30;
	public static float speed = 1f;
	public String direction;
	static Image sideSnake;
	static Image upSnake;
	//constructor for a snake with default direction
	public Segment(float x, float y, String direction)
	{
		try{sideSnake=ImageIO.read(getClass().getResource("sideSnake.png"));} catch(Exception e){}
		try{upSnake=ImageIO.read(getClass().getResource("upSnake.png"));} catch(Exception e){}
		this.x=x;
		this.y=y;
		dx = speed;
		dy = 0;
		w=size;
		h=size;
		this.direction = direction;
	}
	//constructor for a snake with a specific direction 
	public Segment(float x, float y, float dx, float dy, String direction)
	{
		try{upSnake=ImageIO.read(getClass().getResource("upSnake.png"));} catch(Exception e){}
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		w=size;
		h=size;
		this.direction = direction;
	}
	@Override
	public void draw(Graphics g)//draws the correct image for the direction of the segment
	{
		//g.setColor(new Color(28, 152, 0));
		if(direction.equals("up") || direction.equals("down"))
			g.drawImage(upSnake, (int)x,(int)y,w,h, null);
		if(direction.equals("left") || direction.equals("right"))
			g.drawImage(sideSnake, (int)x,(int)y,w,h, null);
		move();
	}
	public void move()
	{
		x += dx;
		y += dy;
	}
}