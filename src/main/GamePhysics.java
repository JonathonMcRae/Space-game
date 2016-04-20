package main;

import java.util.LinkedList;

import entity.EntityTypeA;
import entity.EntityTypeB;
import entity.EntityTypeC;

public class GamePhysics {
	public static boolean Collision(EntityTypeA enta, EntityTypeB bb){
		
		if(enta.getBounds().intersects(bb.getBounds())){
			return true;
		}
		
		return false;
	}
	public static boolean Collision(EntityTypeB entb, EntityTypeA aa){
		if(entb.getBounds().intersects(aa.getBounds())){
				
			return true;
			
		}
		return false;
	}
	public static boolean Collision(EntityTypeC entc, EntityTypeA aa){
		
			if(entc.getBounds().intersects(aa.getBounds())){
				return true;
			}
		
		return false;
	}
}
