package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameEngine.GameObject;

public class Player extends GameObject{
	private float gravity = .5f;
	private float jumpStrength = 10f;
	private float speed = 2;
	private int localX;
	private boolean onGround = false;
	public Player(int x, int y)
	{
		super(2);
		this.x = x;
		localX = 0;
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
			dx += speed;
		}
		if(localX > 0 && Platformer.game.getInput().isKey(KeyEvent.VK_A))
		{
			dx -= speed;
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
		}
		else
		{
			onGround = false;
			dy += gravity;
		}
		localX += dx;
		Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= this.dx;});
		o = this.hits();
		if(o != null)
		{
			localX -= dx;
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += this.dx;});
		}
		if(y > Platformer.game.getHeight()) {
			Platformer.game.stop();
			Platformer.start();
		}
	}
}
