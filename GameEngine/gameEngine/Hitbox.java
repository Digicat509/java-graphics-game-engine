package gameEngine;

import java.awt.geom.Rectangle2D;

//the main class that should be extended by hitboxes
public abstract class Hitbox {
	int x, y;
	public Hitbox(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public abstract boolean hits(Hitbox other);
	public abstract boolean contains(Hitbox other);
	public abstract Rectangle2D getBounds();
}
