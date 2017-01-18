package serverSide;


/**
 * 
 * A class used to generate tiles. 
 *
 */
public class TileFactory {
	
	/**
	 * Generates a tile. 
	 * 
	 * @param blockedProb A double between 0 and 1 with the probability of this tile being blocked. 
	 * @param xCoord The x coordinate of this tile. 
	 * @param yCoord The y coordinate of this tile. 
	 * @return The tile generated. 
	 */
	public static Tile getTile(double blockedProb, int xCoord, int yCoord) {
		if(Math.random() < blockedProb) {
			return new Tile(true, xCoord, yCoord);
		} else {
			return new Tile(false, xCoord, yCoord);
		}
	}
}
