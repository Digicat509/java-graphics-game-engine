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
			
			new Building(750, 400, 200);
			new FloatingPlatform(750, 100, 100, 200);
			new Building(950, 100, 100);
			new Building(1050, 500, 100);
			
			new DogEnemy(1300,525);
			new Building(1200, 550, 350);
			//new DogEnemy(1450,525);
			//new Building(1400, 550, 100);
			new Building(1650, 500, 50);
			
			new Pipe(1300, 100, 150);
			new FloatingPlatform(1200, 50, 100, 300);
			new Pipe(1600, 250, 100);
			new FloatingPlatform(1700, 200, 100, 150);
			
			new Building(1800, 550, 150);
			new DumbDroneEnemy(1920,540);
			new Building(1950, 500, 150);
			new DumbDroneEnemy(2085,490);
			
			new Building(2200, 500, 400);
			new Box(2400-30,500-19);
			
			new Building(2700, 500, 450);
			//new AnimalControlEnemy(2400, 100);
			new Granny(2950, 100);
			
			new Building(3200, 500, 350);
			new LevelPortal(3555, 550, Stage.LEVEL2);
		}
		
		if(stage == Stage.LEVEL2) {
			Platformer.player = new Player(30, 250);
			new Building(-50, 400, 300);
			new Building(350, 500, 300);
			new Building(800, 600, 300);
			new Building(1250, 650, 300);
		}
		
		if(stage == Stage.LEVEL3) {
			Platformer.player = new Player(30, 250);
			new Building(-50, 500, 500);
			new Building(500, 450, 300);
			new DogEnemy(1000,350);
			new Building(850, 400, 300);
			new DogEnemy(1350,350);
			new Building(1200, 400, 300);
			new Building(1550, 500, 300);
			new Building(1950, 600, 400);
			
			new FloatingPlatform(2150, 550, 50, 50);
			new DumbDroneEnemy(2175, 500);
			new FloatingPlatform(2250, 500, 50, 50);
			new DumbDroneEnemy(2275, 450);
			
			new Building(2400, 500, 750);
			new DumbDroneEnemy(2550, 450);
			new DumbDroneEnemy(2600, 450);
			new DumbDroneEnemy(2750, 450);
			new DumbDroneEnemy(2800, 450);
			new SmartDroneEnemy(2950,450);
			
			new FloatingPlatform(2750, 400, 50, 50);
			new FloatingPlatform(2650, 400, 50, 50);
			new FloatingPlatform(2850, 400, 50, 50);
			new FloatingPlatform(2750, 300, 50, 50);
			
			new FloatingPlatform(2750, 300, 50, 50);
			
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
