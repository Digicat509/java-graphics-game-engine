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
	private int scrollDistance = 300;
	
	private boolean sideTouch = false;
	
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
				//x += speed;
				//localX += speed;
				dx += speed;
				
				GameObject o = this.hits();
				if(o != null)
				{
					dx -= speed;
					//x -= speed;
					sideTouch = true;
					//testJumps(o);
					//System.out.println("touch");
					//localX -= speed;
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
				//x -= speed;
				//localX -= speed;
				GameObject o = this.hits();
				if(o != null)
				{
					dx += speed;
					//x += speed;
					//sideTouch = true;
					//testJumps(o);
					//localX += speed;
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
			x += direction*dx;
	}
	
	public void scroll() {
		if(this.x>800) {
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += this.dx;});
		}
	}
	
	public void testJumps(GameObject o) {
		Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= this.dx;});

		if(sideTouch) {
			sliding = true;
			System.out.println("asvdiufa sdfiuwheiufd dsfda");
			
			if(onGround==false) {
				System.out.println("On Ground");
			}
			if((Platformer.game.getInput().isKey(KeyEvent.VK_W) || Platformer.game.getInput().isKey(KeyEvent.VK_UP))) {
//				System.out.println("Wall Jump");
//				onGround=true;
//				dy=-slidingGravity;
				System.out.println("WallJump?");
				wallJump = false;
				dy = -jumpStrength;
				if(Platformer.game.getInput().isKey(KeyEvent.VK_A))
					dx=-1;
				if(Platformer.game.getInput().isKey(KeyEvent.VK_D))
					dx=1;
				dx *= 10;
				sliding = false;
				waitTime = System.currentTimeMillis()+200;
			}

			
			if(onGround == false && wallJump && (Platformer.game.getInput().isKey(KeyEvent.VK_W) || Platformer.game.getInput().isKey(KeyEvent.VK_UP))) {
				System.out.println("working!!!");
				wallJump = false;
				dy = -jumpStrength;
				dx *= 10;
				sliding = false;
				waitTime = System.currentTimeMillis()+200;
			}
		}
		//o = this.hits();

		else {
			if(waitTime <= System.currentTimeMillis())
				wallJump = true;
			sliding = false;
		}

		
	}
	
	public void collisionJumps(GameObject o) {
		//Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= this.dx;});
		updatePosition(1);
		
		o = this.hits();
		//System.out.println(this.allHits()+"\n\n\n\n");

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
					waitTime = System.currentTimeMillis()+150;
				}
			}
			localX -= dx;
			updatePosition(-1);
			//Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += this.dx;});
			o = this.hits(); //supposed to fix the collision issue when wall jumping 
			if(o instanceof Platform) //TODO fix this
			{
				Platform p = (Platform)o;
				localX += dx;//(p.x-x);
				updatePosition(1);
				//Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= this.dx/*p.x-x*/;});
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
		
		if(o instanceof Enemy || o instanceof Hazard)
		{
			Platformer.game.stop();
			Platformer.start();
			if(o instanceof Net)
				Platformer.game.getHandeler().remove(o);
		}
		
		if(o instanceof Portal)
		{
			y -= dy;
			int move = (int)(((Portal)o).oPortal.x-this.w/2);
			this.y = (((Portal)o).oPortal.y-this.h);
			localX += move;
			dy = -dy;
			y += dy;
			updatePosition(1, move);
			//Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= move;});
		}
		
		o = this.hits();
		if(o != null)
		{
			if(o.y>= this.y && x+w > o.x && x < o.x+o.w) {
				dy = 0;
				y = o.y-h;
				onGround = true;
				//System.out.println("over");
			}
			if(y >= o.y+o.h-5) {
				//System.out.println("under");
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
		
		//System.out.println("Collision jumps?");
		collisionJumps(o);
		//testJumps(o);
		
		if(x < 0)
			x = 0;
		
		if(y > Platformer.game.getHeight()) {
			Platformer.game.stop();
			Platformer.start();
		}
		
		if(Platformer.game.getInput().isKey(KeyEvent.VK_R)){
			Platformer.game.stop();
			Platformer.start();
		}
		
		Platformer.updateDistance((localX+30)/w);
		
		if(Platformer.level.stage.equals(Level.Stage.INFINITE))
				Platformer.level.update(localX, y);
	}
}
