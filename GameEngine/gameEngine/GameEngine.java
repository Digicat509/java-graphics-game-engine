package gameEngine;

import java.awt.*;
import java.util.*;

public class GameEngine implements Runnable{
	
	public static void main(String[] args) {
		GameEngine ge = new GameEngine();
		ge.start();
	}
	private int width = 320, height=240;
	private float scale = 1f;
	private String title = "Nothing to see here";
	private Window window;
	private Thread thread;
	private boolean running = false;
	private final double UPDATE_CAP = 1.0/60;
	boolean render = false;
	double frame_time = 0;
	int fps = 0;
	int frames = 0;
	Handler hand;
	private Keyboard kevin = new Keyboard();
	private Mouse mice = new Mouse();
	
	public GameEngine() {
		hand = new Handler();
	}
	public void start() {
		window = new Window(this);
		thread = new Thread(this);
		thread.run();
	}
	public void stop() {
		
	}
	public void run() {
		running = true;
		double first_time = 0;
		double last_time = System.nanoTime()/1000000000.0;
		double passed_time = 0;
		double unproccessed_time = 0;
		
		while(running) {
			first_time = System.nanoTime()/1000000000.0;
			passed_time = first_time - last_time;
			last_time = first_time;
			
			render = false;
			unproccessed_time += passed_time;
			frame_time += passed_time;
			
			while(unproccessed_time >= UPDATE_CAP) {
				unproccessed_time -= UPDATE_CAP;
				//render later
				render = true;
				
				if(frame_time >=1.0) {
					frame_time = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);
				}
			}
			if(render) {
				window.update();
				kevin.update();
				frames++;
			}
			else {
				try{Thread.sleep(1);}catch (Exception e){e.printStackTrace();}
			}
		}
	}
	private void dispose() {
		
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float s)
	{
		scale = s;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Handler getHandeler()
	{
		return hand;
	}
	public void update(Graphics g) {
		hand.render(g);
	}
	public static void drawCenteredString(Graphics g, String s, int x, int y)
	{
		FontMetrics metrics = g.getFontMetrics();
		x = x - metrics.stringWidth(s) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		g.drawString(s, x, y);
	}
	public static void drawCenteredString(Graphics g, String s, int x, int y, int size)
	{
		g.setFont(new Font(g.getFont().getFontName(), 0, size));
		FontMetrics metrics = g.getFontMetrics();
		x = x - metrics.stringWidth(s) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		g.drawString(s, x, y);
	}
	public static void drawCenteredString(Graphics g, String s, int x, int y, int size, String font)
	{
		g.setFont(new Font(font, 0, size));
		FontMetrics metrics = g.getFontMetrics();
		x = x - metrics.stringWidth(s) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		g.drawString(s, x, y);
	}
	public static void drawCenteredString(Graphics g, String s, Rectangle rect)
	{
		g.setFont(new Font(g.getFont().getFontName(), 0, g.getFont().getSize()));
		FontMetrics metrics = g.getFontMetrics();
		int x = rect.x + (rect.width - metrics.stringWidth(s)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.drawString(s, x, y);
	}
	public static void drawCenteredString(Graphics g, String s, Rectangle rect, int size)
	{
		g.setFont(new Font(g.getFont().getFontName(), 0, size));
		FontMetrics metrics = g.getFontMetrics();
		int x = rect.x + (rect.width - metrics.stringWidth(s)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.drawString(s, x, y);
	}
}