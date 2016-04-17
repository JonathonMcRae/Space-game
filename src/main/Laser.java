package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.EntityTypeA;

public class Laser extends GameObject implements EntityTypeA{
	
	private Skins skin;
	private Game game;
	
	
	
	public Laser(double x, double y, Skins skin, Game game){
		super(x,y);

		this.skin = skin;
		this.game = game;
		
	}
	
	public void tick(){
		x+=10;
	}
	public void render(Graphics g){
		g.drawImage(skin.laser, (int)x, (int)y, null);
	}
	public double getX(){
		return x;
	}

	public double getY() {
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int) y, 64, 64);
	}

}
