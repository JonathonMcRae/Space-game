package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Laser {
	private double x;
	private double y;
	
	BufferedImage laser;
	
	
	
	public Laser(double x, double y, Game game){
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		laser = ss.grabimage(2, 3, 64, 64);
	}
	
	public void tick(){
		x+=10;
		//y+=1;
	}
	public void render(Graphics g){
		g.drawImage(laser, (int)x, (int)y, null);
	}
}
