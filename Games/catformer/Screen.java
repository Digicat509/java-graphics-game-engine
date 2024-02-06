package catformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import catformer.Level.Stage;
import gameEngine.Button;
import gameEngine.GameEngine.State;
import gameEngine.GameImage;
import gameEngine.GameObject;
import gameEngine.Text;
import gameEngine.TextBar;
import gameEngine.Timer;

public class Screen extends GameObject{
	ArrayList<Button> list = new ArrayList<Button>();
	SelectorWheel sw = null;
	private boolean selectorWheelHeld = false;
	HashSet<GameObject> editLevel;
	ArrayList<GameObject> editRemove = new ArrayList<GameObject>();
	EditTool et = EditTool.BLOCK;
	Button soundButton = new Button(0, 0, 0, 0, ()->{});
	Building editBuilding = null;
	boolean drag = false;
	int mx = 0, my = 0;
	int level = 1;
	int lastX = 0;
	int editOffsetX = 0;
	Text levelText;
	TextBar textBar;
	Color color;
	String font;
	private boolean soundOn = true;
	private boolean wrote;
	private boolean playerPlaced = false;
	private Timer keyPressDelayTimer = new Timer(0);
	private Timer keyWaitTimer = new Timer(0);
	boolean inputText;
	public Screen() {
		color = new Color(164, 143, 181);
		font = "roboto";
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
			keyWaitTimer = new Timer(1);
			level = 1;
			new GameImage(-1, getClass().getResource("assets/GameImage1.png"), 0, 0, Platformer.game.getWidth(), Platformer.game.getHeight());
			list = new ArrayList<Button>();
			new Text(Platformer.game.getTitle(), Platformer.game.getWidth()/2-25, 200, 40, font, new Color(36, 31, 41));
			list.add(new Button("Play", Platformer.game.getWidth()/2-75, 300, 100, 50, font, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.PLAYING);}));
			list.add(new Button(Platformer.game.getWidth()/2-175, 300, 50, 50, getClass().getResource("assets/LeftArrow.png"), () -> {if(this.level > 1)this.level--;else this.level = Stage.maxLevel;}));
			list.add(new Button(Platformer.game.getWidth()/2+75, 300, 50, 50, getClass().getResource("assets/RightArrow.png"), () -> {if(this.level < Stage.maxLevel)this.level++;else this.level = 1;}));
			levelText = new Text("Level:\t"+level, Platformer.game.getWidth()/2-25, 380, 20, font, color);
			list.add(new Button("Infinite Mode", Platformer.game.getWidth()/2-100, 420, 150, 50, font, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.PLAYING, Stage.INFINITE);}));
			list.add(new Button("Edit Mode", Platformer.game.getWidth()/2-85, 500, 120, 50, font, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.PLAYING, Stage.EDIT);}));
			list.add(new Button("Credits", Platformer.game.getWidth()/2-75, 580, 100, 50, font, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.CREDITS);}));
			soundButton = new Button(Platformer.game.getWidth()-125, 20, 75, 75, getClass().getResource("assets/Sound.png"), () -> {soundOn = !soundOn;try{soundButton.setImage(soundOn?ImageIO.read(getClass().getResource("assets/Sound.png")):ImageIO.read(getClass().getResource("assets/NoSound.png")));}catch(IOException e) {}});
			list.add(soundButton);
			new Text("Leaderboard: ", 75, 75, 18, font, Color.white);
			try {
				int[] leaderboard = readLeaderboard();
				if(leaderboard != null)
					for(int i = 0; i < 10; i++)
						if(i < leaderboard.length)
							new Text(""+leaderboard[i]+"m", 75, 125+(i)*50, 18, font, Color.white);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			keyWaitTimer = new Timer(1);
			new GameImage(-1, getClass().getResource("assets/GameImage1.png"), 0, 0, Platformer.game.getWidth(), Platformer.game.getHeight());
			list = new ArrayList<Button>();
			new Text(Platformer.game.getTitle(), Platformer.game.getWidth()/2-25, 200, 40, new Color(36, 31, 41));
			list.add(new Button("Play", Platformer.game.getWidth()/2-75, 300, 100, 50, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.PLAYING);}));
			list.add(new Button(Platformer.game.getWidth()/2-175, 300, 50, 50, getClass().getResource("assets/LeftArrow.png"), () -> {if(this.level > 1)this.level--;else this.level = Stage.maxLevel;}));
			list.add(new Button(Platformer.game.getWidth()/2+75, 300, 50, 50, getClass().getResource("assets/RightArrow.png"), () -> {if(this.level < Stage.maxLevel)this.level++;else this.level = 1;}));
			levelText = new Text("Level:\t"+level, Platformer.game.getWidth()/2-25, 380, 20, color);
			list.add(new Button("Infinite Mode", Platformer.game.getWidth()/2-100, 420, 150, 50, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.PLAYING, Stage.INFINITE);}));
			list.add(new Button("Edit Mode", Platformer.game.getWidth()/2-85, 500, 120, 50, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.PLAYING, Stage.EDIT);}));
			list.add(new Button("Credits", Platformer.game.getWidth()/2-75, 580, 100, 50, color, getClass().getResource("assets/Button.png"), () -> {this.updateState(State.CREDITS);}));
			soundButton = new Button(Platformer.game.getWidth()-125, 20, 75, 75, getClass().getResource("assets/Sound.png"), () -> {soundOn = !soundOn;try{soundButton.setImage(soundOn?ImageIO.read(getClass().getResource("assets/Sound.png")):ImageIO.read(getClass().getResource("assets/NoSound.png")));}catch(IOException e) {}});
			list.add(soundButton);
			new Text("Leaderboard: ", 75, 75, 18, Color.white);
			try {
				int[] leaderboard = readLeaderboard();
				if(leaderboard != null)
					for(int i = 0; i < 10; i++)
						if(i < leaderboard.length)
							new Text(""+leaderboard[i]+"m", 75, 125+(leaderboard.length-i-1)*50, 18, Color.white);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(state == State.PLAYING && stage == Stage.EDIT)
		{
			sw = null;
			editLevel = new HashSet<GameObject>();
			list = new ArrayList<Button>();
			wrote = false;
			playerPlaced = false;
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
		if(!soundOn) {
			Platformer.sound.stopAll();
		}
		if(Platformer.game.getInput().isKey(KeyEvent.VK_R) && !inputText){
			Platformer.game.stop();
			Platformer.screen.updateState(State.TITLE);
			Platformer.level = null;
		}
		
		if(Platformer.game.state == State.TITLE)
		{
			if(levelText != null)
				levelText.setText("Level:\t"+level);
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
			if(Platformer.game.getInput().isKey(KeyEvent.VK_LEFT) && keyWaitTimer.timeUp())
			{
				if(keyPressDelayTimer.timeUp())
				{
					if(this.level > 1)
						this.level--;
					else 
						this.level = Stage.maxLevel;
					keyPressDelayTimer = new Timer(.2);
				}
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_RIGHT) && keyWaitTimer.timeUp())
			{
				if(keyPressDelayTimer.timeUp())
				{
					if(this.level < Stage.maxLevel)
						this.level++;
					else 
						this.level = 1;
					keyPressDelayTimer = new Timer(.2);
				}
			}
		}
		if(Platformer.level != null && Platformer.level.stage == Stage.CUSTOM)
		{
			if(inputText)
			{
				if(textBar == null) {
					textBar = new TextBar(Platformer.game.getWidth()/2-100, Platformer.game.getHeight()/2-15);
					try {
						String[] temp = readLevelIndex();
						for(String s: temp)
							System.out.println(s+" ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				String k = Platformer.game.getInput().getLastKeyTyped();
				if(k != null)
				{
					textBar.add(k);
				}
				if(textBar.getString().length() > 0 && Platformer.game.getInput().isKey(KeyEvent.VK_ENTER))
				{
					inputText = false;
					String s = textBar.enterString();
					Platformer.game.getHandeler().remove(textBar);
					textBar = null;
					try{Platformer.level.build(s);}catch(Exception e) {e.printStackTrace();}
				}
			}
		}
		if(Platformer.level != null && Platformer.level.stage == Stage.EDIT)
		{
			if(editRemove.size() > 0)
			{
				for(int i = editRemove.size()-1; i >= 0; i--)
				{
					editLevel.remove(editRemove.remove(i));
				}
			}
			if(Platformer.game.getInput().isMouseClicked() && !Platformer.game.getInput().isButton(2) && et != null) {
				mx = Platformer.game.getInput().getMouseX();
				my = Platformer.game.getInput().getMouseY();
				if(et.equals(EditTool.BLOCK) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new Block((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25));
				}
				else if(et.equals(EditTool.PLAYER) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1 && !playerPlaced)
				{
					editLevel.add(new Player((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
					playerPlaced = true;
				}
				else if(et.equals(EditTool.ENEMY) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new DogEnemy((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
				}
				else if(et.equals(EditTool.DOG) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new DogEnemy((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
				}
				else if(et.equals(EditTool.ANIMAL_CONTROL) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new AnimalControlEnemy((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
				}
				else if(et.equals(EditTool.SMART) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new SmartDroneEnemy((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
				}
				else if(et.equals(EditTool.DUMB) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new DumbDroneEnemy((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
				}
				else if(et.equals(EditTool.GRANNY) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new Granny((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, true));
				}
				else if(et.equals(EditTool.PORTAL) && mx % Grid.currGridSize > 1 && mx % Grid.currGridSize < Grid.currGridSize-1 && my % Grid.currGridSize > 1 && my % Grid.currGridSize < Grid.currGridSize-1)
				{
					editLevel.add(new Portal((mx/25)*25+(int)Platformer.level.grid.x, (my/25)*25, (mx/25+4)*25+(int)Platformer.level.grid.x, (my/25-4)*25));
				}
				else if(et.equals(EditTool.ERASER))
				{
					Platformer.game.getHandeler().forEach((GameObject o) -> {
						if(o.getHitbox().contains(mx, my)) {
							if(o instanceof Player)
								playerPlaced = false;
							Platformer.game.getHandeler().remove(o);
							editRemove.add(o);
						}
					});
				}
			}
			if((Math.abs(Platformer.game.getInput().getMouseDragX()) > 1 || Math.abs(Platformer.game.getInput().getMouseDragY()) > 1 )&& et != null)
			{
				if(!drag && et.equals(EditTool.BUILDING))
				{
					editBuilding = new Building((Platformer.game.getInput().getMouseX()/25)*25+(int)Platformer.level.grid.x, (Platformer.game.getInput().getMouseY()/25)*25, 0);
					drag = true;
				}
				else if(et.equals(EditTool.BUILDING) && Platformer.game.getInput().getMouseDragX() >= 50 && Platformer.game.getInput().getMouseDragX()%50 == 0)
				{
					editBuilding = new Building((int)editBuilding.x, (int)editBuilding.y, Platformer.game.getInput().getMouseDragX());
				}
			}
			if(!Platformer.game.getInput().isButton(1) && drag && et != null)
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
			if(Platformer.game.getInput().isButton(2) && Platformer.game.getInput().draging())
			{
				if(lastX == 0)
					lastX = Platformer.game.getInput().getMouseX();
				int temp = (Platformer.game.getInput().getMouseX()-lastX);
				if(editOffsetX + temp > 0)
				{
					editOffsetX += temp;
					Platformer.level.grid.x -= temp;
					if(Platformer.level.grid.x <= -Grid.currGridSize || Platformer.level.grid.x >= Grid.currGridSize) {
						Platformer.level.grid.x %= 25;
					}
					Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x -= temp;});
				}
				lastX = Platformer.game.getInput().getMouseX();
			}
			if(!Platformer.game.getInput().draging()) {
				lastX = 0;
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
				inputText = true;
			}
			if(inputText)
			{
				if(textBar == null)
					textBar = new TextBar(Platformer.game.getWidth()/2-100, Platformer.game.getHeight()/2-15);
				String k = Platformer.game.getInput().getLastKeyTyped();
				if(k != null)
				{
					textBar.add(k);
				}
				if(Platformer.game.getInput().isKey(KeyEvent.VK_ENTER))
				{
					String s = textBar.enterString();
					if(s != "")
					{
						inputText = false;
						Platformer.game.getHandeler().forEach(other -> {if(!other.equals(this))other.x += editOffsetX;});
						try {
							addToLevelIndex(s);
							writeToSaveFile(s);
							wrote = true;
						} 
						catch(IOException e) {e.printStackTrace();}
						textBar = null;
						Platformer.level = null;
						updateState(State.TITLE);
					}
				}
			}
			if(Platformer.game.getInput().isKey(KeyEvent.VK_Z))
				Grid.currGridSize += Platformer.game.getInput().getScroll();
		}
	}
	private void writeToSaveFile(String path) throws IOException{
		//FileWriter out = new FileWriter(new File(getClass().getResource(path).getPath()), false);
		String s = "";
		for(GameObject o: editLevel)
		{
			s += ""+o+"\n";
			System.out.println(o);
		}
		editLevel = new HashSet<GameObject>();
		Platformer.gameData.addData(path, s);
	}
	private int[] readLeaderboard() throws IOException {
		Scanner in = new Scanner(Platformer.gameData.getData("assets/leaderboard.txt") == null? "":Platformer.gameData.getData("assets/leaderboard.txt")/*new File(getClass().getResource("assets/leaderboard.txt").getPath())*/);
		if(in.hasNextLine()) {
			String[] arr = in.nextLine().split(" ");
			int[] temp = new int[arr.length];
			for(int i = 0; i < arr.length; i++)
				temp[i] = Integer.parseInt(arr[i]);
			return temp;
		}
		return null;
	}
	private String[] readLevelIndex() throws IOException {
		Scanner in = new Scanner(Platformer.gameData.getData("assets/level_editor.txt") == null? "":Platformer.gameData.getData("assets/level_editor.txt")/*new File(getClass().getResource("assets/leaderboard.txt").getPath())*/);
		if(in.hasNextLine()) {
			String[] arr = in.nextLine().split(" ");
			return arr;
		}
		return null;
	}
	public void addLeaderboard(int ... i) throws IOException {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int[] arr = readLeaderboard();
		if(arr != null)
			for(int n: arr)
				temp.add(n);
		for(int n: i)
			temp.add(n);
		Collections.sort(temp, Collections.reverseOrder());
		//FileWriter out = new FileWriter(new File(getClass().getResource("assets/leaderboard.txt").getPath()), false);
		String s = "";
		for(int j = 0; j < 10; j++) {
			if(j < temp.size()-1)
				s += temp.get(j)+" ";
			else if(j < temp.size())
				s += ""+temp.get(j);
		}
		Platformer.gameData.addData("assets/leaderboard.txt", s);
	}
	public void addToLevelIndex(String str) throws IOException {
		ArrayList<String> temp = new ArrayList<String>();
		String[] arr = readLevelIndex();
		if(arr != null)
			for(String n: arr)
				if(!n.equals(str))
					temp.add(n);
		temp.add(str);
		//FileWriter out = new FileWriter(new File(getClass().getResource("assets/leaderboard.txt").getPath()), false);
		String s = "";
		for(int j = 0; j < 10; j++) {
			if(j < temp.size()-1)
				s += temp.get(j)+" ";
			else if(j < temp.size())
				s += ""+temp.get(j);
		}
		Platformer.gameData.addData("assets/level_editor.txt", s);
	}
	enum EditTool
	{
		PLAYER(0, null), BUILDING(1, null), BLOCK(2, null), ENEMY(3, null), PORTAL(4, null), ERASER(5, null), DUMB(0, ENEMY), SMART(1, ENEMY), ANIMAL_CONTROL(2, ENEMY), DOG(3, ENEMY), GRANNY(4, ENEMY), BACK(5, ENEMY);
		private int i;
		private EditTool parent;
		private boolean hasChildren;
		EditTool(int i, EditTool parent)
		{
			this.i = i;
			this.parent = parent;
			if(parent != null)
				parent.hasChildren = true;
		}
		public static EditTool choseTool(int i, EditTool parent)
		{
			for(EditTool e : EditTool.values())
			{
				if((parent != null && parent.hasChildren && parent.equals(e.parent) && e.i == i) || (parent == null && e.i == i) || (parent != null && !parent.hasChildren && e.i == i))
					return e;
			}
			return parent;
		}
		public boolean hasChildren() {
			return hasChildren;
		}
		public boolean hasParent() {
			return parent != null;
		}
		public EditTool getParent() {
			return parent;
		}
		public int getIndex() {
			return i;
		}
	}
}
