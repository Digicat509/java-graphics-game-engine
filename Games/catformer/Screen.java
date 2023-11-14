package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import catformer.Level.Stage;
import gameEngine.Button;
//import catformer.Platformer.STATE;
import gameEngine.GameEngine;
import gameEngine.GameEngine.State;
import gameEngine.GameImage;
import gameEngine.GameObject;
import gameEngine.Text;
import gameEngine.Timer;

public class Screen extends GameObject{
	ArrayList<Button> list = new ArrayList<Button>();
	SelectorWheel sw = null;
	private boolean selectorWheelHeld = false;
	HashSet<GameObject> editLevel;
	EditTool et = EditTool.BLOCK;
	Building editBuilding = null;
	boolean drag = false;
	int mx = 0, my = 0;
	int level = 1;
	Text levelText;
	Color color;
	private boolean wrote;
	private Timer keyDelayTimer = new Timer(0);
	public Screen() {
		color = new Color(181, 4, 39);
		Platformer.game.getHandeler().add(this, false);
	}
	public void updateState(State state)
	{
		Platformer.sound.audio.stop();
		Platformer.sound.creditsAudio.stop();
		Platformer.sound.bossAudio.stop();
		Platformer.game.getHandeler().clear();
		Platformer.game.state = state;
		Platformer.game.getHandeler().add(Platformer.screen, false);
		if(state == State.TITLE) {
			level = 1;
			new GameImage(-1, getClass().getResource("assets/GameImage1.png"), 0, 0, Platformer.game.getWidth(), Platformer.game.getHeight());
			list = new ArrayList<Button>();
			new Text(Platformer.game.getTitle(), Platformer.game.getWidth()/2, 200, 40, color);
			list.add(new Button("Play", Platformer.game.getWidth()/2-50, 300, 100, 50, color, () -> {this.updateState(State.PLAYING);}));
			list.add(new Button(Platformer.game.getWidth()/2-150, 300, 50, 50, getClass().getResource("assets/LeftArrow.png"), () -> {if(this.level > 1)this.level--;else this.level = Stage.maxLevel;}));
			list.add(new Button(Platformer.game.getWidth()/2+100, 300, 50, 50, getClass().getResource("assets/RightArrow.png"), () -> {if(this.level < Stage.maxLevel)this.level++;else this.level = 1;}));
			levelText = new Text("Level:\t"+level, Platformer.game.getWidth()/2, 380, 20, color);
			list.add(new Button("Infinite Mode", Platformer.game.getWidth()/2-75, 420, 150, 50, color, () -> {this.updateState(State.PLAYING, Stage.INFINITE);}));
			list.add(new Button("Edit Mode", Platformer.game.getWidth()/2-60, 500, 120, 50, color, () -> {this.updateState(State.PLAYING, Stage.EDIT);}));
			list.add(new Button("Credits", Platformer.game.getWidth()/2-50, 580, 100, 50, color, () -> {this.updateState(State.CREDITS);}));
		}
		else if(state == State.PLAYING)
		{
			new GameImage(-1, getClass().getResource("assets/GameImage1.png"), 0, 0, Platformer.game.getWidth(), Platformer.game.getHeight());
			list = new ArrayList<Button>();
			Platformer.levelNum = level;
			Platformer.start(null);
			Platformer.sound.audio.setFramePosition(0);
			Platformer.sound.audio.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else if(state == State.CREDITS)
		{
			new Credits();
			list = new ArrayList<Button>();
			Platformer.sound.creditsAudio.setFramePosition(0);
			Platformer.sound.creditsAudio.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public void updateState(State state, Stage stage)
	{
		Platformer.sound.audio.stop();
		Platformer.sound.creditsAudio.stop();
		Platformer.game.getHandeler().clear();
		Platformer.game.state = state;
		Platformer.game.getHandeler().add(Platformer.screen, false);
		if(state == State.TITLE) {
			new GameImage(-1, getClass().getResource("assets/GameImage1.png"), 0, 0, Platformer.game.getWidth(), Platformer.game.getHeight());
			list = new ArrayList<Button>();
			new Text(Platformer.game.getTitle(), Platformer.game.getWidth()/2, 200, 40, color);
			list.add(new Button("Play", Platformer.game.getWidth()/2-50, 300, 100, 50, color, () -> {this.updateState(State.PLAYING);}));
			list.add(new Button(Platformer.game.getWidth()/2-150, 300, 50, 50, getClass().getResource("assets/LeftArrow.png"), () -> {if(this.level > 1)this.level--;else this.level = Stage.maxLevel;}));
			list.add(new Button(Platformer.game.getWidth()/2+100, 300, 50, 50, getClass().getResource("assets/RightArrow.png"), () -> {if(this.level < Stage.maxLevel)this.level++;else this.level = 1;}));
			levelText = new Text("Level:\t"+level, Platformer.game.getWidth()/2, 380, 20, color);
			list.add(new Button("Infinite Mode", Platformer.game.getWidth()/2-75, 420, 150, 50, color, () -> {this.updateState(State.PLAYING, Stage.INFINITE);}));
			list.add(new Button("Edit Mode", Platformer.game.getWidth()/2-60, 500, 120, 50, color, () -> {this.updateState(State.PLAYING, Stage.EDIT);}));
			list.add(new Button("Credits", Platformer.game.getWidth()/2-50, 580, 100, 50, color, () -> {this.updateState(State.CREDITS);}));
		}
		else if(state == State.PLAYING && stage == Stage.EDIT)
		{
			sw = null;
			editLevel = new HashSet<GameObject>();
			list = new ArrayList<Button>();
			wrote = false;
			Platformer.start(stage);
		}
		else if(state == State.PLAYING)
		{
			new GameImage(-1, getClass().getResource("assets/GameImage1.png"), 0, 0, Platformer.game.getWidth(), Platformer.game.getHeight());
			list = new ArrayList<Button>();
			Platformer.start(stage);
			Platformer.sound.audio.setFramePosition(0);
			Platformer.sound.audio.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else if(state == State.CREDITS)
		{
			new Credits();
			list = new ArrayList<Button>();
			Platformer.sound.creditsAudio.setFramePosition(0);
			Platformer.sound.creditsAudio.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public void draw(Graphics g) {
		if(Platformer.game.state == State.TITLE)
		{
			if(levelText != null)
				levelText.text = "Level:\t"+level;
			if(Platformer.game.getInput().isMouseClicked()) {
				int mx = Platformer.game.getInput().getMouseX();
				int my = Platformer.game.getInput().getMouseY();
				for(Button b: list)
				{
					b.clickBox(mx, my);
					
				}
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_ENTER))
				this.updateState(State.PLAYING);
			if(Platformer.game.getInput().isKey(KeyEvent.VK_LEFT))
			{
				if(keyDelayTimer.timeUp())
				{
					if(this.level > 1)
						this.level--;
					else 
						this.level = Stage.maxLevel;
					keyDelayTimer = new Timer(.2);
				}
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT))
			{
				if(keyDelayTimer.timeUp())
				{
					if(this.level < Stage.maxLevel)
						this.level++;
					else 
						this.level = 1;
					keyDelayTimer = new Timer(.2);
				}
			}
		}
		if(Platformer.level != null && Platformer.level.stage == Stage.EDIT)
		{
			if(Platformer.game.getInput().isMouseClicked()) {
				mx = Platformer.game.getInput().getMouseX();
				my = Platformer.game.getInput().getMouseY();
				if(et.equals(EditTool.BLOCK) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new Block(mx/Grid.currGridSize, my/Grid.currGridSize));
				}
			}
			if(Math.abs(Platformer.game.getInput().getMouseDragX()) > 1 || Math.abs(Platformer.game.getInput().getMouseDragY()) > 1)
			{
				if(!drag && et.equals(EditTool.BUILDING))
				{
					editBuilding = new Building((Platformer.game.getInput().getMouseX()/25)*25, (Platformer.game.getInput().getMouseY()/25)*25, 0);
					  drag = true;
				}
				else if(et.equals(EditTool.BUILDING) && Platformer.game.getInput().getMouseDragX() >= 50 && Platformer.game.getInput().getMouseDragX()%50 == 0)
				{
					editBuilding = new Building((int)editBuilding.x, (int)editBuilding.y, Platformer.game.getInput().getMouseDragX());
				}
			}
			if(!Platformer.game.getInput().isButton(1) && drag)
			{
				if(et.equals(EditTool.BUILDING) && editBuilding != null && editBuilding.w > 0)
				{
					editLevel.add(editBuilding);
					editBuilding = null;
				}
				else if(editBuilding != null)
				{
					editBuilding = null;
				}
				drag = false;
			}
			if(Platformer.game.getInput().isButton(3) && !selectorWheelHeld)
			{
				selectorWheelHeld = true;
				if(sw == null)
					sw = new SelectorWheel(Platformer.game.getInput().getMouseX(), Platformer.game.getInput().getMouseY());
				else 
				{
					sw.show();
					sw.recenter(Platformer.game.getInput().getMouseX(), Platformer.game.getInput().getMouseY());
				}
			}
			else if(sw != null && !Platformer.game.getInput().isButton(3) && selectorWheelHeld)
			{
				selectorWheelHeld = false;
				sw.selectFinal(Platformer.game.getInput().getMouseX(), Platformer.game.getInput().getMouseY());
				System.out.println(et);
				sw.hide();
			}
			if(selectorWheelHeld){
				sw.select(Platformer.game.getInput().getMouseX(), Platformer.game.getInput().getMouseY());
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_ESCAPE) && !wrote)
			{
				try {
					writeToSaveFile("\\C:\\Users\\alexa\\OneDrive\\Documents\\GitHub\\java-graphics-game-engine\\Games\\catformer\\assets\\level_editor.txt"/*"assets/level_editor.txt"*/);
					wrote = true;
				} 
				catch(IOException e) {e.printStackTrace();}
				catch(URISyntaxException e) {e.printStackTrace();}
				Platformer.level = null;
				updateState(State.TITLE);
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_Z))
				Grid.currGridSize += Platformer.game.getInput().getScroll();
		}
	}
	private void writeToSaveFile(String path) throws IOException, URISyntaxException{
		FileWriter out = new FileWriter(new File(path/*getClass().getResource(path).toURI()*/), false);
		for(GameObject o: editLevel)
		{
			out.write(""+o+"\n");
			System.out.println(o);
		}
		editLevel = new HashSet<GameObject>();
		out.flush();
	}
	enum EditTool
	{
		PLAYER(0), BUILDING(1), BLOCK(2), ENEMY(3), PORTAL(4);
		private int i;
		EditTool(int i)
		{
			this.i = i;
		}
		public static EditTool choseTool(int i)
		{
			for(EditTool e : EditTool.values())
			{
				if(e.i == i)
					return e;
			}
			return null;
		}
	}
}
