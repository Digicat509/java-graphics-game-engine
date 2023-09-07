package catformer;

import java.awt.Color;
import java.awt.Graphics;
import gameEngine.GameObject;

public class Portal extends GameObject{
	public int connection;
	
	public Portal(int x, int y, int c) {
		this.x = x;
		this.y = y;
		w = 75;
		h = 35;
		connection = c;
		Platformer.game.getHandeler().add(this, true);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, w, h);
	}
}
