package clientSide;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import cookieGame.EatCookie;
import interactions.Woodcutting;
import serverSide.ViewingGrid;

public class Controller {
	Player player;



	public Controller(ViewingGrid view, Player player) {
		this.player = player;
		view.getInputMap().put(KeyStroke.getKeyStroke("W"), "MoveNorth");
		view.getInputMap().put(KeyStroke.getKeyStroke("S"), "MoveSouth");
		view.getInputMap().put(KeyStroke.getKeyStroke("D"), "MoveEast");
		view.getInputMap().put(KeyStroke.getKeyStroke("A"), "MoveWest");
		view.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "ChopTree");
		view.getInputMap().put(KeyStroke.getKeyStroke("R"), "EatCookie");
		view.getActionMap().put("MoveNorth", new MoveNorth());
		view.getActionMap().put("MoveSouth", new MoveSouth());
		view.getActionMap().put("MoveEast", new MoveEast());
		view.getActionMap().put("MoveWest", new MoveWest());
		view.getActionMap().put("ChopTree", new ChopTree());
		view.getActionMap().put("EatCookie", new EatCookies());


	}
	
	class MoveNorth extends AbstractAction {
		private static final long serialVersionUID = -8186489884720882366L;
		public void actionPerformed(ActionEvent e) {
			player.moveNorth();
		}
	}
	
	class MoveSouth extends AbstractAction {
		private static final long serialVersionUID = -4519132934297710536L;
		public void actionPerformed(ActionEvent e) {
			player.moveSouth();
		}
	}
	
	class MoveEast extends AbstractAction {
		private static final long serialVersionUID = -702732098973419595L;
		public void actionPerformed(ActionEvent e) {
			player.moveEast();
		}
	}
	
	class MoveWest extends AbstractAction {
		private static final long serialVersionUID = -3839848205817119577L;
		public void actionPerformed(ActionEvent e) {
			player.moveWest();
		}
	}
	
	class ChopTree extends AbstractAction {
		private static final long serialVersionUID = -3923709123371029306L;
		public void actionPerformed(ActionEvent e) {
			player.performInteraction(Woodcutting.INTERACTION_ID, player.getXCoord(), player.getYCoord());
		}
	}
	class EatCookies extends AbstractAction {
		private static final long serialVersionUID = -3923709123371029306L;
		public void actionPerformed(ActionEvent e) {
			player.performInteraction(EatCookie.INTERACTION_ID, player.getXCoord(), player.getYCoord());
		}
	}
}
