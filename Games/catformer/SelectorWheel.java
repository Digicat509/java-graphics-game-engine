package catformer;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import catformer.Screen.EditTool;
import gameEngine.GameObject;

public class SelectorWheel extends GameObject {
	Image[] sprites = new Image[6];
	Image[][] selectionSprites = new Image[6][7];
	Image currImage;
	EditTool current;
	public SelectorWheel(int x, int y)
	{
		super(3, "assets/SelectorWheel.png");
		for(int i = 1; i <= sprites.length; i++)
		{
			try {
				sprites[i-1] = ImageIO.read(getClass().getResource("assets/SelectorWheel"+i+".png"));
			} catch (IOException e) {
				System.out.println("No Image for "+getImage()+" "+this.getClass()+"!!!");
			}
		}
		loadSelectionImages();
		w = 200;
		h = 200;
		this.x = x-w/2;
		this.y = y-h/2;
		currImage = getImage();
		current = Platformer.screen.et;
		Platformer.game.getHandeler().add(this, false);
	}
	
	private void loadSelectionImages() {
		try {
			selectionSprites[0][0] = ImageIO.read(getClass().getResource("assets/GoodCat.png"));
			selectionSprites[0][1] = ImageIO.read(getClass().getResource("assets/WindowGratePersonTile.png"));
			selectionSprites[0][2] = ImageIO.read(getClass().getResource("assets/TestTile.png"));
			selectionSprites[0][3] = ImageIO.read(getClass().getResource("assets/Dog.png"));
			selectionSprites[0][4] = ImageIO.read(getClass().getResource("assets/Portal.png"));
			selectionSprites[0][5] = ImageIO.read(getClass().getResource("assets/Eraser.png"));
			selectionSprites[4][0] = ImageIO.read(getClass().getResource("assets/RollyPolly.png"));
			selectionSprites[4][1] = ImageIO.read(getClass().getResource("assets/Beetle.png"));
			selectionSprites[4][2] = ImageIO.read(getClass().getResource("assets/AnimalControl.png"));
			selectionSprites[4][3] = ImageIO.read(getClass().getResource("assets/Dog.png"));
			selectionSprites[4][4] = ImageIO.read(getClass().getResource("assets/Granny.png"));
			selectionSprites[4][5] = ImageIO.read(getClass().getResource("assets/back.png"));
			selectionSprites[5][0] = ImageIO.read(getClass().getResource("assets/Spikes.png"));
			selectionSprites[5][1] = ImageIO.read(getClass().getResource("assets/Portal.png"));
			selectionSprites[5][2] = ImageIO.read(getClass().getResource("assets/Box.png"));
			selectionSprites[5][3] = ImageIO.read(getClass().getResource("assets/MiddlePipe.png"));
			selectionSprites[5][5] = ImageIO.read(getClass().getResource("assets/back.png"));
		} catch (IOException e) {
			System.out.println("No Image for "+getImage()+" "+this.getClass()+"!!!");
		}
	}

	@Override
	public void draw(Graphics g) {
		if(visible) {
			g.drawImage(currImage, (int)x, (int)y, (int)w, (int)h, null);
			for(int i = 0; i < selectionSprites[0].length; i++)
			{
				if(current != null && selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i] != null)
				{
					int w = selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i].getWidth(null) > 30 ? (int)(selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i].getWidth(null)*.4) : (int)(selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i].getWidth(null)*.6);
					int h = selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i].getHeight(null) > 30 ? (int)(selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i].getHeight(null)*.4) : (int)(selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i].getHeight(null)*.6);
					int x = (int)((this.x+this.w/2)+this.w/3*Math.cos((Math.PI/3)*(i+1)-((Math.PI*2)/3)))-w/2;
					int y = (int)((this.y+this.h/2)+this.h/3*Math.sin((Math.PI/3)*(i+1)-((Math.PI*2)/3)))-h/2;
					g.drawImage(selectionSprites[current != null && current.hasChildren()?current.getIndex()+1:0][i], x, y, w, h, null);
				}
				else if(current == null&& selectionSprites[0][i] != null) {
					int w = selectionSprites[0][i].getWidth(null) > 30 ? (int)(selectionSprites[0][i].getWidth(null)*.4) : (int)(selectionSprites[0][i].getWidth(null)*.6);
					int h = selectionSprites[0][i].getHeight(null) > 30 ? (int)(selectionSprites[0][i].getHeight(null)*.4) : (int)(selectionSprites[0][i].getHeight(null)*.6);
					int x = (int)((this.x+this.w/2)+this.w/3*Math.cos((Math.PI/3)*(i+1)-((Math.PI*2)/3)))-w/2;
					int y = (int)((this.y+this.h/2)+this.h/3*Math.sin((Math.PI/3)*(i+1)-((Math.PI*2)/3)))-h/2;
					g.drawImage(selectionSprites[0][i], x, y, w, h, null);
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
			currImage = getImage();
		}
	}
	public void selectFinal(int mouseX, int mouseY) {
		int xd = (int)(mouseX-(x+w/2));
		int yd = (int)(mouseY-(y+h/2));
		if(Math.sqrt(Math.pow(xd, 2)+Math.pow(yd, 2)) > this.w/5)
		{
			int pos = 6-(int)((Math.toDegrees(Math.atan2(xd, yd)+Math.PI)/(60)))-1;
			pos = pos > 0 ? pos : 0;
			Platformer.screen.et = EditTool.choseTool(pos, current);
			current = EditTool.choseTool(pos, current);
			if(current == EditTool.BACK) {
				Platformer.screen.et = EditTool.BLOCK;
				current = null;
			}
		}
	}
}
