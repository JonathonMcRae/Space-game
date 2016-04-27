package main;


import java.awt.Canvas;
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
import entity.EntityTypeC;
import entity.EntityTypeD;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int Width = 800;
	public static final int Height = 600;
	public final String Title = "spaaaaaace";

	private boolean gameon = false;
	private Thread thread;


	private BufferedImage image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet =  null;
	private BufferedImage background = null;
	private BufferedImage level0 = null;
	private BufferedImage level1 = null;
	private BufferedImage level2 = null;
	private BufferedImage level3 = null;
	private BufferedImage level4 = null;
	private BufferedImage level5 = null;
	private BufferedImage level6 = null;
	private BufferedImage gameOver = null;


	private int is_shooting = 0;
	private int is_shooting2 = 0;

	private int enemy_count = 1;
	private int Tiekills = 0;
	private int SDcount = 0;
	private int SDkills = 0;
	private int Score = 0;
	private int level = 0;
	private int delay = 0;
	public LinkedList<EntityTypeA> ea;
	public LinkedList<EntityTypeB> eb;
	public LinkedList<EntityTypeC> ec;
	public LinkedList<EntityTypeD> ed;


	public static enum STATE {
		MENU,
		GAME,
		END
	};
	public static STATE State = STATE.MENU;

	Random r = new Random();



	//private BufferedImage player;
	private Player p;
	private Player2 p2;
	private Controller c;
	private Skins tp;
	private Menu menu;
	private ShowScore showscore;
	public static int numberOfPlayers = 2;
	public static boolean padraicmode = false;

	public void init()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{

			spriteSheet = loader.loadimage("/sprite_sheet.png");
			background = loader.loadimage("/background.png");
			level0 = loader.loadimage("/level0.png");
			level1 = loader.loadimage("/Space1.png");
			level2 = loader.loadimage("/Space2.png");
			level3 = loader.loadimage("/Space3.png");
			level4 = loader.loadimage("/Space4.png");
			level5 = loader.loadimage("/Space5.png");
			level6 = loader.loadimage("/Space6.png");
			gameOver = loader.loadimage("/GameOver.png");





		}catch(IOException e){
			e.printStackTrace();
		}
		addKeyListener(new KeyboardInput(this));
		this.addMouseListener(new MouseInput(this));
		tp = new Skins(this);
		c = new Controller(tp, this);
		menu = new Menu();
		showscore = new ShowScore(this);

		if(numberOfPlayers == 2){
			p2 = new Player2(300, 300, tp, this, c);
		}
		p = new Player(200, 200, tp, this, c);

		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		ed = c.getEntityD();
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
			tp.getTextures();
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
		if(State == STATE.GAME){
			p.tick();
			c.tick();
			if(numberOfPlayers == 2){
				p2.tick();
			}
		}
		if(Tiekills >= enemy_count){
			if (enemy_count == 10){
				SDcount++;
				c.createSD(SDcount);
				enemy_count = 2;
				Tiekills = 0;
				c.createEnemy(enemy_count);
			}else{
				enemy_count +=1;
				Tiekills = 0;
				c.createEnemy(enemy_count);
			}
		}
		if(Score > 10){
			level = 1;
		}
		if(Score > 500){
			level = 2;
		}
		if(Score > 1000){
			level = 3;
		}
		if(Score > 10000){
			level = 4;
		}
		if(Score > 100000){
			level = 5;
		}
		if(Score > 1000000){
			level = 6;
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

		if(level == 0){
			g.drawImage(level0, 0,0, null);
		}
		if(level == 1){
			g.drawImage(level1, 0,0, null);
		}
		if(level == 2){
			g.drawImage(level2, 0,0, null);
		}
		if(level == 3){
			g.drawImage(level3, 0,0, null);
		}
		if(level == 4){
			g.drawImage(level4, 0,0, null);
		}
		if(level == 5){
			g.drawImage(level5, 0,0, null);
		}
		if(level > 5){
			g.drawImage(level6, 0,0, null);
		}
		//g.drawImage(player, 100, 100, this);
		if(State == STATE.GAME){
			p.render(g);
			c.render(g);
			if(numberOfPlayers == 2){
				p2.render(g);
			}
		}else if(State == STATE.MENU){
			g.drawImage(background, 0,0, null);
			menu.render(g);
			delay = 0;
			enemy_count = 1;
			Tiekills = 0;
			SDcount = 0;
			SDkills = 0;
			Score = 0;
			level = 0;
		}else if(State == STATE.END){
			g.drawImage(gameOver,0,0, null);
			showscore.render(g);
			if(delay == 1000){
				State = STATE.MENU;
			}
			delay++;
		}
		/////////////////
		g.dispose();
		bufferstrat.show();

	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(State == STATE.GAME){
			if(key == KeyEvent.VK_RIGHT){
				p.setVelX(6);

			} else if(key == KeyEvent.VK_LEFT){
				p.setVelX(-6);
			} else if(key == KeyEvent.VK_DOWN){
				p.setVelY(6);
			} else if(key == KeyEvent.VK_UP){
				p.setVelY(-6);
			} else if(key == KeyEvent.VK_L ){
				if(is_shooting == 3){
					is_shooting = 0;
				}
				if(is_shooting == 0){
					c.addEntity(new Laser(p.getX(),p.getY(), tp, this));}
				is_shooting++;
			} 
			if(numberOfPlayers == 2){
				if(key == KeyEvent.VK_A){
					p2.setVelX(-4);
				} else if(key == KeyEvent.VK_D){
					p2.setVelX(4);
				}else if(key == KeyEvent.VK_S){
					p2.setVelY(4);
				}else if(key == KeyEvent.VK_W){
					p2.setVelY(-4);
				}else if(key == KeyEvent.VK_SPACE ){
					if(is_shooting2 == 4){
						is_shooting2 = 0;
					}
					if(is_shooting2 == 0){
						c.addEntity(new Laser(p2.getX(),p2.getY(), tp, this));}
					is_shooting2++;
				}
			}
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
		} else if(key == KeyEvent.VK_L){
			is_shooting = 0;
		}
		if(numberOfPlayers == 2){
			if(key == KeyEvent.VK_A){
				p2.setVelX(0);
			} else if(key == KeyEvent.VK_D){
				p2.setVelX(0);
			}else if(key == KeyEvent.VK_S){
				p2.setVelY(0);
			}else if(key == KeyEvent.VK_W){
				p2.setVelY(0);
			}else if(key == KeyEvent.VK_SPACE ){

				is_shooting2 = 0;


			}}

	}

	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	public int getScore(){
		return Score;
	}
	public void setScore(int score){
		Score = score;
	}
	public int getEnemy_count() {
		return enemy_count;
	}
	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	public int getSDKills(){
		return SDkills;
	}
	public void setSDKills(int SDkill){
		SDkills = SDkill;
	}
	public int getTieKills() {
		return Tiekills;
	}
	public void setTieKills(int kills) {
		this.Tiekills = kills;
	}
	public boolean getPadraic(){
		return padraicmode;
	}

}
