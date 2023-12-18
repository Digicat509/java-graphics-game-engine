package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;

import gameEngine.GameObject;

public class Portal extends GameObject{
	Portal oPortal;
	public Portal(int x, int y, int ox, int oy) {
		super(1, "assets/Portal.png");
		this.x = x;
		this.y = y;
		w = 75;
		h = 35;
		oPortal = new Portal(ox, oy, this);
		Platformer.game.getHandeler().add(this, true);
	}
	
	private Portal(int x, int y, Portal p) {
		super(1, "assets/Portal.png");
		this.x = x;
		this.y = y;
		w = 75;
		h = 35;
		oPortal = p;
		Platformer.game.getHandeler().add(this, true);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Block ? x == ((Block)obj).x && y == ((Block)obj).y: super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	@Override
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25+", "+oPortal.x/25+", "+oPortal.y/25;
	}
}
