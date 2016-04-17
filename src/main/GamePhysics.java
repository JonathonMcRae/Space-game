package main;

import java.util.LinkedList;

import entity.EntityTypeA;
import entity.EntityTypeB;

public class GamePhysics {
	public static boolean Collision(EntityTypeA enta, LinkedList<EntityTypeB> bb){
		for(int n = 0; n <bb.size(); n++ ){
			if(enta.getBounds().intersects(bb.get(n).getBounds())){
				return true;
			}
		}
		return false;
	}
	public static boolean Collision(EntityTypeB entb, LinkedList<EntityTypeA> aa){
		for(int n = 0; n <aa.size(); n++ ){
			if(entb.getBounds().intersects(aa.get(n).getBounds())){
				return true;
			}
		}
		return false;
	}
}
