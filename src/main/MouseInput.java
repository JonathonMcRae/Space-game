package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	private Game game;

	public MouseInput(Game game){
		this.game = game;
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(game.State == game.State.MENU){
			if(mx >= 265 && mx <= 535 && my >= 225 && my <= 275)
			{
				Game.numberOfPlayers = 1;
				Game.padraicmode = false;
				Game.State = Game.STATE.GAME;
			}
			else if(mx >= 285 && mx <= 515 && my >= 325 && my <= 375)
			{
				Game.numberOfPlayers = 2;
				Game.padraicmode = false;
				Game.State = Game.STATE.GAME;
			}
			else if(mx >= 365 && mx <= 435 && my >= 425 && my <= 475)
			{
				System.exit(1);
			}
			else if(mx >= 610 && mx <= 650 && my >= 540 && my <= 580)
			{
				Game.numberOfPlayers = 1;
				Game.padraicmode = true;
				Game.State = Game.STATE.GAME;
			}
			else if(mx >= 0 && mx <= 40 && my >= 0 && my <= 40)
			{
				Game.numberOfPlayers = 2;
				Game.padraicmode = true;
				Game.State = Game.STATE.GAME;
			}
		}

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
