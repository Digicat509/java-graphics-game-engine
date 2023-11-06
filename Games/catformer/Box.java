package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class Box extends GameObject implements Entity{
	int heal;
	public Box(int x, int y)
	{
		super(1, "assets/Box.png");
		this.x = x;
		this.y = y;
		w = 30;
		h = 20;
		heal = 50;
		Platformer.game.getHandeler().add(this, true);
	}
}
