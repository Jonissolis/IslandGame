package serverSide;

import clientSide.Controller;
import clientSide.Player;
import clientSide.SimpleAI;
import clientSide.WoodcuttingAI;
import cookieGame.EatCookie;

public class Main {

	public static void main(String[] args) {
		IslandGrid islandGrid = new IslandGrid(20, 20);
		GameView gameView = new GameView(islandGrid);
		
		new Controller(gameView.getViewingGrid(), new Player(new Client(islandGrid)));
		new EatCookie(islandGrid).randomizeNewCookie();
		for(int i = 0; i < 1; i++) {
			new Thread(new WoodcuttingAI(new Client(islandGrid))).start();
			new Thread(new SimpleAI(new Client(islandGrid))).start();
			
		}
	}

}
