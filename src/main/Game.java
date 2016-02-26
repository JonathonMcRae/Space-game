package main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	public Game(){
	JFrame container = new JFrame("Padraic");
	JPanel panel = (JPanel) container.getContentPane();
	panel.setPreferredSize(new Dimension(800,600));
	panel.setLayout(null);
	
	panel.setBounds(0,0,800,600);
	setIgnoreRepaint(true);
	}
}
