package main;

import java.awt.Graphics;
import java.awt.Rectangle;

import entity.EntityTypeD;

public class EnemyLaser extends GameObject implements EntityTypeD{
	
	private Skins skin;
	public EnemyLaser(double x, double y, Skins skin, Game game){
		super(x,y);
		this.skin = skin;
		
	}
	
	public void tick(){
		x-=4;
	}
	public void render(Graphics g){
		g.drawImage(skin.enemyLaser, (int)x, (int)y, null);
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
