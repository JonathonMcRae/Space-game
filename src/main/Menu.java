package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Menu {	
	
	public void render(Graphics g){
		Font title_font = new Font("comic sans ms", Font.BOLD, 100);
		g.setFont(title_font);
		g.setColor(Color.white);
		g.drawString("spaaaaaace", 145, 150);
		
		Font single_font = new Font("comic sans ms", Font.BOLD, 50);
		g.setFont(single_font);
		g.drawString("single player", 265, 275);
		
		Font multi_font = new Font("comic sans ms", Font.BOLD, 50);
		g.setFont(multi_font);
		g.drawString("multiplayer", 285, 375);
		
		Font quit_font = new Font("comic sans ms", Font.BOLD, 50);
		g.setFont(quit_font);
		g.drawString("quit", 365, 475);
	}
	
}
