package interactions;

import serverSide.Character;
import serverSide.Tile;


/**
 * Class to handle interactions. Currently both has functionality to get all interactions available on a certain tile and functionality for performing interactions. 
 * I don't like this class. I will probably change it into an abstract class or interface and let each interaction have it's own class. 
 * 
 *
 */
public abstract class Interaction {
	private final int interactionID;


	public abstract boolean interact(Character performer, Tile tile);
	
	protected Interaction(int interactionID) {
		this.interactionID = interactionID; 
	}
	public final int getInteractionID() {
		return interactionID;
	}
	
	/**
	 * This calculates the distance between a character and a tile. Will not follow diagonals.  
	 * 
	 * @param character The character. 
	 * @param tile The tile. 
	 * @return The distance between them. 
	 */
	public static int getDistance(Character character, Tile tile) {
		return Math.abs(character.getXCoord() - tile.getXCoord()) + Math.abs(character.getYCoord() - tile.getYCoord());
	}
}
