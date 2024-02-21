package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class Spikes extends Hazard{

	public Spikes(int x, int y, int w) {
		super(x, y, w, 24, 40);
		try {
			this.setImage(ImageIO.read(getClass().getResource("assets/Spikes.png")));
		} catch (Exception e) {e.printStackTrace();}
	}
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		for(int i = 0; i < w; i+=16) {
			if(i < w-16)
				g2d.drawImage(getImage(), (int)x+i, (int)y, 16, h, null);
			else 
				g2d.drawImage(getImage(), (int)x+i, (int)y, w-i, h, null);
		}
	}
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25+", "+w/25;
	}
}
