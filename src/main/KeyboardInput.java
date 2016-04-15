package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//whenever a key is pressed this runs
public class KeyboardInput extends KeyAdapter{

	
	Game game;
	
	public KeyboardInput(Game game){
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
		
	}
	@Override
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}
	
}
