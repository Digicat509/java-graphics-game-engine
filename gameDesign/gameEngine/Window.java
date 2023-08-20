package gameDesign.gameEngine;

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;

public class Window {
	
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private Graphics g;
	private BufferStrategy bs;
	private GameEngine game;
	
	public Window(GameEngine ge) {
		game=ge;
		image = new BufferedImage(ge.getWidth(), ge.getHeight(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension dimension = new Dimension((int)(ge.getWidth()*ge.getScale()), (int)(ge.getHeight()*ge.getScale()));
		
		canvas.setPreferredSize(dimension);
		
		frame = new JFrame(ge.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.requestFocus();
		canvas.createBufferStrategy(2);
		
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	public BufferedImage getImage() {
		return image;
	}
	public JFrame getFrame() {
		return frame;
	}

	public void update() {
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		game.update(g);
		bs.show();
		
	}
	
	
}
