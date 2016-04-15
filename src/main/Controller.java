package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	private LinkedList<Laser> l = new LinkedList<Laser>();
	
	Laser tempLaser;
	
	Game game;
	
	public Controller(Game game){
		this.game = game;
		addLaser(new Laser(100, 300, game));
	}
	
	public void tick(){
		for(int i = 0; i < l.size(); i++){
			tempLaser = l.get(i);
			
			tempLaser.tick();
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < l.size(); i++){
			tempLaser = l.get(i);
			
			tempLaser.render(g);
		}
	}
	public void addLaser(Laser laser){
		l.add(laser);
	}
	public void removeLaser(Laser laser){
		l.remove(laser);
	}
}
