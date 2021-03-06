package interactions;

import serverSide.Character;
import serverSide.Tile;


/** 
 *
 */
public abstract class Interaction {


	public abstract boolean interact(Character performer, Tile tile);
	
	public abstract int getInteractionID();
	
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
