package gameEngine;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

//a hitbox that is a rectangle shape
public class RectangularHitbox extends Hitbox {
	int w, h;
	private Rectangle2D rect;
	public RectangularHitbox(int x, int y, int w, int h) {
		super(x, y);
		this.w = w;
		this.h = h;
		this.rect = new Rectangle(x, y, w, h);
	}

	@Override
	public boolean hits(Hitbox other) {
		return other != null ? this.getBounds().intersects(other.getBounds()): false;
	}

	@Override
	public boolean contains(Hitbox other) {
		return other != null ? this.getBounds().contains(other.getBounds()): false;

	}
	
	public boolean contains(int x, int y) {
		return this.getBounds().contains(x, y);
	}

	@Override
	public Rectangle2D getBounds() {
		return rect;
	}

}
