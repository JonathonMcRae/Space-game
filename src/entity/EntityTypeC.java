package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
//used for Star Destroyer
public interface EntityTypeC {

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	public double getX();
	public double getY();
}
