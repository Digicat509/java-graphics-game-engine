package catformer;

import java.awt.event.KeyEvent;

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
			new Background();
			Platformer.player = new Player(30, 250);
			new Pipe(120, 400, 80);
			new Building(-40, 350, 160);
			new Building(200, 350, 120);
			new Building(400, 350, 80);
			new Building(480, 250, 80);
			new Building(640, 350, 160);
			new FloatingPlatform(640, 50, 80, 200);
			//new TutorialBoss(630, 0);
			new Building(800, 50, 80);
			new Building(1000, 500, 80);
			new Building(1160, 550, 160);
			new Building(1360, 550, 120);
			new Building(1560, 500, 40);
			

			
			new Pipe(1080, 100, 180);
			new FloatingPlatform(1000, 50, 80, 350);
			new Pipe(1360, 250, 120);
			new FloatingPlatform(1480, 200, 80, 150);


			
			new Building(1680, 550, 160);
			//new DumbDroneEnemy(1760,540);
			new Building(1840, 500, 160);
			//new DumbDroneEnemy(1920,490);
		}
		if(stage == Stage.TEST)
		{
			new Background();
			Platformer.player = new Player(30, 0);
			Platformer.text = new Text(""+Platformer.distance+"m", Platformer.game.getWidth()-100, 30, 20);
			new Building(0, 200, 50);
			new Pipe(40, 300, 50);
			new Building(90, 250, 80);
			new Spikes(230, 210, 20);
			new Building(230, 220, 80);
			new Building(350, 180, 40);
			new Building(450, 320, 150);
			new Pipe(600, 370, 50, 90);
			new Portal(165,300,300,100);
			new DumbDroneEnemy(100,200);
			//new AnimalControlEnemy(100, 100);
			
		}
		if(stage == Stage.INFINITE)
		{
			new Background();
			Platformer.text = new Text(""+Platformer.distance+"m", Platformer.game.getWidth()-100, 30, 20);
			Platformer.player = new Player(30, 0);
			int width = (int)(Math.random()*3+1)*50;
			height = ((int)(Math.random()*300)+100);
			new Building(0, height, width);
			currX += width;
			drawCurrX += width;
			generate(0);
		}
	}
	private void generate(int x) {
		while(currX < x+Platformer.game.getWidth()*10)
		{
			int rand = (int)(Math.random()*100);
			int prob = (int)(Math.random()*20);
			int width = (int)(Math.random()*3+1)*50;
			height = height + ((int)(Math.random()*200)-100);
			while(height < 250)
				height = height + ((int)(Math.random()*100));
			while(height > Platformer.game.getHeight()-100)
				height = height - ((int)(Math.random()*100));
			if(prob >= 16) 
				new DogEnemy(drawCurrX+rand+width/2, height-21);
			else if(prob >= 14)
				new DumbDroneEnemy(drawCurrX+rand+width/2, height-16);
			else if(prob >= 12)
				new SmartDroneEnemy(drawCurrX+rand+width/2, height-16);
			else if (prob >= 11)
				new AnimalControlEnemy(drawCurrX+rand+width/2, height-46);
			else if(prob >= 7 && rand > 75 && height > 100)
				new Portal(drawCurrX+rand-75, height+30, drawCurrX+rand+150, height-100);
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
			drawCurrX = Platformer.game.getWidth();
			generate((int)x);
		}
	}
	public enum Stage
	{
		TEST,
		LEVEL1,
		LEVEL2,
		LEVEL3,
		INFINITE
	}
}
