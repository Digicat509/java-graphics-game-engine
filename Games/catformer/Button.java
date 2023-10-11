package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameEngine.*;

public class Button extends GameObject{
	
	Text text;
	String val;
	Rectangle clickBox;
	
	public Button(String v, int x, int y, int w, int h) {
		val = v;
		text = new Text(val, x, y, 20);
		Platformer.game.getHandeler().add(this, false);
	}
	
	@Override
	public void draw(Graphics g) {
		//System.out.println("Rect");
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, w, h);
	}
	
	public boolean clickBox(int mx, int my) {
		clickBox = new Rectangle((int)x, (int)y, w, h);
		if(clickBox.contains(mx, my)) {
			System.out.println("TRUE");
			return true;
		}
			
		return false;
	}
}
