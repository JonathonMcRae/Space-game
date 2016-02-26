package main;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;




import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Game implements ActionListener {
	BufferStrategy strategy;
	public Game(){
	JFrame container = new JFrame("CSE 2102");
	container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel panel = (JPanel) container.getContentPane();
	panel.setPreferredSize(new Dimension(800,600));
	panel.setLayout(null);
	
	panel.setBounds(0,0,800,600);
	
	panel.setIgnoreRepaint(true);
	
    JMenuBar jmenubar = new JMenuBar();

    JMenu jmGameOptions = new JMenu("Game");
    
    JMenuItem jmiOpen = new JMenuItem("Open");
    jmGameOptions.add(jmiOpen);
    jmiOpen.addActionListener(this);
    
    JMenuItem jmiClose = new JMenuItem("Close");
    jmGameOptions.add(jmiClose);
    jmiClose.addActionListener(this);
    
    JMenuItem jmiSave = new JMenuItem("Save");
    jmGameOptions.add(jmiSave);
    jmiSave.addActionListener(this);
    
    JMenuItem jmiExit = new JMenuItem("Exit");
    jmGameOptions.add(jmiExit);
    jmiExit.addActionListener(this);
    
    jmenubar.add(jmGameOptions);


    container.setJMenuBar(jmenubar);
    container.setVisible(true);
  
  
	
	container.pack();
	container.setResizable(false);
	

	}
	 public void actionPerformed(ActionEvent ae) {
		    String comStr = ae.getActionCommand();
		    System.out.println(comStr + " Selected");
	  }
}
