package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int Width = 800;
	public static final int Height = 600;
	public final String Title = "Padriaca";
	
	private boolean gameon = false;
	private Thread thread;
	
	
	private BufferedImage image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet =  null;
	
	
	private BufferedImage player;
	
	public void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			
			spriteSheet = loader.loadimage("/sprite_sheet.png");
			
		}catch(IOException e){
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		player = ss.grabimage(1, 1, 16, 16);
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
		
		g.drawImage(player, 100, 100, this);
		
		/////////////////
		g.dispose();
		bufferstrat.show();
		
	}
}
