package serverSide;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import clientSide.BaseControls;

public class Client implements Observer {
	private Character character;
	private IslandGrid islandGrid;
	private BaseControls client;
	
	public Client(IslandGrid islandGrid) {
		this.islandGrid = islandGrid;
		character = new Character(islandGrid);
	}
	public void setClient(BaseControls client) {
		this.client = client;
	}
	public boolean moveWest() {
		return character.moveWest();
	}
	public boolean moveEast() {
		return character.moveEast();
	}
	public boolean moveNorth() {
		return character.moveNorth();
	}
	public boolean moveSouth() {
		return character.moveSouth();
	}
	public int getXCoord() {
		return character.getXCoord();
	}
	public int getYCoord() {
		return character.getYCoord();
	}
	public boolean isTileOccupied(int x, int y) {
		return islandGrid.getTile(x, y).isOccupied();
	}
	public boolean isTileBlocked(int x, int y) {
		return islandGrid.getTile(x, y).isBlocked();
	}
	
	/**
	 * Checks which interactions that are available from a tile. 
	 * @param x The x coordinate of the tile. 
	 * @param y The y coordinate of the tile. 
	 * @return A list of interaction IDs. 
	 */
	public Set<Integer> getInteractions(int x, int y) {
		Tile tile = islandGrid.getTile(x, y);
		if(tile != null) {
			return tile.getInteractions();
		}
		return null;
	}
	
	/**
	 * Attempts to perform an interaction. Does nothing if unable. 
	 * 
	 * @param interactionID The ID of the interaction. 
	 * @param x The x coordinate of the tile where the interaction is to be performed. 
	 * @param y The y coordinate of the tile where the interaction is to be performed. 
	 * @return True if the interaction was performed. Otherwise false. 
	 */
	public boolean performInteraction(int interactionID, int x, int y) {
		Tile tile = islandGrid.getTile(x, y);
		if(tile != null) {
			return tile.interact(interactionID, character);
		}
		return false;
	}

	/**
	 * Adds this client as observer on a tile causing it to call the clients updateTile() method. 
	 * @param x The x coordinate of the tile to be observed. 
	 * @param y The y coordinate of the tile to be observed.
	 */
	public void observeTile(int x, int y) {
		islandGrid.getTile(x, y).addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Tile) {
			Tile tile = (Tile) o;
			if(client != null) {
				client.updateTile(tile.getXCoord(), tile.getYCoord());
			}
		}
	}
	
	public int getGridWidth() {
		return islandGrid.getWidth();
	}
	public int getGridHeight() {
		return islandGrid.getHeight();
	}
}
