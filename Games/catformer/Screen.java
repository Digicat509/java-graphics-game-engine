package catformer;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import catformer.Level.Stage;
import gameEngine.Button;
//import catformer.Platformer.STATE;
import gameEngine.GameEngine;
import gameEngine.GameEngine.State;
import gameEngine.GameObject;
import gameEngine.Text;

public class Screen extends GameObject{
	ArrayList<Button> list = new ArrayList<Button>();
	int level = 1;
	Text levelText;
	public Screen() {
		Platformer.game.getHandeler().add(this, false);
	}
	public void updateState(State state)
	{
		Platformer.sound.audio.stop();
		Platformer.sound.creditsAudio.stop();
		Platformer.game.getHandeler().clear();
		Platformer.game.state = state;
		Platformer.game.getHandeler().add(Platformer.screen, false);
		if(state == State.TITLE) {
			level = 1;
			list = new ArrayList<Button>();
			new Text(Platformer.game.getTitle(), Platformer.game.getWidth()/2, 200, 40);
			list.add(new Button("Play", Platformer.game.getWidth()/2-50, 300, 100, 50, () -> {this.updateState(State.PLAYING);}));
			list.add(new Button(Platformer.game.getWidth()/2-150, 300, 50, 50, getClass().getResource("assets/LeftArrow.png"), () -> {if(this.level > 1)this.level--;else this.level = Stage.maxLevel;}));
			list.add(new Button(Platformer.game.getWidth()/2+100, 300, 50, 50, getClass().getResource("assets/RightArrow.png"), () -> {if(this.level < Stage.maxLevel)this.level++;else this.level = 1;}));
			levelText = new Text("Level:\t"+level, Platformer.game.getWidth()/2, 380, 20);
			list.add(new Button("Infinite Mode", Platformer.game.getWidth()/2-75, 420, 150, 50, () -> {this.updateState(State.PLAYING, Stage.INFINITE);}));
			list.add(new Button("Credits", Platformer.game.getWidth()/2-50, 500, 100, 50, () -> {this.updateState(State.CREDITS);}));
		}
		else if(state == State.PLAYING)
		{
			list = new ArrayList<Button>();
			Platformer.levelNum = level;
			Platformer.start(null);
			Platformer.sound.audio.setFramePosition(0);
			Platformer.sound.audio.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else if(state == State.CREDITS)
		{
			new Credits();
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
			list = new ArrayList<Button>();
			new Text(Platformer.game.getTitle(), Platformer.game.getWidth()/2, 200, 40);
			list.add(new Button("Play", Platformer.game.getWidth()/2-50, 280, 100, 50, () -> {this.updateState(State.PLAYING);}));
			list.add(new Button("Credits", Platformer.game.getWidth()/2-50, 380, 100, 50, () -> {this.updateState(State.CREDITS);}));
			list.add(new Button("Infinite Mode", Platformer.game.getWidth()/2-75, 480, 150, 50, () -> {this.updateState(State.PLAYING, Stage.INFINITE);}));
		}
		else if(state == State.PLAYING)
		{
			list = new ArrayList<Button>();
			Platformer.start(stage);
			Platformer.sound.audio.setFramePosition(0);
			Platformer.sound.audio.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else if(state == State.CREDITS)
		{
			new Credits();
			Platformer.sound.creditsAudio.setFramePosition(0);
			Platformer.sound.creditsAudio.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public void draw(Graphics g) {
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
	}
}
