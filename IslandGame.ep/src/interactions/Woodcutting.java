package interactions;

import serverSide.Character;
import serverSide.Item;
import serverSide.Tile;

public class Woodcutting extends Interaction{
	public static final int INTERACTION_ID = 4001;
	
	public Woodcutting() {
		super(INTERACTION_ID);
	}
	
	public boolean interact(Character performer, Tile tile) {
		if(performer.getContainer().getProperty(Item.WOODCUTTING_POWER_INDEX) > 0) {
			if(getDistance(performer, tile) <= 1) {
				if(tile.hasInteraction(this)) {
					tile.removeInteraction(this);
					return true;
				}
			}
		}
		return false;
	}
}
