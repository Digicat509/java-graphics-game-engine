package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

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
	private boolean facing = true;
	
	public Player(int x, int y)
	{
		super(2, "assets/Cat.png");
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
		if(facing) {
			g.drawImage(image,(int)x,(int)y, w, h, null);
		}
		else {
			g.drawImage(image,(int)x+w,(int)y, -w, h, null);
		}
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
			facing = true;
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
			facing = false;
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
		if(o instanceof Platform)
		{
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
			localX -= dx;
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += this.dx;});
			/*o = this.hits(); supposed to fix the collision issue when wall jumping 
			if(o instanceof Platform) TODO fix this
			{
				Platform p = (Platform)o;
				localX += (p.x-x);
				Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= p.x-x;});
			}*/
		}
		else {
			if(waitTime <= System.currentTimeMillis())
				wallJump = true;
			sliding = false;
		}
	}
	
	public void move()
	{
		arrowMovement();
		
		GameObject o = this.hits();
		
		if(o instanceof Enemy)
		{
			Platformer.game.stop();
			Platformer.start();
		}
		
		if(o instanceof Portal)
		{
			y -= dy;
			int move = (int)(((Portal)o).x+((Portal)o).ox+((Portal)o).w/2-this.w);
			this.y = ((Portal)o).y+((Portal)o).oy-this.h;
			localX += move;
			dy = -dy;
			y += dy;
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= move;});
		}
		
		o = this.hits();
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
		
		if(Platformer.level.stage.equals(Level.Stage.INFINITE))
				Platformer.level.update(localX, y);
	}
}
