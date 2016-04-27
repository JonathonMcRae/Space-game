package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class ShowScore {	
	private Game game;
	private int shift = 0;
	
	public ShowScore(Game game){
		this.game = game;
	}
	public void render(Graphics g){
		Font title_font = new Font("comic sans ms", Font.BOLD, 100);
		g.setFont(title_font);
		g.setColor(Color.white);
		if(game.getScore() < 100){
			shift = 0;
		}
		if(game.getScore()>= 100){
			shift =  -50;
		}
		if(game.getScore() >= 1000){
			shift = -75;
		}
		if(game.getScore() >= 10000){
			shift = -100;
		}
		g.drawString("SCORE: " + Integer.toString(game.getScore()), 145+shift, 450);
		
		
	}
	
}