package catformer;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

//import catformer.Platformer.STATE;
import gameEngine.GameEngine;
import gameEngine.GameEngine.State;
import gameEngine.GameObject;

public class Screen extends GameObject{
	GameEngine game;
	ArrayList<Button> list = new ArrayList<Button>();
	public Screen(GameEngine ge) {
		game = ge;
		
		Platformer.game.getHandeler().add(this, false);
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressssssssss");
		int mx= Platformer.game.getInput().getMouseX();
		int my= Platformer.game.getInput().getMouseY();
		
		System.out.println(mx+" "+my);
		
		for(Button b:list) {
			b.clickBox(mx, my);
		}
		
//		if(GameEngine.state == GameEngine.state.TITLE) {
//			//GameEngine.stopPlay();
//			System.out.print("ahhhhhh");
//			new Button("Hello", 200, 200, 200, 40);
//		}
	}
	
	//local variable state should be in form GameEngine.State.______
	public void updateState(State state) {
		GameEngine.state = state;
		Platformer.game.getHandeler().clear();
		list.clear();
		
		if(GameEngine.state == GameEngine.state.TITLE) {
			System.out.println("title");
			list.add(new Button("Hello", 200, 200, 30, 40));
			list.add(new Button("Bye", 300, 30, 30, 40));
		}
	}
	
	public void draw(Graphics g) {
		int mx= Platformer.game.getInput().getMouseX();
		int my= Platformer.game.getInput().getMouseY();
		
		
	}
}
