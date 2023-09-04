package catformer;

public class Level {
	public Stage stage;
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
			new Pipe(600, 370, 50);
		}
		if(stage == Stage.INFINITE)
		{
			new Background();
			new Player(10, 0);
			int currX = 0;
			int width = (int)(Math.random()*3+1)*50;
			new Building(0, ((int)(Math.random()*300)+100), width);
			currX += width;
			while(currX < Platformer.game.getWidth())
			{
				int rand = (int)(Math.random()*100);width = (int)(Math.random()*3+1)*50;
				new Building(currX+rand, ((int)(Math.random()*300)+100), width);
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