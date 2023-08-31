package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameEngine.GameObject;

public class Player extends GameObject{
	private float gravity = .5f;
	private float jumpStrength = 10f;
	private boolean onGround = false;
	public Player(int x, int y)
	{
		super(2);
		this.x = x;
		this.y = y;
		w = 20;
		h = 20;
		dx = 0;
		dy = 0;
		Platformer.game.getHandeler().add(this, true);
	}
	@Override
	public void draw(Graphics g)
	{
		g.setColor(new Color(213, 250, 88));
		g.fillRect((int)x, (int)y, w, h);
		move();
	}
	public void move()
	{
		dx = 0;
		if(onGround && Platformer.game.getInput().isKey(KeyEvent.VK_W))
		{
			dy = -jumpStrength;
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_D))
		{
			dx += 1;
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_A))
		{
			dx -= 1;
		}
		y += dy;
		
		
		GameObject o = this.hits();
		if(o != null)
		{
			if(o.y>= this.y) {
				dy = 0;
				y = o.y-h;
				onGround = true;
			}
			//collision on the right
			if(o.x+o.w > this.x && o.y < this.y && this.x > o.x) {
				System.out.println("Right");
				dx = 0;
				x = o.x +o.w;
			}
			//collision in the left 
			else if(o.x < this.x + this.w && o.y < this.y) {
				System.out.println("Left");
				dx = 0;
				x = o.x - w;
			}
			
		}
		else
		{
			onGround = false;
			dy += gravity;
		}
		x += dx;
	}
}
