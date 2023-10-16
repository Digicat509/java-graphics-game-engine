package catformer;

import java.util.Queue;
import java.awt.Graphics;
import java.io.IOException;
import java.io.File;
import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;

import gameEngine.GameEngine.State;
import gameEngine.GameImage;
import gameEngine.GameObject;
import gameEngine.Text;

public class Credits extends GameObject {
	ArrayList<GameObject> credits;
	public Credits() {
		credits = new ArrayList<GameObject>();
		x = Platformer.game.getWidth()/2;
		dy = 1;
		try{make();}catch(Exception e) {e.printStackTrace();}
		Platformer.game.getHandeler().add(this, false);
	}
	private void make() throws IOException {
		Queue<String> strs = new LinkedList<String>();
		Scanner s = new Scanner(new BufferedInputStream(getClass().getResource("assets/credits.txt").openStream()));
		while(s.hasNextLine())
			strs.add(s.nextLine());
		int i = 0;
		while(strs.size() > 0)
		{
			credits.add(new Text(strs.remove(), (int)x, Platformer.game.getHeight()+i*50, 20));
			i++;
		}
		credits.add(new GameImage(getClass().getResource("assets/GoodCat.png"), (int)x-350, Platformer.game.getHeight()+200, 4));
		credits.add(new GameImage(getClass().getResource("assets/Candy.png"), (int)x+200, Platformer.game.getHeight()+300, 4));
		credits.add(new GameImage(getClass().getResource("assets/Dog.png"), (int)x+300, Platformer.game.getHeight()+500, 4));
		credits.add(new GameImage(getClass().getResource("assets/RollyPolly.png"), (int)x-350, Platformer.game.getHeight()+550, 4));
		credits.add(new GameImage(getClass().getResource("assets/AnimalControl.png"), (int)x+250, Platformer.game.getHeight()+950, 4));
		credits.add(new GameImage(getClass().getResource("assets/Beetle.png"), (int)x+200, Platformer.game.getHeight()+700, 4));
		credits.add(new GameImage(getClass().getResource("assets/WindowTile.png"), (int)x-400, Platformer.game.getHeight()+900, 4));
		credits.add(new GameImage(getClass().getResource("assets/WindowGratePersonTile.png"), (int)x+300, Platformer.game.getHeight()+1200, 4));
		credits.add(new GameImage(getClass().getResource("assets/Granny.png"), (int)x-250, Platformer.game.getHeight()+1400, 4));
	}
	@Override
	public void move() {
		super.move();
		GameObject temp = null;
		for(GameObject t: credits)
		{
			if(t.y+t.h < 0)
				temp = t;
			t.y -= dy;
		}
		if(temp != null)
		{
			credits.remove(temp);
			Platformer.game.getHandeler().remove(temp);
			if(credits.size() == 0) {
				Platformer.screen.updateState(State.TITLE);
			}
			temp = null;
		}
	}
}