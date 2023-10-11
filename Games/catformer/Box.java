package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Box extends GameObject {
	int heal;
	public Box(int x, int y)
	{
		super(1);
		this.x = x;
		this.y = y;
		w = 24;
		h = 16;
		heal = 50;
		Platformer.game.getHandeler().add(this, true);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(115, 72, 2));
		g.fillRect((int)x, (int)y, w, h);
	}
}
