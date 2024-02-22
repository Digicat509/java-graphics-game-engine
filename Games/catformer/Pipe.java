package catformer;

import java.awt.*;

import javax.imageio.ImageIO;

public class Pipe extends Platform {
	Image leftPipe, rightPipe, middlePipe;
	public Pipe(int x, int y, int w) {
		super(x, y, w, 25, 0);
		try {
			leftPipe = ImageIO.read(getClass().getResource("assets/LeftPipe.png"));
			rightPipe = ImageIO.read(getClass().getResource("assets/RightPipe.png"));
			middlePipe = ImageIO.read(getClass().getResource("assets/MiddlePipe.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	public Pipe(int x, int y, int w, int theta) {
		super(x, y, w, 10, theta);
		try {
			leftPipe = ImageIO.read(getClass().getResource("assets/LeftPipe.png"));
			rightPipe = ImageIO.read(getClass().getResource("assets/RightPipe.png"));
			middlePipe = ImageIO.read(getClass().getResource("assets/MiddlePipe.png"));
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		if(w % 25 == 0 && w >= 75) {
			for(int i = 0; i < w; i+=25) {
				if(i == 0)
					g2d.drawImage(leftPipe, (int)x+i, (int)y, 25, h, null);
				else if(i < w-25)
					g2d.drawImage(middlePipe, (int)x+i, (int)y, 25, h, null);
				else 
					g2d.drawImage(rightPipe, (int)x+i, (int)y, 25, h, null);
			}
		}
		else {
			for(int i = 0; i < w; i+=25) {
				if(i < w-16)
					g2d.drawImage(middlePipe, (int)x+i, (int)y, 25, h, null);
				else 
					g2d.drawImage(middlePipe, (int)x+i, (int)y, w-i, h, null);
			}
		}
	}
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25+", "+w/25;
	}
}
