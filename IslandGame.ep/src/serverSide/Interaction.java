package serverSide;

import java.util.ArrayList;
import java.util.List;

public class Interaction {
	public static final int WOODCUTTING = 0;


	protected static boolean interact(int interaction, Character performer, Tile tile) {
		if(interaction == WOODCUTTING && performer.getContainer().getProperty(Item.WOODCUTTING_POWER_INDEX) > 0 &&
				 getDistance(performer, tile) <= 1 && tile.getProperty(Tile.WOODCUTTABLE_INDEX) > 0) {
			tile.changeProperty(Tile.WOODCUTTABLE_INDEX, 0);
			return true;
		}
		return false;
	}


	protected static List<Integer> getInteractions(Tile tile) {
		List<Integer> interactions = new ArrayList<Integer>();
		if(tile.getProperty(Tile.WOODCUTTABLE_INDEX) > 0) {
			interactions.add(0);
		}
		return interactions;
	}
	
	/**
	 * This calculates the distance between a character and a tile. Will not follow diagonals.  
	 * 
	 * @param character The character. 
	 * @param tile The tile. 
	 * @return The distance between them. 
	 */
	private static int getDistance(Character character, Tile tile) {
		return Math.abs(character.getXCoord() - tile.getXCoord()) + Math.abs(character.getYCoord() - tile.getYCoord());
	}
}
