package interactions;

import serverSide.Character;
import serverSide.Tile;


/** 
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
	
	/**
	 * TODO Implement hashcode. 
	 * Not sure how we want it implemented tho. 
	 * Maybe just return interactionID. 
	 */
	public int hashCode() {
		return super.hashCode();
	}
}
