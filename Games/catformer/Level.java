package catformer;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import gameEngine.Text;
import gameEngine.GameEngine.State;

public class Level {
	public Stage stage;
	int currX = 0;
	private int drawCurrX = 0;
	int height;
	Grid grid;
	public Level(Stage stage)
	{
		this.stage = stage;
		if(stage == Stage.CUSTOM)
		{
			Platformer.screen.inputText = true;
		}
		if(stage == Stage.LEVEL5)
		{
			try {build("assets/level5.txt");}catch(Exception e) {e.printStackTrace();}
		}
		if(stage == Stage.LEVEL4)
		{
			try {build("assets/level4.txt");}catch(Exception e) {e.printStackTrace();}
		}
		if(stage == Stage.LEVEL3) {
			try {build("assets/level3.txt");}catch(Exception e) {e.printStackTrace();}
		}
		
		if(stage == Stage.LEVEL2) {
			try {build("assets/level2.txt");}catch(Exception e) {e.printStackTrace();}
		}
		
		if(stage == Stage.LEVEL1) {
			try {build("assets/level1.txt");}catch(Exception e) {e.printStackTrace();}
		}
		if(stage == Stage.EDIT)
		{
			grid = new Grid();
		}
		if(stage == Stage.TEST)
		{
			try {build("assets/test_level.txt");}catch(Exception e) {e.printStackTrace();}
		}
		if(stage == Stage.INFINITE)
		{
			Platformer.text = new Text(""+Platformer.distance+"m", Platformer.game.getWidth()-100, 30, 20);
			int width = (int)(Math.random()*3+1)*50;
			height = ((int)(Math.random()*300)+100);
			Platformer.player = new Player(30, height-50, false);
			new Building(0, height, width);
			currX += width;
			drawCurrX += width;
			Platformer.game.getHandeler().stopRender();
			Platformer.game.state = State.LOADING;
			generate(1);
			Platformer.game.getHandeler().startRender();
		}
	}
	private void generate() {
		int dist = drawCurrX+Platformer.game.getWidth();
		while(drawCurrX < dist)
		{
			int rand = (int)(Math.random()*100);
			int prob = (int)(Math.random()*40);
			int width = (int)(Math.random()*3+1)*50;
			height = height + ((int)(Math.random()*200)-100);
			while(height < 250)
				height = height + ((int)(Math.random()*100));
			while(height > Platformer.game.getHeight()-200)
				height = height - ((int)(Math.random()*100));
			if(prob >= 32) 
				new DogEnemy(drawCurrX+rand+width/2, height-21, false);
			else if(prob >= 28)
				new DumbDroneEnemy(drawCurrX+rand+width/2, height-16, false);
			else if(prob >= 24)
				new SmartDroneEnemy(drawCurrX+rand+width/2, height-16, false);
			else if (prob >= 22)
				new AnimalControlEnemy(drawCurrX+rand+width/2, height-46, false);
			else if(prob >= 21)
				new Box(drawCurrX+rand+width/2, height-19);
			else if(prob >= 13 && rand > 75 && height > 100)
				new Portal(drawCurrX+rand-75, height+30, drawCurrX+rand+150, height-130);
			new Building(drawCurrX+rand, height, width);
			currX += rand+width;
			drawCurrX += rand+width;
		}
	}
	private void generate(double screens) {
		int dist = drawCurrX+(int)(Platformer.game.getWidth()*screens);
		int width = (int)(Math.random()*3+1)*50;
		new Building(drawCurrX, height, width);
		currX += width;
		drawCurrX += width;
		while(drawCurrX < dist)
		{
			int rand = (int)(Math.random()*100);
			int prob = (int)(Math.random()*40);
			width = (int)(Math.random()*3+1)*50;
			height = height + ((int)(Math.random()*200)-100);
			while(height < 250)
				height = height + ((int)(Math.random()*100));
			while(height > Platformer.game.getHeight()-200)
				height = height - ((int)(Math.random()*100));
			if(prob >= 32) 
				new DogEnemy(drawCurrX+rand+width/2, height-21, false);
			else if(prob >= 28)
				new DumbDroneEnemy(drawCurrX+rand+width/2, height-16, false);
			else if(prob >= 24)
				new SmartDroneEnemy(drawCurrX+rand+width/2, height-16, false);
			else if (prob >= 22)
				new AnimalControlEnemy(drawCurrX+rand+width/2, height-46, false);
			else if(prob >= 21)
				new Box(drawCurrX+rand+width/2, height-19);
			else if(prob >= 13 && rand > 75 && height > 100)
				new Portal(drawCurrX+rand-75, height+30, drawCurrX+rand+150, height-130);
			new Building(drawCurrX+rand, height, width);
			currX += rand+width;
			drawCurrX += rand+width;
		}
	}
	public void update(float x, float y)
	{	
		if(currX < x+Platformer.game.getWidth())
		{
			drawCurrX = (int)Platformer.player.x+Platformer.game.getWidth();
			generate();
		}
	}
	public void build(String path) throws IOException
	{
		Scanner s;
		try {
			s = new Scanner((new BufferedInputStream(getClass().getResource(path).openStream())));
		}
		catch(Exception e) {
			s = new Scanner("");
		}
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			String name;
			String temp;
			Scanner ns = new Scanner(line);
			if(line.substring(0, 5).equals("class"))
			{
				name = ns.next()+" "+ns.next();
				temp = ns.nextLine().strip();
				System.out.println(name+" "+temp);
			}
			else
			{
				name = ns.next();
				temp = ns.nextLine().strip();
				System.out.println(name+" "+temp);
			}
			if(name.length() > 0 && temp.length() > 0)
			{
				String[] parameters = temp.matches("\\(.*\\)")? temp.substring(temp.indexOf("(")+1, temp.lastIndexOf(")")).split("\\s*,\\s*"): temp.split("\\s*,\\s*");
				for(int i = 0; i < parameters.length; i++)
					parameters[i] = parameters[i].strip();
				if(name.equalsIgnoreCase("block") || name.equalsIgnoreCase(""+Block.class))
				{
					new Block((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25);
				}
				else if(name.equalsIgnoreCase("text") || name.equalsIgnoreCase(""+Text.class))
				{
					if(parameters.length == 4)
						new Text(parameters[0], (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25, Integer.parseInt(parameters[3]));
					if(parameters.length == 3)
						new Text(parameters[0], (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("building")  || name.equalsIgnoreCase(""+Building.class))
				{
					new Building((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, Integer.parseInt(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("player")  || name.equalsIgnoreCase(""+Player.class))
				{
					Platformer.player = new Player((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, false);
				}
				else if(name.equalsIgnoreCase("dog enemy")  || name.equalsIgnoreCase("dogenemy") || name.equalsIgnoreCase(""+DogEnemy.class))
				{
					new DogEnemy((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, false);
				}
				else if(name.equalsIgnoreCase("smart drone enemy") || name.equalsIgnoreCase("smartdroneenemy") || name.equalsIgnoreCase(""+SmartDroneEnemy.class))
				{
					new SmartDroneEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("dumb drone enemy") || name.equalsIgnoreCase("dumbdroneenemy") || name.equalsIgnoreCase(""+DumbDroneEnemy.class))
				{
					new DumbDroneEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("granny")  || name.equalsIgnoreCase(""+Granny.class))
				{
					new Granny((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("animal control enemy") || name.equalsIgnoreCase("animalcontrolenemy") || name.equalsIgnoreCase(""+AnimalControlEnemy.class))
				{
					new AnimalControlEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("portal")  || name.equalsIgnoreCase(""+Portal.class))
				{
					new Portal((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25, (int)Double.parseDouble(parameters[3])*25);
				}
				else if(name.equalsIgnoreCase("spikes")  || name.equalsIgnoreCase(""+Spikes.class))
				{
					new Spikes((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("pipe")  || name.equalsIgnoreCase(""+Pipe.class))
				{
					new Pipe((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("box")  || name.equalsIgnoreCase(""+Box.class))
				{
					new Box((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]));
				}
				else if(name.equalsIgnoreCase("level portal") || name.equalsIgnoreCase("levelportal") || name.equalsIgnoreCase(""+LevelPortal.class))
				{
					new LevelPortal((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), Stage.get(parameters[2]), Boolean.parseBoolean(parameters[3]));
				}
				else if(name.equalsIgnoreCase("tutorial box") || name.equalsIgnoreCase("tutorialbox") || name.equalsIgnoreCase(""+LevelPortal.class))
				{
					new TutorialBox(parameters[0], (int)Double.parseDouble(parameters[1]), (int)Double.parseDouble(parameters[2]), (int)Double.parseDouble(parameters[3]), (int)Double.parseDouble(parameters[4]));
				}
				else if(name.equalsIgnoreCase("floating platform") || name.equalsIgnoreCase("floatingplatform") || name.equalsIgnoreCase(""+LevelPortal.class))
				{
					new FloatingPlatform((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), (int)Double.parseDouble(parameters[2]), (int)Double.parseDouble(parameters[3]));
				}
			}
		}
		s.close();
	}
	public void build(String path, boolean disabled) throws IOException
	{
		Scanner s;
		try {
			s = new Scanner((new BufferedInputStream(getClass().getResource(path).openStream())));
		}
		catch(Exception e) {
			e.printStackTrace();
			s = new Scanner("");
		}
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			String name;
			String temp;
			Scanner ns = new Scanner(line);
			if(line.substring(0, 5).equals("class"))
			{
				name = ns.next()+" "+ns.next();
				temp = ns.nextLine().strip();
				System.out.println(name+" "+temp);
			}
			else
			{
				name = ns.next();
				temp = ns.nextLine().strip();
				System.out.println(name+" "+temp);
			}
			if(name.length() > 0 && temp.length() > 0)
			{
				String[] parameters = temp.matches("\\(.*\\)")? temp.substring(temp.indexOf("(")+1, temp.lastIndexOf(")")).split("\\s*,\\s*"): temp.split("\\s*,\\s*");
				for(int i = 0; i < parameters.length; i++)
					parameters[i] = parameters[i].strip();
				if(name.equalsIgnoreCase("block") || name.equalsIgnoreCase(""+Block.class))
				{
					new Block((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25);
				}
				else if(name.equalsIgnoreCase("text") || name.equalsIgnoreCase(""+Text.class))
				{
					if(parameters.length == 4)
						new Text(parameters[0], (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25, Integer.parseInt(parameters[3]));
					if(parameters.length == 3)
						new Text(parameters[0], (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("building")  || name.equalsIgnoreCase(""+Building.class))
				{
					new Building((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, Integer.parseInt(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("player")  || name.equalsIgnoreCase(""+Player.class))
				{
					Player p = new Player((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, disabled);
					Platformer.player = disabled?null:p;
				}
				else if(name.equalsIgnoreCase("dog enemy")  || name.equalsIgnoreCase(""+DogEnemy.class))
				{
					new DogEnemy((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, disabled);
				}
				else if(name.equalsIgnoreCase("smart drone enemy")  || name.equalsIgnoreCase(""+SmartDroneEnemy.class))
				{
					new SmartDroneEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), disabled);
				}
				else if(name.equalsIgnoreCase("dumb drone enemy")  || name.equalsIgnoreCase(""+DumbDroneEnemy.class))
				{
					new DumbDroneEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), disabled);
				}
				else if(name.equalsIgnoreCase("granny")  || name.equalsIgnoreCase(""+Granny.class))
				{
					new Granny((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), disabled);
				}
				else if(name.equalsIgnoreCase("animal control enemy")  || name.equalsIgnoreCase(""+AnimalControlEnemy.class))
				{
					new AnimalControlEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), disabled);
				}
				else if(name.equalsIgnoreCase("portal")  || name.equalsIgnoreCase(""+Portal.class))
				{
					new Portal((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25, (int)Double.parseDouble(parameters[3])*25);
				}
				else if(name.equalsIgnoreCase("spikes")  || name.equalsIgnoreCase(""+Spikes.class))
				{
					new Spikes((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("pipe")  || name.equalsIgnoreCase(""+Pipe.class))
				{
					new Pipe((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, (int)Double.parseDouble(parameters[2])*25);
				}
				else if(name.equalsIgnoreCase("box")  || name.equalsIgnoreCase(""+Box.class))
				{
					new Box((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]));
				}
				else if(name.equalsIgnoreCase("level portal") || name.equalsIgnoreCase("levelportal") || name.equalsIgnoreCase(""+LevelPortal.class))
				{
					new LevelPortal((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), Stage.get(parameters[2]), Boolean.parseBoolean(parameters[3]));
				}
				else if(name.equalsIgnoreCase("tutorial box") || name.equalsIgnoreCase("tutorialbox") || name.equalsIgnoreCase(""+LevelPortal.class))
				{
					new TutorialBox(parameters[0], (int)Double.parseDouble(parameters[1]), (int)Double.parseDouble(parameters[2]), (int)Double.parseDouble(parameters[3]), (int)Double.parseDouble(parameters[4]));
				}
				else if(name.equalsIgnoreCase("floating platform") || name.equalsIgnoreCase("floatingplatform") || name.equalsIgnoreCase(""+LevelPortal.class))
				{
					new FloatingPlatform((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), (int)Double.parseDouble(parameters[2]), (int)Double.parseDouble(parameters[3]));
				}
			}
			if(disabled) {
				Platformer.game.getHandeler().forEach((g) -> {Platformer.screen.editLevel.add(g);});
			}
		}
		s.close();
	}
	public enum Stage
	{
		TEST(-3),
		EDIT(-2),
		LEVEL1(1),
		LEVEL2(2),
		LEVEL3(3),
		LEVEL4(4),
		LEVEL5(5),
		CUSTOM(-1),
		INFINITE(0);
		int level;
		static final int maxLevel = 5;
		Stage(int i)
		{
			this.level = i;
		}
		public static Stage get(int i)
		{
			for(Stage s: Stage.values())
				if(s.level == i)
					return s;
			return null;
		}
		public static Stage get(String s) {
			for(Stage st: Stage.values())
				if(st.name().equals(s))
					return st;
			return null;
		}
	}
}
