package gameEngine;

public class Timer {
	private long time;
	public Timer(double seconds)
	{
		time = System.currentTimeMillis()+(int)(1000*seconds);
	}
	public boolean timeUp()
	{
		return time < System.currentTimeMillis();
	}
}
