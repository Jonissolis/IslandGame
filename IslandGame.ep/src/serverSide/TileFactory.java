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
	 * @param treeProb A double between 0 and 1 with the probability of this tile having a tree. s 
	 * @param xCoord The x coordinate of this tile. 
	 * @param yCoord The y coordinate of this tile. 
	 * @return The tile generated. 
	 */
	public static Tile getTile(double blockedProb, double treeProb, int xCoord, int yCoord) {
		boolean blocked = Math.random() < blockedProb ? true : false;
		Tile tile = new Tile(xCoord, yCoord, blocked);
		if(Math.random() < treeProb) {
			tile.addInteraction(new Woodcutting());
		}
		return tile;
	}
}
