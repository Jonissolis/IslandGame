package cookieGame;

import java.util.Random;

import interactions.Interaction;
import serverSide.Character;
import serverSide.IslandGrid;
import serverSide.Tile;

public class EatCookie extends Interaction {

	public static final int INTERACTION_ID = 7;
	public static int xCoord;
	public static int yCoord;
	private IslandGrid islandGrid;
	public EatCookie(IslandGrid islandGrid) {
		this.islandGrid = islandGrid;
	}
	
	@Override
	public boolean interact(Character performer, Tile tile) {
		if(getDistance(performer, tile) == 0 && tile.hasInteraction(this)) {
			tile.removeInteraction(this);
			randomizeNewCookie();
			return true;
		}
		return false;
	}

	@Override
	public int getInteractionID() {
		return INTERACTION_ID;
	}
	
	public void randomizeNewCookie() {
		Random random = new Random();
		while(true) {
			xCoord = random.nextInt(islandGrid.getWidth());
			yCoord = random.nextInt(islandGrid.getHeight());
			if(addCookie(xCoord, yCoord)) {
				return;
			}
		}
	}
	private boolean addCookie(int x, int y) {
		Tile newTile = islandGrid.getTile(x, y);
		if(!newTile.isBlocked()) {
			newTile.addInteraction(this);
			return true;
		}
		return false;
	}
	
}
