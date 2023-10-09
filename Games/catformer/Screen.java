package catformer;

import java.awt.event.*;

//import catformer.Platformer.STATE;
import gameEngine.GameEngine;

public class Screen extends MouseAdapter{
	GameEngine game;
	public Screen(GameEngine ge) {
		game = ge;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx= e.getX();
		int my= e.getY();
		
		
		
		if(GameEngine.state == GameEngine.state.TITLE) {
			//GameEngine.stopPlay();
			System.out.print("ahhhhhh");
		}
	}
}
