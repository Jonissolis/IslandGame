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
	public static Tile getTile(double blockedProb, double treeProb, int xCoord, int yCoord) {
		int[] properties = new int[3];
		properties[Tile.IS_BLOCKED_INDEX] = Math.random() < blockedProb ? 1 : 0;
		properties[Tile.IS_OCCUPIED_INDEX] = 0;
		properties[Tile.WOODCUTTABLE_INDEX] = Math.random() < treeProb ? 1 : 0;
		return new Tile(xCoord, yCoord, properties);
	}
}
