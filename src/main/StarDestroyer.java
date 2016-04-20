package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import entity.EntityTypeA;
import entity.EntityTypeC;

public class StarDestroyer extends GameObject implements EntityTypeC{

	private Skins skin;
	private Game game;
	private Controller controller;
	private double speed = 0.3;
	private int health = 10;
	private int is_shooting = 0;
	Random r = new Random();
	
	public StarDestroyer(double x, double y, Skins skin, Controller c, Game game){
		super(x,y);
		this.game = game;
		this.controller = c;
		this.skin = skin;
	}
	public double getX(){
		return x;
	}
	
	public void tick(){
		x -= speed;
		shoot();
		for(int n = 0; n < game.ea.size(); n++){
		EntityTypeA tempa = game.ea.get(n);
		
		if(GamePhysics.Collision(this, tempa)){
			if(health ==1)
				{controller.removeEntity(this);
				game.setKills(game.getKills()+1);}
			else{health--;}
			controller.removeEntity(tempa);
			}
		}
		
	}
	public void shoot(){
		if(is_shooting == 0){
		controller.addEntity(new EnemyLaser(x, y, skin, game));
		is_shooting++;
		}
		else if(is_shooting == r.nextDouble()* 60 + 60){
			is_shooting = 0;
		}
		else{
			is_shooting++;
		}
	}
	public void render(Graphics g){
		g.drawImage(skin.starDestroyer, (int)x, (int)y, null);
	}
	public double getY() {
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int) y, 64, 64);
	}
}
