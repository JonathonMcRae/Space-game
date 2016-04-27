package main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import entity.EntityTypeA;
import entity.EntityTypeB;
import entity.EntityTypeC;
import entity.EntityTypeD;

public class Controller {
	private LinkedList<EntityTypeA> ea = new LinkedList<EntityTypeA>();
	private LinkedList<EntityTypeB> eb = new LinkedList<EntityTypeB>();
	private LinkedList<EntityTypeC> ec = new LinkedList<EntityTypeC>();
	private LinkedList<EntityTypeD> ed = new LinkedList<EntityTypeD>();


	EntityTypeA tempea;
	EntityTypeB tempeb;
	EntityTypeC tempec;
	EntityTypeD temped;
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
	public void reset(){
		ea.clear();
		eb.clear();
		ec.clear();
		ed.clear();
		
	}
	
	
	public void createSD(int SDcount){
		for(int n = 0; n < SDcount; n++){
			addEntity(new StarDestroyer(810, r.nextInt(400), skin, this, game));
		}
	}
	public void createStarDestroyer(int SDcount){
		for(int i = 0; i < SDcount; i++){
			addEntity(new StarDestroyer(810, r.nextInt(400), skin, this, game));

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
		for(int n = 0; n < ec.size(); n++){
			tempec = ec.get(n);
			
			tempec.tick();
		}
		for(int n = 0; n < ed.size(); n++){
			temped = ed.get(n);
			
			temped.tick();
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
		for(int n = 0; n < ec.size(); n++){
			tempec = ec.get(n);
			
			tempec.render(g);
		}
		for(int n = 0; n < ed.size(); n++){
			temped = ed.get(n);
			
			temped.render(g);
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
	public void addEntity(EntityTypeC en){
		ec.add(en);
	}
	public void removeEntity(EntityTypeC en){
		ec.remove(en);
	}
	public LinkedList<EntityTypeC> getEntityC(){
		return ec;
	}
	public void addEntity(EntityTypeD en){
		ed.add(en);
	}
	public void removeEntity(EntityTypeD en){
		ed.remove(en);
	}
	public LinkedList<EntityTypeD> getEntityD(){
		return ed;
	}

}

