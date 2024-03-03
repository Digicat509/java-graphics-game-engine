package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEngine.GameObject;
import gameEngine.Text;

public class TutorialBox extends GameObject {
	private String str;
	private int alpha;
	private Text text;
	private boolean increase;
	public TutorialBox(String str, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.str = str;
		this.w = w;
		this.h = h;
		alpha = 255;
		Platformer.game.getHandeler().add(this, true);
		text = new Text(str, new Rectangle((int)x, (int)y, w, h), Color.white);
	}
	@Override
	public void draw(Graphics g) {
		if(alpha <= 0)
			increase = true;
		if(alpha >= 255)
			increase = false;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0, 0, 0, alpha));
		text.setColor(new Color(255, 255, 255, alpha));
		g2d.fillRect((int)x, (int)y, w, h);
		alpha+=increase?3:-3;
	}
}
