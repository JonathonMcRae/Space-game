package main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import entity.EntityTypeA;
import entity.EntityTypeB;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int Width = 800;
	public static final int Height = 600;
	public final String Title = "Star Wars";
	
	private boolean gameon = false;
	private Thread thread;
	
	
	private BufferedImage image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet =  null;
	private BufferedImage background = null;
	
	private int is_shooting = 0;
	
	private int enemy_count = 1;
	private int kills = 0;
	public LinkedList<EntityTypeA> ea;
	public LinkedList<EntityTypeB> eb;
	
	Random r = new Random();

	
	
	//private BufferedImage player;
	private Player p;
	private Controller c;
	private Skins tp;
	
	public void init()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			
			spriteSheet = loader.loadimage("/sprite_sheet.png");
			background = loader.loadimage("/background.png");
			
		}catch(IOException e){
			e.printStackTrace();
		}
		addKeyListener(new KeyboardInput(this));
		tp = new Skins(this);
		p = new Player(200, 200, tp, this);
		c = new Controller(tp, this);
		ea = c.getEntityA();
		eb = c.getEntityB();
		c.createEnemy(enemy_count);
	}
	
	
	
	public static void main(String args[]){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(Width, Height));
		
		JFrame screen = new JFrame(game.Title);
		screen.add(game);
		screen.pack();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setResizable(false);
		screen.setVisible(true);
		
		game.start();
	}

	
	private synchronized void start(){
		if (gameon)
			return;
		gameon = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if (!gameon)
			return;
		gameon = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	//game loop
	public void run() {
		init();
		long lasttime = System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(gameon){
			long now = System.nanoTime();
			delta += (now - lasttime) / ns;
			lasttime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta --;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates +" Ticks, FPS: " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
		
	}


	private void tick() {
		p.tick();
		c.tick();
		
		if(kills >= enemy_count){
			enemy_count +=2;
			kills = 0;
			c.createEnemy(enemy_count);
		}
	}
	private void render(){
		BufferStrategy bufferstrat = this.getBufferStrategy();
		if (bufferstrat == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bufferstrat.getDrawGraphics();
		/////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0,0, null);
		//g.drawImage(player, 100, 100, this);
		p.render(g);
		c.render(g);
		
		/////////////////
		g.dispose();
		bufferstrat.show();
		
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			p.setVelX(5);
			
		} else if(key == KeyEvent.VK_LEFT){
			p.setVelX(-5);
		} else if(key == KeyEvent.VK_DOWN){
			p.setVelY(5);
		} else if(key == KeyEvent.VK_UP){
			p.setVelY(-5);
		} else if(key == KeyEvent.VK_SPACE ){
			if(is_shooting == 3){
				is_shooting = 0;
			}
			if(is_shooting == 0){
			c.addEntity(new Laser(p.getX(),p.getY(), tp, this));}
			is_shooting++;
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
		} else if(key == KeyEvent.VK_LEFT){
			p.setVelX(0);
		} else if(key == KeyEvent.VK_DOWN){
			p.setVelY(0);
		} else if(key == KeyEvent.VK_UP){
			p.setVelY(0);
		} else if(key == KeyEvent.VK_SPACE ){
			is_shooting =0;
		}
		
	}
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	public int getEnemy_count() {
		return enemy_count;
	}
	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
}
