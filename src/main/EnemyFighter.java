package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import entity.EntityTypeA;
import entity.EntityTypeB;

public class EnemyFighter extends GameObject implements EntityTypeB{
	private Skins skin;
	
	Random r = new Random();
	private Game game;
	private Controller controller;
	private double speed = 0.3 + 3 * r.nextDouble() ;
	public double is_shooting = r.nextInt(60);
	
	public EnemyFighter(double x, double y, Skins skin, Controller c, Game game){
		super(x,y);
		this.game = game;
		this.controller = c;
		this.skin = skin;
	}
	public double getX(){
		return x;
	}
	public void shoot(){
		if(is_shooting == 0){
		controller.addEntity(new EnemyLaser(x, y, skin, game));
		is_shooting++;
		}
		else if(is_shooting == 190){
			is_shooting = 0;
		}
		else{
			is_shooting++;
		}
	}
	
	
	public void tick(){
		x -= speed;
		shoot();
		for(int n = 0; n < game.ea.size(); n++){
			EntityTypeA tempa = game.ea.get(n);
			if(GamePhysics.Collision(this, tempa)){
				controller.removeEntity(this);
				controller.removeEntity(tempa);
				game.setKills(game.getKills()+1);
			}
		}
	}
	public void render(Graphics g){
		g.drawImage(skin.enemy1, (int)x, (int)y, null);
	}
	public double getY() {
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int) y, 64, 64);
	}
}
