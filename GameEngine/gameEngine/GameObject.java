package gameEngine;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

import javax.imageio.ImageIO;

public abstract class GameObject implements Comparable<GameObject> {
	public float x,y,dx,dy;
	public int w,h;
	public double theta;
	protected int layers;
	private Image image;
	protected boolean visible = true;
	public GameObject() {
		this(0);
	}
	public GameObject(int layers) {
		this.layers = layers;
	}
	public GameObject(String image) {
		this(0);
		try {
			this.setImage(ImageIO.read(getClass().getResource(image)));
		} catch (IOException e) {
			System.out.println("No Image for "+image+" "+this.getClass()+"!!!");
		}
	}
	public GameObject(int layers, String image) {
		this.layers = layers;
		try {
			this.setImage(ImageIO.read(getClass().getResource(image)));
		} catch (Exception e) {
			System.out.println("No Image for "+image+" "+this.getClass()+"!!!");
		}
	}
	public GameObject(URL image) {
		this(0);
		try {
			this.setImage(ImageIO.read(image));
		} catch (IOException e) {
			System.out.println("No Image for "+image+" "+this.getClass()+"!!!");
		}
	}
	public GameObject(int layers, URL image) {
		this.layers = layers;
		try {
			this.setImage(ImageIO.read(image));
		} catch (Exception e) {
			System.out.println("No Image for "+image+" "+this.getClass()+"!!!");
		}
	}
	public int compareTo(GameObject other) {
		return (this.layers-other.layers);
	}
	public boolean hits(GameObject other) {
		if(this.getHitbox().hits(other.getHitbox())) {
			return true;
		}
		return false;
	}
	public boolean hitsAny() {
		for(GameObject o: Handler.hitsHand) {
			if(!this.equals(o)) {
				if(this.getHitbox().hits(o.getHitbox())) {
					return true;
				}
			}
		}
		return false;
	}
	public GameObject hits() {
		if(this.layers > 0) {
			for(GameObject o: Handler.hitsHand) {
				if(!this.equals(o))
					if(o.layers > 0)
						if(this.getHitbox().hits(o.getHitbox()))
							return o;
			}
		}
		return null;
	}
	public Hitbox getHitbox() {
		return new RectangularHitbox((int)x, (int)y, w, h);
	}
	public HashSet<GameObject> allHits() {
		HashSet<GameObject> hiting = new HashSet<GameObject>(); 
		if(this.layers > 0) {
			for(GameObject o: Handler.hitsHand) {
				if(!this.equals(o))
					if(o.layers > 0)
						if(this.getHitbox().hits(o.getHitbox()))
							hiting.add(o);
			}
			return hiting;
		}
		return null;
	}
	public void show() {
		visible = true;
	}
	public void hide() {
		visible = false;
	}
	public void draw(Graphics g) {
		if(visible) {
			Graphics2D g2d = (Graphics2D)g;
			try {
				g2d.drawImage(getImage(), (int)x, (int)y, (int)w, (int)h, null);
			}
			catch(Exception e) {
				g2d.setColor(Color.white);
				g2d.fillRect((int)x,(int)y,w,h);
			}
			move();
		}
	}
	public void move() {
		x += dx;
		y += dy;
	}
	@Override
	public String toString() {
		return ""+this.getClass()+" "+x+", "+y;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
