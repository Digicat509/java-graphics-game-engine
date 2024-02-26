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
		
		if(stage == Stage.LEVEL3) {
			Platformer.player = new Player(30, 250, false);
			new Building(-50, 400, 400);
			
			new Building(450, 400, 100);
			new Building(550, 300, 100);
			
			new Building(750, 400, 200);
			new FloatingPlatform(750, 100, 100, 200);
			new Building(950, 100, 100);
			new Building(1050, 500, 100);
			
			new DogEnemy(1300,525, false);
			new Building(1200, 550, 350);
			new Building(1650, 500, 50);
			
			new Pipe(1275, 100, 175);
			new Pipe(1600, 250, 125);
			new FloatingPlatform(1200, 50, 100, 300);
			new FloatingPlatform(1700, 200, 100, 150);
			
			new Building(1800, 550, 150);
			new DumbDroneEnemy(1920,540, false);
			new Building(1950, 500, 150);
			new DumbDroneEnemy(2085,490, false);
			
			new Building(2200, 500, 400);
			new Box(2400-30,500-19);
			
			new Building(2700, 500, 450);
			new Granny(2950, 100, false);
			
			new Building(3200, 500, 350);
			new LevelPortal(3555, 550, Stage.LEVEL1, true);
		}
		
		if(stage == Stage.LEVEL2) {
			Platformer.player = new Player(30, 250, false);
			new Building(-50, 400, 1450);
			new Building(1400, 500, 400);
			
			new Building(500, 350, 50);
			new Building(600, 300, 50);
			new Building(700, 250, 50);
			new Building(800, 300, 50);
			new Building(900, 350, 50);
			new FloatingPlatform(900, 200, 50, 50);
			new Spikes(550,376,50);
			new Spikes(650,376,50);
			new Spikes(750,376,50);
			new Spikes(850,376,50);
			
			new FloatingPlatform(1150, 200, 150, 50);
			new DumbDroneEnemy(1200, 150, false);
			new FloatingPlatform(1400, 100, 100, 200);
			new FloatingPlatform(1600, 300, 100, 50);
			new DumbDroneEnemy(1650, 250, false);

			new Building(1900, 400, 100);
			new Building(2100, 300, 100);
			new Building(2300, 400, 100);
			new DogEnemy(1950, 350, false);
			new DogEnemy(2350, 350, false);
			new DogEnemy(2150, 250, false);
			
			new Building(2500, 500, 200);
			new Building(2700, 450, 50);
			new Building(2750, 400, 50);
			new Building(2800, 350, 50);
			
			new Building(2950, 500, 700);
			new FloatingPlatform(3050, 300, 50, 50);
			new FloatingPlatform(3500, 300, 50, 50);
			
			new AnimalControlEnemy(3075, 250, false);
			new AnimalControlEnemy(3525, 250, false);
			new LevelPortal(3650, 500, Stage.LEVEL3, false);
		}
		
		if(stage == Stage.LEVEL1) {
			Platformer.player = new Player(30, 250, false);
			new Building(-50, 500, 500);
			new Building(500, 450, 300);
			new DogEnemy(1000,350, false);
			new Building(850, 400, 300);
			new DogEnemy(1350,350, false);
			new Building(1200, 400, 300);
			new Building(1550, 500, 300);
			new Building(1950, 600, 400);
			
			new FloatingPlatform(2150, 550, 50, 50);
			new DumbDroneEnemy(2175, 500, false);
			new FloatingPlatform(2250, 500, 50, 50);
			new DumbDroneEnemy(2275, 450, false);
			
			new Building(2400, 500, 650);
			new DumbDroneEnemy(2550, 450, false);
			new DumbDroneEnemy(2600, 450, false);
			new DumbDroneEnemy(2750, 450, false);
			new DumbDroneEnemy(2800, 450, false);
			new SmartDroneEnemy(2950,450, false);
			
			new FloatingPlatform(2750, 400, 50, 50);
			new FloatingPlatform(2650, 400, 50, 50);
			new FloatingPlatform(2850, 400, 50, 50);
			new FloatingPlatform(2750, 300, 50, 50);
			
			new Building(3050, 450, 100);
			new Building(3100, 400, 50);
			
			new Building(3250, 500, 400);
			new DumbDroneEnemy(3525, 450, false);
			new Building(3250, 450, 100);
			new Building(3250, 400, 50);
			
			new Building(3650, 450, 150);
			new Building(3700, 400, 100);
			new Building(3750, 350, 50);
			new LevelPortal(3800, 500, Stage.LEVEL2, false);
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
					Platformer.player = new Player((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, false);
				}
				else if(name.equalsIgnoreCase("dog enemy")  || name.equalsIgnoreCase(""+DogEnemy.class))
				{
					new DogEnemy((int)Double.parseDouble(parameters[0])*25, (int)Double.parseDouble(parameters[1])*25, false);
				}
				else if(name.equalsIgnoreCase("smart drone enemy")  || name.equalsIgnoreCase(""+SmartDroneEnemy.class))
				{
					new SmartDroneEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("dumb drone enemy")  || name.equalsIgnoreCase(""+DumbDroneEnemy.class))
				{
					new DumbDroneEnemy((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("granny")  || name.equalsIgnoreCase(""+Granny.class))
				{
					new Granny((int)Double.parseDouble(parameters[0]), (int)Double.parseDouble(parameters[1]), false);
				}
				else if(name.equalsIgnoreCase("animal control enemy")  || name.equalsIgnoreCase(""+AnimalControlEnemy.class))
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
			}
		}
		s.close();
	}
	public enum Stage
	{
		TEST(-3),
		EDIT(-1),
		LEVEL1(1),
		LEVEL2(2),
		LEVEL3(3),
		CUSTOM(4),
		INFINITE(0);
		int level;
		static final int maxLevel = 4;
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
	}
}
