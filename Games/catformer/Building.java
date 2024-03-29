package catformer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Building extends Platform {
	ArrayList<ArrayList<BuildingBlock>> arr = new ArrayList<ArrayList<BuildingBlock>>();
	
	public Building(int x, int y, int w) {
		super(x, y, w, Platformer.game.getHeight()-y, 0);
		try {
			setImage(ImageIO.read(getClass().getResource("assets/TestTile.png")));
		} catch (IOException e) {}
		for(int i = 0; i < w; i += 50) {
			arr.add(new ArrayList<BuildingBlock>());
			for(int j = 0; j <= h; j += 50) {
				double r = Math.random();
				if(r > .4)
					arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/TestTile.png"));
				else if(r > .13)
					arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/WindowTile.png"));
				else if(r > .08)
					arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/WindowGrateTile.png"));
				else
					arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/WindowGratePersonTile.png"));
			}
		}
	}
	@Override
	public void draw(Graphics g)
	{
		updateSize(w);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.red);
		for(int i = 0; i < w; i += 50) {
			for(int j = 0; j <= h; j += 50) {
				arr.get(i/50).get(j/50).x = x+i;
				arr.get(i/50).get(j/50).y = y+j;
				arr.get(i/50).get(j/50).draw(g);
			}
		}
//		for(int i = 0; i < w; i += 10) //draws 10X10 rects change this to tiles when tile png is made
//			for(int j = 0; j <= h; j += 10) {
////				if(Math.random()>0.5) {
////					g2d.setColor(Color.red);
////				}
////				else {
////					g2d.setColor(Color.blue);
////				}
//				//g2d.drawImage(image, (int)x+i, (int)y+j, 50, 50, null);
//				g2d.fillRect((int)x+i, (int)y+j, 10, 10);
//			}
	}
	private void updateSize(int w) {
		if(w != 0 && w%50 == 0) {
			for(int i = arr.size()*50; i < w; i += 50) {
				arr.add(new ArrayList<BuildingBlock>());
				for(int j = 0; j <= h; j += 50) {
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/TestTile.png"));
					else if(r > .13)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/WindowTile.png"));
					else if(r > .08)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/WindowGrateTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/WindowGratePersonTile.png"));
				}
			}
		}
	}
	public String toString() {
		return ""+this.getClass()+" "+x/25+", "+y/25+", "+w/25;
	}
}
