package catformer;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import gameEngine.Button;
//import catformer.Platformer.STATE;
import gameEngine.GameEngine;
import gameEngine.GameEngine.State;
import gameEngine.GameObject;

public class Screen extends GameObject{
	GameEngine game;
	ArrayList<Button> list = new ArrayList<Button>();
	public Screen(GameEngine ge) {
		game = ge;
		
		Platformer.game.getHandeler().add((GameObject)this, false);
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
	public void changeState(State state)
	{
		Platformer.game.getHandeler().clear();
		if(state == Platformer.game.state.TITLE) {
			list = new ArrayList<Button>();
			list.add(new Button("Hello", 200, 200, 30, 40, () -> {this.changeState(Platformer.game.state.PLAYING);}));
			list.add(new Button("Bye", 300, 30, 30, 40, () -> {this.changeState(Platformer.game.state.PLAYING);}));
		}
		if(state == Platformer.game.state.PLAYING)
		{
			list = new ArrayList<Button>();
			Platformer.start();
		}
	}
	//local variable state should be in form GameEngine.State.______
	public void updateState(State state) {
		Platformer.game.state = state;
		if(state == Platformer.game.state.TITLE) {
			list = new ArrayList<Button>();
			list.add(new Button("Hello", 200, 200, 30, 40, () -> {this.changeState(Platformer.game.state.PLAYING);}));
			list.add(new Button("Bye", 300, 30, 30, 40, () -> {this.changeState(Platformer.game.state.PLAYING);}));
		}
		if(state == Platformer.game.state.PLAYING)
		{
			list = new ArrayList<Button>();
			Platformer.start();
		}
	}
	
	public void draw(Graphics g) {
		if(Platformer.game.getInput().isMouseClicked()) {
			int mx= Platformer.game.getInput().getMouseX();
			int my= Platformer.game.getInput().getMouseY();
			System.out.println(mx+", "+my);
			for(Button b: list)
			{
				b.clickBox(mx, my);
				
			}
			//loop through btn list and check which ones are pressed
		}
		
		//System.out.print(Platformer.game.state);
		
		if(Platformer.game.state == Platformer.game.state.PLAYING)
			Platformer.start();
		
	}
}
