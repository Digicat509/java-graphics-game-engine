package catformer;

import java.awt.Color;
import java.awt.Graphics;

import gameEngine.GameObject;

public class HPBar extends GameObject {
	public HPBar()
	{
		x = 30;
		y = 20;
		w = 200;
		h = 30;
		Platformer.game.getHandeler().add(this, false);
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, w, h);
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, (w*Platformer.player.getHP())/Platformer.player.maxHP, h);
	}
}
