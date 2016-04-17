package main;

import java.awt.image.BufferedImage;


public class Skins {
	private int playerx = 1;
	private int playery = 1;
	public BufferedImage player;
	public BufferedImage laser;
	public BufferedImage enemy1;
	public BufferedImage padraic;
	
	private SpriteSheet ss = null;
	
	public Skins(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
		
	}
	private void getTextures(){
		player = ss.grabimage(playerx, playery, 64, 64);
		laser = ss.grabimage(2, 3, 64, 64);
		enemy1 = ss.grabimage(2, 1, 64, 64);
		padraic = ss.grabimage(2, 2, 64, 64);
		
	}
	
	public void setSelection(int x, int y){
		playerx = x;
		playery = y;
	}
	
	public int getPlayerX(){
		return playerx;
	}
	public int getPlayerY(){
		return playery;
	}
}
