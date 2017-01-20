package interactions;

import serverSide.Character;
import serverSide.Tile;

public class Woodcutting extends Interaction {
	public static final int INTERACTION_ID = 4001;
	public boolean interact(Character performer, Tile tile) {
		if(performer.getContainer().canWoodcut()) {
			if(getDistance(performer, tile) <= 1) {
				if(tile.hasInteraction(this)) {
					tile.removeInteraction(this);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int getInteractionID() {		
		return INTERACTION_ID; // The ID of woodcutting. 
	}
}
