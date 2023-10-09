package catformer;

import gameEngine.GameObject;

public class DangerThing extends GameObject {
	int damage;
	public DangerThing(int damage) {
		super(2);
		this.damage = damage;
	}
}
