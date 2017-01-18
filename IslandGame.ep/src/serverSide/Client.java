package serverSide;

import java.util.List;

public class Client {
	private Character character;
	private IslandGrid islandGrid;
	
	public Client(IslandGrid islandGrid) {
		this.islandGrid = islandGrid;
		character = new Character(islandGrid);
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
	 * @return A list of interactions. 
	 */
	public List<Integer> getInteractions(int x, int y) {
		Tile tile = islandGrid.getTile(x, y);
		if(tile != null) {
			return Interaction.getInteractions(tile);
		}
		return null;
	}
	public boolean performInteraction(int interaction, int x, int y) {
		Tile tile = islandGrid.getTile(x, y);
		if(tile != null) {
			return Interaction.interact(interaction, character, tile);
		}
		return false;
	}
}
