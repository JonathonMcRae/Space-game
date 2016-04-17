package main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import entity.EntityTypeA;
import entity.EntityTypeB;

public class Controller {
	private LinkedList<EntityTypeA> ea = new LinkedList<EntityTypeA>();
	private LinkedList<EntityTypeB> eb = new LinkedList<EntityTypeB>();

	EntityTypeA tempea;
	EntityTypeB tempeb;
	Random r = new Random();
	private Skins skin;
	private Game game;
	
	public Controller(Skins skin, Game game){
		this.skin = skin;
		this.game = game;
	}
	public void createEnemy(int enemy_count){
		for(int i = 0; i < enemy_count; i++){
			addEntity(new EnemyFighter(810, r.nextInt(536), skin, this, game));

		}
	}
	
	public void tick(){
		for(int n = 0; n < ea.size(); n++){
			tempea = ea.get(n);
			
			tempea.tick();
		}
		for(int n = 0; n < eb.size(); n++){
			tempeb = eb.get(n);
			
			tempeb.tick();
		}
	}
	public void render(Graphics g){
		for(int n = 0; n < ea.size(); n++){
			tempea = ea.get(n);
			
			tempea.render(g);
		}
		for(int n = 0; n < eb.size(); n++){
			tempeb = eb.get(n);
			
			tempeb.render(g);
		}
	}
	public void addEntity(EntityTypeA en){
		ea.add(en);
	}
	public void removeEntity(EntityTypeA en){
		ea.remove(en);
	}
	public void addEntity(EntityTypeB en){
		eb.add(en);
	}
	public void removeEntity(EntityTypeB en){
		eb.remove(en);
	}
	public LinkedList<EntityTypeA> getEntityA(){
		return ea;
	}
	public LinkedList<EntityTypeB> getEntityB(){
		return eb;
	}

}

