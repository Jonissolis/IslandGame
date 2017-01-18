package serverSide;

import clientSide.SimpleAI;

public class Main {

	public static void main(String[] args) {
		IslandGrid islandGrid = new IslandGrid(20, 20);
		new ViewingGrid(islandGrid);
		for(int i = 0; i < 10; i++) {
			new Thread(new SimpleAI(new Client(islandGrid))).start();
		}
	}

}
