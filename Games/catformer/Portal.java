package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameEngine.GameObject;

public class Portal extends GameObject{
	float ox, oy;
	public Portal(int x, int y, int ox, int oy) {
		this.x = x;
		this.y = y;
		this.ox = ox-x;
		this.oy = oy-y;
		w = 75;
		h = 35;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, w, h);
		g.fillOval((int)(x+ox), (int)(y+oy), w, h);
	}
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, w, h);
	}
}
