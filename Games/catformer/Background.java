package catformer;

import gameEngine.GameObject;

public class Background extends GameObject{
	public Background()
	{
		super(0);
		Platformer.game.getHandeler().add(this, false);
	}
	public void draw()
	{
		
	}
}
