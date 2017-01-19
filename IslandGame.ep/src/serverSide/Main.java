package serverSide;

import clientSide.SimpleAI;
import clientSide.WoodcuttingAI;

public class Main {

	public static void main(String[] args) {
		IslandGrid islandGrid = new IslandGrid(20, 20);
		new ViewingGrid(islandGrid);
		for(int i = 0; i < 1; i++) {
			new Thread(new WoodcuttingAI(new Client(islandGrid))).start();
			new Thread(new SimpleAI(new Client(islandGrid))).start();
			
		}
	}

}
