package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.GameEngine.State;
import gameEngine.GameObject;
import gameEngine.Timer;

public class Player extends GameObject implements Entity {
	private float slidingGravity = 2f;
	private float jumpStrength = 10f;
	private float xAcceleration = 0;
	private float realdx = 0;
	private int localX;
	private boolean onGround = false;
	private boolean wallJump = false;
	private boolean sliding = false;
	private long waitTime;
	private boolean facing = true;
	public int scrollDistance = 300;
	private int invincibilityFrames = 30;
	private long frameTimer = 0;
	private Timer walkTimer = new Timer(0);
	private boolean walking;
	private Timer timer = new Timer(0);
	private Timer healTimer = new Timer(0);
	private int HP;
	int maxHP;
	private Platform lastWall;
	private Image walkImage;
	private int frame;
	private boolean disabled = false;
	
	public Player(int x, int y, boolean disabled)
	{
		super(3, "assets/GoodCat.png");
		this.disabled = disabled;
		this.x = x;
		localX = 0;
		this.y = y;
		w = 34;
		h = 14;
		dx = 0;
		dy = 0;
		HP = 100;
		maxHP = HP;
		try {
			this.walkImage = ImageIO.read(getClass().getResource("assets/GoodCatMidWalk.png"));
		} catch (Exception e) {e.printStackTrace();}
		if(!disabled)
			new HPBar();
		Platformer.game.getHandeler().add(this, true);
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(visible)
		{
			if(walking && !sliding)
			{
				if(walkTimer.timeUp())
				{
					if(frame < 1)
						frame++;
					else 
						frame = 0;
					walkTimer = new Timer(.2);
				}
				switch(frame)
				{
					case 0:
						if(facing) {
							g.drawImage(image,(int)x,(int)y, w, h, null);
						}
						else {
							g.drawImage(image,(int)x+w,(int)y, -w, h, null);
						}
						break;
					case 1:
						if(facing) {
							g.drawImage(walkImage,(int)x,(int)y, w, h, null);
						}
						else {
							g.drawImage(walkImage,(int)x+w,(int)y, -w, h, null);
						}
						break;
				}
			}
			else
			{
				if(facing) {
					g.drawImage(image,(int)x,(int)y, w, h, null);
				}
				else {
					g.drawImage(image,(int)x+w,(int)y, -w, h, null);
				}
			}
			if(!timer.timeUp())
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
		if(!disabled)
			move();
	}
	
	private void arrowMovement() {
		dx = 0;
		if(onGround && (Platformer.game.getInput().isKey(KeyEvent.VK_W) || Platformer.game.getInput().isKey(KeyEvent.VK_UP)))
		{
			dy = -jumpStrength;
		}
		
		if(Platformer.game.getInput().isKey(KeyEvent.VK_A) || Platformer.game.getInput().isKey(KeyEvent.VK_LEFT) || Platformer.game.getInput().isKey(KeyEvent.VK_D) || Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT))
		{
			walking = true;
			if(Platformer.game.getInput().isKey(KeyEvent.VK_D) || Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT))
			{
				facing = true;
				xAcceleration = (realdx < 1 && realdx >= 0) ? 1: .25f;
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_A) || Platformer.game.getInput().isKey(KeyEvent.VK_LEFT))
			{
				facing = false;
				xAcceleration = (realdx > -1 && realdx <= 0) ? -1: -.25f;
			}
		}
		else
		{
			walking = false;
			xAcceleration = realdx > 0 ? -.25f : realdx < 0 ? .25f : 0;
		}
		
		realdx += xAcceleration;
		
		if(dy>10)
			dy=10;
		if(dy<-10) 
			dy=-10;
		if(realdx > 5)
			realdx = 5f;
		if(realdx < -5)
			realdx = -5f;
		
		dx = (int)realdx;
		y += dy;
	}
	
	void updatePosition(int direction)
	{
		localX += direction*dx;
		if(localX > scrollDistance)
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= direction*this.dx;});
		else
			x += direction*dx;
	}
	
	void updatePosition(int direction, int amount)
	{
		localX += direction*amount;
		if(localX > scrollDistance)
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= direction*amount;});
		else
			x += direction*amount;		
	}
	
	private void updatePositionFinal(int direction)
	{
		localX += direction*dx;
		if(localX > scrollDistance)
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= direction*this.dx;});
		else
			x += direction*dx;
		GameObject o = this.hits();
		if(o != null && (o instanceof Platform || o instanceof Box))
		{
			double changeX;
			if(dx < 0)
				changeX = (o.x+o.w-x);
			else if(dx > 0)
				changeX = (o.x-x-w);
			else 
				changeX = 0;
			localX += changeX;
			if(localX > scrollDistance)
				Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= changeX;});
			else
				x += changeX;
		}
	}
	
	private void updatePositionFinal(int direction, int amount)
	{
		localX += direction*amount;
		if(localX > scrollDistance)
			Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= direction*amount;});
		else
			x += direction*amount;
		GameObject o = this.hits();
		if(o != null && (o instanceof Platform || o instanceof Box))
		{
			double changeX;
			if(direction*amount < 0)
				changeX = (o.x+o.w-x);
			else if(direction*amount > 0)
				changeX = (o.x-x-w);
			else 
				changeX = 0;
			localX += changeX;
			if(localX > scrollDistance)
				Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= changeX;});
			else
				x += changeX;
		}
	}
	
	private void collisionEffects(GameObject o)
	{
		if(o instanceof DangerThing)
		{
			damage(o);
			if(o instanceof Net)
				Platformer.game.getHandeler().remove(o);
		}
		
		if(o instanceof Portal)
		{
			y -= dy;
			int move = (int)(((Portal)o).oPortal.x+((Portal)o).oPortal.w/2-this.w/2)-(int)x;
			this.y = (((Portal)o).oPortal.y-this.h);
			dy = -dy;
			y += dy;
//			if(Platformer.level.stage.equals(Level.Stage.INFINITE))
//				Platformer.level.update(localX+move, y);
			updatePositionFinal(1, move);
		}
		
		if(o instanceof LevelPortal){
			((LevelPortal) o).updateStage();
		}
		
		if(o instanceof Box)
		{
			if(healTimer.timeUp())
			{
				if(this.HP <= maxHP-5)
				{
					this.HP += 5;
					((Box)o).heal -= 5;
					if(((Box)o).heal <= 0)
						Platformer.game.getHandeler().remove(o);
				}
				healTimer  = new Timer(1);
			}
		}
	}
	
	private void collisionJumps(GameObject o) {
		updatePosition(1);
		
		o = this.hits();
		
		collisionEffects(o);
		
		if(o instanceof Entity && !(o instanceof Projectile))
		{
			if(realdx > 0)
				updatePosition(1, (int)(o.x-x-w));
			else if(realdx < 0)
				updatePosition(1, (int)(o.x+o.w-x));
			realdx = 0;
		}
		else if(o instanceof Platform)
		{
			float temp = realdx;
			realdx = 0;
			if(((Platformer.game.getInput().isKey(KeyEvent.VK_D) || Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT))) || ((Platformer.game.getInput().isKey(KeyEvent.VK_A) || Platformer.game.getInput().isKey(KeyEvent.VK_LEFT))))
			{
				sliding = true;
				if(onGround == false && wallJump && o != lastWall && (Platformer.game.getInput().isKey(KeyEvent.VK_W) || Platformer.game.getInput().isKey(KeyEvent.VK_UP)))
				{
					wallJump = false;
					dy = -jumpStrength;
					realdx = (temp > 0) ? -3:3;
					sliding = false;
					waitTime = System.currentTimeMillis()+150;
					lastWall = (Platform)o;
				}
			}
			if(temp > 0 && o.x <= x+w)
				updatePosition(1, (int)(o.x-x-w));
			else if(temp < 0 && x <= o.x+o.w)
				updatePosition(1, (int)(o.x+o.w-x));
		}
		else {
			if(waitTime <= System.currentTimeMillis())
				wallJump = true;
			if(onGround)
				lastWall = null;
			sliding = false;
		}
	}
	
	public void move()
	{
		arrowMovement();
		
		GameObject o = this.hits();
		
		collisionEffects(o);
		
		o = this.hits();
		if(o != null && !(o instanceof Projectile))
		{
			if(dy > 0 && o.y <= y+h) {
				dy = 0;
				y = o.y-h;
				onGround = true;
			}
			if(dy < 0 && y <= o.y+o.h) {
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
		
		collisionJumps(o);
		
		if(x < 0) {
			x = 0;
			localX = 0;
		}
		
		if(y > Platformer.game.getHeight()) {
			restart();
		}
		
		Platformer.updateDistance((localX+30)/w);
		
		if(Platformer.level != null && Platformer.level.stage.equals(Level.Stage.INFINITE))
				Platformer.level.update(localX+30, y);
	}
	
	void damage(GameObject o) {
		if(timer.timeUp())
		{
			this.HP -= ((DangerThing)o).damage;
			timer = new Timer((double)invincibilityFrames/Platformer.game.getFps());
		}
		if(HP <= 0)
		{
			restart();
		}
	}
	private void restart() {
		try {
			if(Platformer.level.stage.equals(Level.Stage.INFINITE))
				Platformer.screen.addLeaderboard((localX+30)/w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Platformer.game.stop();
		Platformer.screen.updateState(State.TITLE);
	}
	public int getHP() {
		return HP;
	}
	
	@Override
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25;
	}
}
