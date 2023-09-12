package catformer;

public class Level {
	public Stage stage;
	int currX = 0;
	public Level(Stage stage)
	{
		this.stage = stage;
		if(stage == Stage.LEVEL1)
		{
			new Background();
			new Player(10, 0);
			new Building(0, 200, 50);
			new Pipe(40, 300, 50);
			new Building(90, 250, 50);
			new Building(120, 250, 50);
			new Building(230, 220, 80);
			new Building(350, 180, 40);
			new Building(450, 320, 150);
			new Pipe(600, 370, 50, 90);
			new Portal(165,300,300,100);

		}
		if(stage == Stage.INFINITE)
		{
			new Background();
			new Player(10, 0);
			int width = (int)(Math.random()*3+1)*50;
			int height = ((int)(Math.random()*300)+100);
			new Building(0, height, width);
			currX += width;
			while(currX < Platformer.game.getWidth()*10)
			{
				int rand = (int)(Math.random()*100);
				width = (int)(Math.random()*3+1)*50;
				if(height < 100)
					height = height + ((int)(Math.random()*100));
				else if(height > Platformer.game.getHeight()-100)
					height = height - ((int)(Math.random()*100));
				else
					height = height + ((int)(Math.random()*200)-100);
				new Building(currX+rand, height, width);
				currX += rand+width;
			}
		}
	}
	public void update(float x, float y)
	{
		if(currX < x+400)
		{
			System.out.println("update level");
			int ocurrX = currX + Platformer.game.getWidth()*10;
			int width = (int)(Math.random()*3+1)*50;
			int height = ((int)(Math.random()*300)+100);
			while(currX < ocurrX)
			{
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				int rand = (int)(Math.random()*100);
				width = (int)(Math.random()*3+1)*50;
				if(height < 100)
					height = height + ((int)(Math.random()*100));
				else if(height > Platformer.game.getHeight()-100)
					height = height - ((int)(Math.random()*100));
				else
					height = height + ((int)(Math.random()*200)-100);
				new Building(currX+rand, height, width);
				currX += rand+width;
			}
		}
	}
	public enum Stage
	{
		LEVEL1,
		LEVEL2,
		LEVEL3,
		INFINITE
	}
}
