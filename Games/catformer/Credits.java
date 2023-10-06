package catformer;

import java.util.Queue;
import java.awt.Graphics;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

import gameEngine.GameObject;
import gameEngine.Text;

public class Credits extends GameObject {
	Queue<Text> credits;
	public Credits() {
		credits = new LinkedList<Text>();
		x = Platformer.game.getWidth()/2;
		dy = 1;
		try{make();}catch(Exception e) {e.printStackTrace();}
		Platformer.game.getHandeler().add(this, false);
	}
	private void make() throws IOException {
		Queue<String> strs = new LinkedList<String>();
		Scanner s = new Scanner(new File("C:\\Users\\alexa\\OneDrive\\Documents\\GitHub\\java-graphics-game-engine\\Games\\catformer\\assets\\credits.txt"));
		while(s.hasNextLine())
			strs.add(s.nextLine());
		int i = 0;
		while(strs.size() > 0)
		{
			credits.add(new Text(strs.remove(), (int)x, Platformer.game.getHeight()+i*50, 20));
			i++;
		}
	}
	@Override
	public void move() {
		super.move();
		boolean temp = false;
		for(Text t: credits)
		{
			if(t.y < 0)
				temp = true;
			t.y -= dy;
		}
		if(temp)
		{
			Platformer.game.getHandeler().remove(credits.poll());
		}
	}
}
