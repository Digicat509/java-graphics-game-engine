package catformer;

import gameEngine.Text;

public class Level {
	public Stage stage;
	int currX = 0;
	private int drawCurrX = 0;
	int height;
	public Level(Stage stage)
	{
		this.stage = stage;
		
		if(stage == Stage.LEVEL1) {
			Platformer.player = new Player(30, 250);
			//new Pipe(100, 450, 100);
			new Building(-50, 400, 400);
			//new Building(200, 400, 150);
			
			new Building(450, 400, 100);
			new Building(550, 300, 100);
			
			new Building(750, 400, 150);
			new FloatingPlatform(750, 100, 80, 200);
			new Building(900, 100, 100);
			new Building(1000, 500, 100);
			
			new DogEnemy(1250,525);
			new Building(1150, 550, 350);
			//new DogEnemy(1450,525);
			//new Building(1400, 550, 100);
			new Building(1600, 500, 50);
			
			new Pipe(1250, 100, 150);
			new FloatingPlatform(1150, 50, 100, 300);
			new Pipe(1550, 250, 100);
			new FloatingPlatform(1650, 200, 100, 150);
			
			new Building(1750, 550, 150);
			new DumbDroneEnemy(1870,540);
			new Building(1900, 500, 150);
			new DumbDroneEnemy(2035,490);
			
			new Building(2150, 500, 400);
			new Box(2350-12,500-16);
			
			new Building(2650, 500, 450);
			//new AnimalControlEnemy(2400, 100);
			new Granny(2900, 100);
		}
		
		if(stage == Stage.LEVEL2) {
			Platformer.player = new Player(30, 250);
			new Building(-50, 400, 400);
			//new Building(-50, 400, 400);
		}
		if(stage == Stage.TEST)
		{
			Platformer.player = new Player(30, 0);
			Platformer.text = new Text(""+Platformer.distance+"m", Platformer.game.getWidth()-100, 30, 20);
			new Building(-50, 300, 300);
			
			
		}
		if(stage == Stage.INFINITE)
		{
			Platformer.text = new Text(""+Platformer.distance+"m", Platformer.game.getWidth()-100, 30, 20);
			int width = (int)(Math.random()*3+1)*50;
			height = ((int)(Math.random()*300)+100);
			Platformer.player = new Player(30, height-50);
			new Building(0, height, width);
			currX += width;
			drawCurrX += width;
			generate();
		}
	}
	private void generate() {
		int dist = drawCurrX+Platformer.game.getWidth();
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
				new DogEnemy(drawCurrX+rand+width/2, height-21);
			else if(prob >= 28)
				new DumbDroneEnemy(drawCurrX+rand+width/2, height-16);
			else if(prob >= 24)
				new SmartDroneEnemy(drawCurrX+rand+width/2, height-16);
			else if (prob >= 22)
				new AnimalControlEnemy(drawCurrX+rand+width/2, height-46);
			else if(prob >= 21)
				new Box(drawCurrX+rand+width/2, height-16);
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
			System.out.println("update level");
			drawCurrX = (int)Platformer.player.x+Platformer.game.getWidth();
			generate();
		}
	}
	public enum Stage
	{
		TEST(-1),
		LEVEL1(1),
		LEVEL2(2),
		LEVEL3(3),
		INFINITE(0);
		int level;
		static final int maxLevel = 3;
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
