package game2048;

import java.awt.Graphics;

import gameEngine.GameEngine;
import gameEngine.GameObject;
import gameEngine.Keyboard;

public class Game2048 extends GameObject{
	static GameEngine game;
	static Block[][] blocks = new Block[4][4];
	private static int currentScore;
	public static boolean up, down, left, right;
	public static void main(String[] args)
	{
		Game2048 game2048 = new Game2048();
	}
	public Game2048()
	{
		game = new GameEngine();
		game.setTitle("2048!");
		game.setWidth(470);
		game.setHeight(470);
		new Background();
		blockStart();
		game.start();
	}
	public static void blockStart()
	{
		int r = (int)(Math.random()*4);
		int c = (int)(Math.random()*4); 
		blocks[r][c] = new Block(r, c);
	}
	private static void addBlock()
	{
		if(!isAllFull())
		{
			int r = (int)(Math.random()*4);
			int c = (int)(Math.random()*4);
			while(blocks[r][c] != null)
			{
				r = (int)(Math.random()*4);
				c = (int)(Math.random()*4);
			}
			blocks[r][c] = new Block(r, c);
		}
	}
	public static void render(Graphics g)
	{
		up = Keyboard.up;
		down = Keyboard.down;
		left = Keyboard.left;
		right = Keyboard.right;
		if(down)
		{
			for(int r = 0; r < blocks.length; r++)
			{
				for(int c = blocks.length-2; c >= 0; c--)
				{
					if(blocks[r][c] != null)
					{
						int i = 1;
						while(c+i < blocks.length && blocks[r][c+i] == null)
						{
							blocks[r][c+(i-1)].col++;
							blocks[r][c+i] = blocks[r][c+(i-1)];
							blocks[r][c+(i-1)] = null;
							i++;
						}
						if(c+i < blocks.length && blocks[r][c+i] != null)
						{
							if(blocks[r][c+i].value == blocks[r][c+(i-1)].value)
							{
								blocks[r][c+i].value += blocks[r][c+(i-1)].value;
								currentScore += blocks[r][c+i].value;
								blocks[r][c+(i-1)] = null;
								blocks[r][c+i].valueSizeChanged = true;
							}
						}
					}
				}
			}
			addBlock();
			up = false;
			down = false;
			left = false;
			right = false;
		}
		else if(up)
		{
			for(int r = 0; r < blocks.length; r++)
			{
				for(int c = 1; c < blocks[0].length; c++)
				{
					if(blocks[r][c] != null)
					{
						int i = 1;
						while(c-i >= 0 && blocks[r][c-i] == null)
						{
							blocks[r][c-(i-1)].col--;
							blocks[r][c-i] = blocks[r][c-(i-1)];
							blocks[r][c-(i-1)] = null;
							i++;
						}
						if(c-i >= 0 && blocks[r][c-i] != null)
						{
							if(blocks[r][c-i].value == blocks[r][c-(i-1)].value)
							{
								blocks[r][c-i].value += blocks[r][c-(i-1)].value;
								currentScore += blocks[r][c-i].value;
								blocks[r][c-(i-1)] = null;
								blocks[r][c-i].valueSizeChanged = true;
							}
						}
					}
				}
			}
			addBlock();
			up = false;
			down = false;
			left = false;
			right = false;
		}
		else if(left)
		{
			for(int r = 1; r < blocks.length; r++)
			{
				for(int c = 0; c < blocks[0].length; c++)
				{
					if(blocks[r][c] != null)
					{
						int i = 1;
						while(r-i >= 0 && blocks[r-i][c] == null)
						{
							blocks[r-(i-1)][c].row--;
							blocks[r-i][c] = blocks[r-(i-1)][c];
							blocks[r-(i-1)][c] = null;
							i++;
						}
						if(r-i >= 0 && blocks[r-i][c] != null)
						{
							if(blocks[r-i][c].value == blocks[r-(i-1)][c].value)
							{
								blocks[r-i][c].value += blocks[r-(i-1)][c].value;
								currentScore += blocks[r-i][c].value;
								blocks[r-(i-1)][c] = null;
								blocks[r-i][c].valueSizeChanged = true;
							}
						}
					}
				}
			}
			addBlock();
			up = false;
			down = false;
			left = false;
			right = false;
		}
		else if(right)
		{
			for(int r = blocks.length-2; r >= 0; r--)
			{
				for(int c = 0; c < blocks[0].length; c++)
				{
					if(blocks[r][c] != null)
					{
						int i = 1;
						while(r+i < blocks.length && blocks[r+i][c] == null)
						{
							blocks[r+(i-1)][c].row++;
							blocks[r+i][c] = blocks[r+(i-1)][c];
							blocks[r+(i-1)][c] = null;
							i++;
						}
						if(r+i < blocks.length && blocks[r+i][c] != null)
						{
							if(blocks[r+i][c].value == blocks[r+(i-1)][c].value)
							{
								blocks[r+i][c].value += blocks[r+(i-1)][c].value;
								currentScore += blocks[r+i][c].value;
								blocks[r+(i-1)][c] = null;
								blocks[r+i][c].valueSizeChanged = true;
							}
						}
					}
				}
			}
			addBlock();
			up = false;
			down = false;
			left = false;
			right = false;
		}
	}
	public static boolean didLose()
	{
		for(int r = 0; r < blocks.length; r++)
		{
			for(int c = 0; c < blocks[0].length; c++)
			{
				if(blocks[r][c] == null)
					return false;
				else if(canMove(r, c))
					return false;
			}
		}
		return true;
	}
	private static boolean isAllFull()
	{
		for(int r = 0; r < blocks.length; r++)
		{
			for(int c = 0; c < blocks[0].length; c++)
			{
				if(blocks[r][c] == null)
					return false;
			}
		}
		return true;
	}
	private static boolean canMove(int r, int c)
	{
		if(r-1 >= 0 && blocks[r-1][c] != null && blocks[r][c].value == blocks[r-1][c].value)
			return true;
		if(r+1 < blocks.length && blocks[r+1][c] != null && blocks[r][c].value == blocks[r+1][c].value)
			return true;
		if(c+1 < blocks[0].length && blocks[r][c+1] != null && blocks[r][c].value == blocks[r][c+1].value)
			return true;
		if(c-1 >= 0 && blocks[r][c-1] != null && blocks[r][c].value == blocks[r][c-1].value)
			return true;
		return false;
	}
}
