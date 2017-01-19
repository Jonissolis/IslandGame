package serverSide;

import interactions.Woodcutting;

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
		int[] properties = new int[Tile.NUMBER_OF_PROPERTIES];
		properties[Tile.IS_BLOCKED_INDEX] = Math.random() < blockedProb ? 1 : 0;
		properties[Tile.IS_OCCUPIED_INDEX] = 0;
		Tile tile = new Tile(xCoord, yCoord, properties);
		if(Math.random() < treeProb) {
			tile.addInteraction(new Woodcutting());
		}
		return tile;
	}
}
