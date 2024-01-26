package catformer;

import catformer.Level.Stage;
import gameEngine.GameEngine;
import gameEngine.GameEngine.State;
import gameEngine.Text;
import gameEngine.GameData;

public class Platformer {
	static GameEngine game;
	static GameData gameData;
	static Level level;
	static Player player;
	static Text text;
	static Sound sound;
	static int distance;
	public static final float GRAVITY = .5f;
	static Screen screen;
	static int levelNum = 0;
	
	public static void main(String[] args)
	{
		gameData = new GameData();
		Platformer platformer = new Platformer();
	}
	
	public Platformer()
	{
		game = new GameEngine();
		game.setTitle("Catformer!");
		game.setWidth((int)(game.getWidth()*1.5));
		game.setScale(3f);
		game.title();
		sound = new Sound();
		screen = new Screen();
		screen.updateState(State.TITLE);
		game.start();
		//game.state = State.LOADING;
	}
	public static void start(Stage stage)
	{
		game.getHandeler().clear();
		if(stage != null)
		{
			if(stage == Stage.CUSTOM)
				screen.inputText = true;
			level = new Level(stage);
		}
		else
		{
			if(Stage.get(levelNum) == Stage.CUSTOM)
				screen.inputText = true;
			level = new Level(Stage.get(levelNum));
		}
	}
	
	public static void updateDistance(int d)
	{
		distance = d;
		if(text != null)
			text.setText(""+distance+"m");
	}
}
