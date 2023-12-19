package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class FloatingPlatform extends Platform {
	
	ArrayList<ArrayList<BuildingBlock>> arr = new ArrayList<ArrayList<BuildingBlock>>();
	
	public FloatingPlatform(int x, int y, int w, int h)
	{
		super(x, y, w, h, 0);
		try {
			image = ImageIO.read(getClass().getResource("assets/TestTile.png"));
		} catch (IOException e) {}
		for(int i = 0; i < w; i += 50) {
			arr.add(new ArrayList<BuildingBlock>());
			for(int j = 0; j < h; j += 50) {
				if(i == 0 && j == 0)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/LeftTopCornerSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/LeftTopCornerSuspendedTileWindow.png"));
				}
				else if(i == 0 && j == h-50)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/LeftBottomCornerSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/LeftBottomCornerSuspendedTileWindow.png"));
				}
				else if(i == w-50 && j == 0)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/RightTopCornerSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/RightTopCornerSuspendedTileWindow.png"));
				}
				else if(i == w-50 && j == h-50)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/RightBottomCornerSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/RightBottomCornerSuspendedTileWindow.png"));
				}
				else if(i == 0)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/LeftSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/LeftSuspendedTileWindow.png"));
				}
				else if(j == 0)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/TopSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/TopSuspendedTileWindow.png"));
				}
				else if(i == w-50)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/RightSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/RightSuspendedTileWindow.png"));
				}
				else if(j == h-50)
				{
					double r = Math.random();
					if(r > .4)
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/BottomSuspendedTile.png"));
					else
						arr.get(i/50).add(new BuildingBlock((int)x+i, (int)y+j, 50, 50, "assets/BottomSuspendedTileWindow.png"));
				}
				else
				{
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
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.red);
		for(int i = 0; i < w; i += 50) {
			for(int j = 0; j < h; j += 50) {
				arr.get(i/50).get(j/50).x = x+i;
				arr.get(i/50).get(j/50).y = y+j;
				arr.get(i/50).get(j/50).draw(g);
			}
		}
	}
}
