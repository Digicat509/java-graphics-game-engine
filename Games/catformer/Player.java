package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gameEngine.GameObject;

public class Player extends GameObject{
	private float gravity = .5f;
	private float slidingGravity = 2f;
	private float jumpStrength = 10f;
	private float speed = 3;
	private int localX;
	private boolean onGround = false;
	private boolean wallJump = false;
	private boolean sliding = false;
	private long waitTime;

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
	
	public void arrowMovement() {
		dx = 0;
		if(onGround && Platformer.game.getInput().isKey(KeyEvent.VK_W))
		{
			dy = -jumpStrength;
			
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_D))
		{
			if(localX > 0)
				dx += speed;
			else
			{
				x += speed;
				localX += speed;
				
				GameObject o = this.hits();
				if(o != null)
				{
					x -= speed;
					localX -= speed;
				}
			}
			
		}
		
		if(Platformer.game.getInput().isKey(KeyEvent.VK_A))
		{
			if(localX > 0) 
				dx -= speed;
			
			else if(x > 0)
			{
				x -= speed;
				localX -= speed;
				GameObject o = this.hits();
				if(o != null)
				{
					x += speed;
					localX += speed;
				}
				if(x < 0)
					x = 0;
			}
		}
		y += dy;
	}
	
	public void collisionJumps(GameObject o) {
		Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= this.dx;});
		o = this.hits();
		if(o != null)
		{
			localX -= dx;
			//stops downward acceleration when sliding 
			if((Platformer.game.getInput().isKey(KeyEvent.VK_D) && dx > 0) || (Platformer.game.getInput().isKey(KeyEvent.VK_A) && dx < 0))
			{
				sliding = true;
				//Wall jump if touching a wall
				if(wallJump && Platformer.game.getInput().isKey(KeyEvent.VK_W))
				{
					System.out.println("WallJump?");
					wallJump = false;
					dy = -jumpStrength;
					dx *= 10;
					sliding = false;
					waitTime = System.currentTimeMillis()+200;
				}
			}
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += this.dx;});
		}
		else {
			if(waitTime <= System.currentTimeMillis())
				wallJump = true;
			sliding = false;
		}
	}
	
//	public void teleport() {
//		GameObject o = this.hits();
//		if(o instanceof Portal) {
//			for(GameObject temp: Platformer.game.getHandeler().hand) {
//				
//			}
//		}
//	}
	
	public void move()
	{
		arrowMovement();
		
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
			if(sliding)
				dy = slidingGravity;
		}
		localX += dx;
		
		collisionJumps(o);
		
		if(y > Platformer.game.getHeight()) {
			Platformer.game.stop();
			Platformer.start();
		}
		
	}
}
