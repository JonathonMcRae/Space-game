package main;
import java.awt.Graphics;
import java.awt.Rectangle;

import entity.EntityTypeA;
import entity.EntityTypeD;

public class Player2 extends GameObject implements EntityTypeA{
	
	private double velX = 0;
	private double velY = 0;
	
	
	private Skins skin;
	private Game game;
	private Controller controller;
	private int health = 4;
	
	public Player2(double x, double y, Skins skin, Game game, Controller controller){
		super(x,y);
		this.skin = skin;
		this.game = game;
		this.controller = controller;
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
		for(int n = 0; n < game.ed.size(); n++){
			EntityTypeD tempa = game.ed.get(n);
			if(GamePhysics.Collision(this, tempa)){
				if(health == 1){
					controller.removeEntity(this);
					game.State = game.State.END;
					health = 5;
				}
				else{
					health--;
					controller.removeEntity(tempa);
				}
			}
		}
		
	}
	
	public void render(Graphics g){
		g.drawImage(skin.player2, (int)x, (int)y, null);
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

