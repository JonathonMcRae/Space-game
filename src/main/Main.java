package main;

import java.awt.Dimension;

import javax.swing.*;
public class Main extends JFrame{
	public static void main(String[] args){
		new Game();
	}
	public void Game(){
	JFrame container = new JFrame("Padraic");
	JPanel panel = (JPanel) container.getContentPane();
	Dimension D = new Dimension(800,600);
	panel.setPreferredSize(new Dimension(800,600));
	
	
	}
	
}
