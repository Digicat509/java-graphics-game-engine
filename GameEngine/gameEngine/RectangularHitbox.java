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
		System.out.println("check hits");//happening
		if(rect.intersects(other.getBounds()))
			System.out.println("hiting???");//not happening
		return other != null ? this.rect.intersects(other.getBounds()): false;
	}

	@Override
	public boolean contains(Hitbox other) {
		return other != null ? this.rect.contains(other.getBounds()): false;

	}

	@Override
	public Rectangle2D getBounds() {
		return rect;
	}

}
