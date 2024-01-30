package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import gameEngine.GameObject;

public class Granny extends Enemy {

	private long delay = 0;
	private long frameTimer = 0;
	private static final int RANGE = 400;
	public static int speed = 3;
	private int frame;
	private Image frame1, frame2;
	private boolean musicOn;
	
	public Granny(int x, int y, boolean disabled) {
		super(x, y, speed, 20, disabled);
		w = 22;
		h = 44;
		try {
			this.image = ImageIO.read(getClass().getResource("assets/Granny.png"));
		} catch (Exception e) {e.printStackTrace();}
		try {
			this.frame1 = ImageIO.read(getClass().getResource("assets/GrannyThrow1.png"));
		} catch (Exception e) {e.printStackTrace();}
		try {
			this.frame2 = ImageIO.read(getClass().getResource("assets/GrannyThrow2.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void draw(Graphics g) {
		switch(frame)
		{
			case 0:
				if(Platformer.player != null && Platformer.player.x < x+w)
					g.drawImage(image, (int)x, (int)y, w, h-1, null);
				else
					g.drawImage(image, (int)x+w, (int)y, -w, h-1, null);
				break;
			case 1:
				if(Platformer.player != null && Platformer.player.x < x+w)
					g.drawImage(frame1, (int)x, (int)y, w, h-1, null);
				else
					g.drawImage(frame1, (int)x+w, (int)y, -w, h-1, null);
				break;
			case 2:
				if(Platformer.player != null && Platformer.player.x < x+w)
					g.drawImage(frame2, (int)x, (int)y, w, h-1, null);
				else
					g.drawImage(frame2, (int)x+w, (int)y, -w, h-1, null);
				break;
			case 3:
				if(Platformer.player != null && Platformer.player.x < x+w)
					g.drawImage(frame1, (int)x, (int)y, w, h-1, null);
				else
					g.drawImage(frame1, (int)x+w, (int)y, -w, h-1, null);
				break;
		}
		if(!disabled)
			move();
	}
	
	@Override
	public void move() {
		float yVel = 0;
		int xDist = 0;
		int yDist = 0;
		if(Platformer.player != null && x-Platformer.player.x-Platformer.player.w < Platformer.game.getWidth()-400-RANGE && !musicOn)
		{
			Platformer.sound.audio.stop();
			Platformer.sound.bossAudio.setFramePosition(0);
			Platformer.sound.bossAudio.loop(Clip.LOOP_CONTINUOUSLY);
			musicOn = true;
		}
		if(Platformer.player != null && delay - 200 < System.currentTimeMillis())
		{
			xDist = (int)(Platformer.player.x-x);
			yDist = (int)(Platformer.player.y-y);
			double framesTillImpact = (double)Math.abs(xDist)/5;
			yVel = (float)((yDist-.5*.5*Math.pow(framesTillImpact, 2))/framesTillImpact);
		}
		if(Platformer.player != null && Platformer.player.x < x-Platformer.player.w && delay < System.currentTimeMillis() && Math.abs(xDist) < RANGE)
		{
			new GrannyCandy((int)(x-10), (int)(y+10), -5, yVel);
			delay = System.currentTimeMillis()+1000;
			frameTimer = System.currentTimeMillis()+333;
		}
		if(Platformer.player != null && Platformer.player.x > x+w && delay < System.currentTimeMillis() && Math.abs(xDist) < Platformer.player.scrollDistance+35+w)
		{
			new GrannyCandy((int)(x-10), (int)(y+10), 5, yVel);
			delay = System.currentTimeMillis()+1000;
			frameTimer = System.currentTimeMillis()+333;
			frame = 2;
		}
		if(frameTimer != 0 && frameTimer < System.currentTimeMillis())
		{
			if(frame < 3)
			{
				frame++;
				frameTimer = System.currentTimeMillis()+333;
			}
			else
			{
				frame = 0;
				frameTimer = 0;
			}
		}
		y+=dy;
		GameObject o = this.hits();
		
		if(o != null && !(o instanceof Enemy))
		{
			x+= dx;
			if(x < o.x) {
				x = o.x;
				dx*=-1;
			}
			else if((x+w) > (o.x+o.w)){
				x = o.x+o.w-w;
				dx*=-1;
			}
			dy = 0;
			if(o.y>= this.y) {
				y = o.y-h;
			}
		}
		else if(o == null)
		{
			dy += Platformer.GRAVITY;
		}
		
		push();
	}
}
