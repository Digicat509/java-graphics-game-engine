package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

import gameEngine.GameObject;

public class Player extends GameObject{
	private float slidingGravity = 2f;
	private float jumpStrength = 10f;
	private float speed = 3;
	private int localX;
	private boolean onGround = false;
	private boolean wallJump = false;
	private boolean sliding = false;
	private long waitTime;
	private boolean facing = true;
	public int scrollDistance = 300;
	private int invincibilityFrames = 30;
	private long frameTimer;
	private long timer = 0;
	private long healTimer = 0;
	private int HP;
	int maxHP;
	
	public Player(int x, int y)
	{
		super(3, "assets/Cat.png");
		this.x = x;
		localX = 0;
		this.y = y;
		w = 20;
		h = 20;
		dx = 0;
		dy = 0;
		HP = 100;
		maxHP = HP;
		new HPBar();
		Platformer.game.getHandeler().add(this, true);
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(visible)
		{
			if(facing) {
				g.drawImage(image,(int)x,(int)y, w, h, null);
			}
			else {
				g.drawImage(image,(int)x+w,(int)y, -w, h, null);
			}
			if(System.currentTimeMillis() < timer)
			{
				if(frameTimer >= 5)
				{
					visible = false;
					frameTimer = 0;
				}
				frameTimer ++;
			}
		}
		else
		{
			if(frameTimer >= 5)
			{
				visible = true;
				frameTimer = 0;
			}
			frameTimer ++;
		}
		move();
		
	}
	
	
	
	public void arrowMovement() {
		dx = 0;
		if(onGround && (Platformer.game.getInput().isKey(KeyEvent.VK_W) || Platformer.game.getInput().isKey(KeyEvent.VK_UP)))
		{
			dy = -jumpStrength;
			
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_D) || Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT))
		{
			facing = true;
			if(localX > scrollDistance)
				dx += speed;
			else
			{
				dx += speed;
				
				GameObject o = this.hits();
				if(o != null)
				{
					dx -= speed;
				}
			}
			
		}
		
		if(Platformer.game.getInput().isKey(KeyEvent.VK_A) || Platformer.game.getInput().isKey(KeyEvent.VK_LEFT))
		{
			facing = false;
			if(localX > scrollDistance) 
				dx -= speed;
			
			else if(x > 0)
			{
				dx -= speed;

				GameObject o = this.hits();
				if(o != null)
				{
					dx += speed;
				}
			}
		}
		y += dy;
	}
	
	private void updatePosition(int direction)
	{
		if(localX > scrollDistance)
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= direction*this.dx;});
		else
			x += direction*dx;
	}
	
	private void updatePosition(int direction, int amount)
	{
		if(localX > scrollDistance)
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= direction*amount;});
		else
			x += direction*amount;
	}
	
	public void scroll() {
		if(this.x>800) {
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += this.dx;});
		}
	}
	
	public void collisionJumps(GameObject o) {
		updatePosition(1);
		
		o = this.hits();

		if(o instanceof Platform)
		{
			//stops downward acceleration when sliding 
			if(((Platformer.game.getInput().isKey(KeyEvent.VK_D) || Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT)) && dx > 0) || ((Platformer.game.getInput().isKey(KeyEvent.VK_A) || Platformer.game.getInput().isKey(KeyEvent.VK_LEFT)) && dx < 0))
			{
				sliding = true;

				//Wall jump if touching a wall
				if(onGround == false && wallJump && (Platformer.game.getInput().isKey(KeyEvent.VK_W) || Platformer.game.getInput().isKey(KeyEvent.VK_UP)))
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
			updatePosition(-1);
			o = this.hits(); //supposed to fix the collision issue when wall jumping 
			if(o instanceof Platform) //TODO fix this
			{
				Platform p = (Platform)o;
				localX += dx;//(p.x-x);
				updatePosition(1);
			}
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
		
		if(o instanceof DangerThing)
		{
			if(System.currentTimeMillis() > timer)
			{
				this.HP -= ((DangerThing)o).damage;
				timer = System.currentTimeMillis() + (int)(1000*((double)invincibilityFrames/60));
			}
			if(o instanceof Net)
				Platformer.game.getHandeler().remove(o);
			if(HP <= 0)
			{
				Platformer.game.stop();
				Platformer.start();
			}
		}
		
		if(o instanceof Portal)
		{
			y -= dy;
			int move = (int)(((Portal)o).oPortal.x+((Portal)o).oPortal.w/2-this.w/2)-(int)x;
			this.y = (((Portal)o).oPortal.y-this.h);
			dy = -dy;
			y += dy;
			localX += move;
			updatePosition(1, move);
		}
		
		if(o instanceof Box)
		{
			if(System.currentTimeMillis() > healTimer)
			{
				if(this.HP <= maxHP-5)
				{
					this.HP += ((Box)o).heal/10;
					((Box)o).heal -= 5;
					if(((Box)o).heal <= 0)
						Platformer.game.getHandeler().remove(o);
				}
				healTimer = System.currentTimeMillis() + 1000;
			}
		}
		
		o = this.hits();
		if(o != null)
		{
			if(o.y>= this.y && x+w > o.x && x < o.x+o.w) {
				dy = 0;
				y = o.y-h;
				onGround = true;
			}
			if(y >= o.y+o.h-5) {
				dy = 0;
				y = o.y+o.h;
			}
		}
		else
		{
			onGround = false;
			dy += Platformer.GRAVITY;
			if(sliding)
				dy = slidingGravity;
		}
		localX += dx;
		
		collisionJumps(o);
		
		if(x < 0)
			x = 0;
		
		if(y > Platformer.game.getHeight()) {
			Platformer.game.stop();
			Platformer.start();
		}
		
		if(Platformer.game.getInput().isKey(KeyEvent.VK_R)){
			Platformer.game.stop();
			//Platformer.start();
		}
		
		Platformer.updateDistance((localX+30)/w);
		
		if(Platformer.level.stage.equals(Level.Stage.INFINITE))
				Platformer.level.update(localX+30, y);
	}
	public int getHP() {
		return HP;
	}
}
