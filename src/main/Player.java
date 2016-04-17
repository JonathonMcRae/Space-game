package main;
import java.awt.Graphics;
import java.awt.Rectangle;

import entity.EntityTypeA;

public class Player extends GameObject implements EntityTypeA{
	
	private double velX = 0;
	private double velY = 0;
	
	
	private Skins skin;
	private Game game;
	
	public Player(double x, double y, Skins skin, Game game){
		super(x,y);
		this.skin = skin;
		this.game = game;
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if (x <= 0 ){
			x = 0;
		}
		if (x >= 736){
			x = 736;
		}
		if (y <= 0){
			y = 0;
		}
		if (y >= 536){
			y = 536;
		}
		if(GamePhysics.Collision(this, game.eb)){
			System.out.println("LOSE");
		}
	}
	
	public void render(Graphics g){
		g.drawImage(skin.player, (int)x, (int)y, null);
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
		
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public void setVelY(double velY){
		this.velY = velY;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int) y, 64, 64);
	}

}
