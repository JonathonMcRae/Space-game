package main;

import java.awt.image.BufferedImage;


public class Skins {
	private int playerx = 1;
	private int playery = 1;
	private int player2x = 1;
	private int player2y = 1;
	public BufferedImage player;
	public BufferedImage laser;
	public BufferedImage enemy1;
	public BufferedImage starDestroyer;
	public BufferedImage player2;
	public BufferedImage enemyLaser;
	
	Game game;
	
	private SpriteSheet ss = null;
	
	public Skins(Game game) {
		this.game = game;
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
		
	}
	private void getTextures(){
		if(game.getPadraic()){
			player = ss.grabimage(2, 2, 64, 64);
			laser = ss.grabimage(3, 2, 64, 64);
			enemy1 = ss.grabimage(1, 3, 64, 64);
			player2 = ss.grabimage(2, 2, 64, 64);
			enemyLaser = ss.grabimage(3, 2, 64, 64);
		}else{
		player = ss.grabimage(playerx, playery, 64, 64);
		laser = ss.grabimage(2, 3, 64, 64);
		enemy1 = ss.grabimage(2, 1, 64, 64);
		player2 = ss.grabimage(3, 1, 64, 64);
		starDestroyer = ss.grabimage(3, 3, 128, 128);
		enemyLaser = ss.grabimage(1, 4, 64, 64);
		}
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
	public void setSelection2(int x, int y){
		player2x = x;
		player2y = y;
	}
	
	public int getPlayer2X(){
		return player2x;
	}
	public int getPlayer2Y(){
		return player2y;
	}
}
