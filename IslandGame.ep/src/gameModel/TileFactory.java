package gameModel;



public class TileFactory {
	
	public static Tile getTile(double blockedProb, int xCoord, int yCoord) {
		if(Math.random() < blockedProb) {
			return new Tile(true, xCoord, yCoord);
		} else {
			return new Tile(false, xCoord, yCoord);
		}
	}
}
