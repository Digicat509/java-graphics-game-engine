package catformer;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import catformer.Screen.EditTool;
import gameEngine.GameObject;

public class SelectorWheel extends GameObject {
	Image[] sprites = new Image[6];
	Image[] selectionSprites = new Image[6];
	Image currImage;
	public SelectorWheel(int x, int y)
	{
		super(3, "assets/SelectorWheel.png");
		for(int i = 1; i <= sprites.length; i++)
		{
			try {
				sprites[i-1] = ImageIO.read(getClass().getResource("assets/SelectorWheel"+i+".png"));
			} catch (IOException e) {
				System.out.println("No Image for "+image+" "+this.getClass()+"!!!");
			}
		}
		loadSelectionImages();
		w = 200;
		h = 200;
		this.x = x-w/2;
		this.y = y-h/2;
		currImage = image;
		Platformer.game.getHandeler().add(this, false);
	}
	
	private void loadSelectionImages() {
		try {
			selectionSprites[0] = ImageIO.read(getClass().getResource("assets/GoodCat.png"));
			selectionSprites[1] = ImageIO.read(getClass().getResource("assets/WindowGratePersonTile.png"));
			selectionSprites[2] = ImageIO.read(getClass().getResource("assets/TestTile.png"));
			selectionSprites[3] = ImageIO.read(getClass().getResource("assets/Dog.png"));
			selectionSprites[4] = ImageIO.read(getClass().getResource("assets/Portal.png"));
		} catch (IOException e) {
			System.out.println("No Image for "+image+" "+this.getClass()+"!!!");
		}
	}

	@Override
	public void draw(Graphics g) {
		if(visible) {
			g.drawImage(currImage, (int)x, (int)y, (int)w, (int)h, null);
			for(int i = 0; i < selectionSprites.length; i++)
			{
				if(selectionSprites[i] != null)
				{
					int w = selectionSprites[i].getWidth(null) > 30 ? (int)(selectionSprites[i].getWidth(null)*.4) : (int)(selectionSprites[i].getWidth(null)*.6);
					int h = selectionSprites[i].getHeight(null) > 30 ? (int)(selectionSprites[i].getHeight(null)*.4) : (int)(selectionSprites[i].getHeight(null)*.6);
					int x = (int)((this.x+this.w/2)+this.w/3*Math.cos((Math.PI/3)*(i+1)-((Math.PI*2)/3)))-w/2;
					int y = (int)((this.y+this.h/2)+this.h/3*Math.sin((Math.PI/3)*(i+1)-((Math.PI*2)/3)))-h/2;
					g.drawImage(selectionSprites[i], x, y, w, h, null);
				}
			}
		}
	}
	
	public void recenter(int mouseX, int mouseY) {
		this.x = mouseX-w/2;
		this.y = mouseY-h/2;
	}
	
	public void select(int mouseX, int mouseY) {
		int xd = (int)(mouseX-(x+w/2));
		int yd = (int)(mouseY-(y+h/2));
		if(Math.sqrt(Math.pow(xd, 2)+Math.pow(yd, 2)) > this.w/5)
		{
			int pos = 6-(int)((Math.toDegrees(Math.atan2(xd, yd)+Math.PI)/(60)))-1;
			pos = pos > 0 ? pos : 0;
			currImage = sprites[pos];
		}
		else
		{
			currImage = image;
		}
	}
	public void selectFinal(int mouseX, int mouseY) {
		int xd = (int)(mouseX-(x+w/2));
		int yd = (int)(mouseY-(y+h/2));
		if(Math.sqrt(Math.pow(xd, 2)+Math.pow(yd, 2)) > this.w/5)
		{
			int pos = 6-(int)((Math.toDegrees(Math.atan2(xd, yd)+Math.PI)/(60)))-1;
			pos = pos > 0 ? pos : 0;
			Platformer.screen.et = EditTool.choseTool(pos);
		}
	}
}
