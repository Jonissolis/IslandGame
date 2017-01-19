package serverSide;

/**
 * Class to keep track of a single character. 
 */
public class Character {
	private IslandGrid islandGrid;
	private int xCoord;
	private int yCoord;
	private Container backpack;
	
	
	/**
	 * Creates a character and places them on a random tile in the input grid. 
	 * @param islandGrid
	 */
	public Character(IslandGrid islandGrid) {
		this.islandGrid = islandGrid;
		Tile occupiedTile = islandGrid.placeOnEmptyTile(this); 
		if(occupiedTile == null) {
			System.out.println("ERROR: A character was unable to be placed because the grid is full. ");
		} else {
			xCoord = occupiedTile.getXCoord();
			yCoord = occupiedTile.getYCoord();
			backpack = new Container(20);									// This is for testing purpose. Remove later on!
			backpack.addItem(ItemFactory.getItem(ItemFactory.HATCHET_ID)); 	// This is for testing purpose. Remove later on!
		}
	}
	

	/**
	 * 
	 * Tries to move west. 
	 * Does nothing if unable.
	 * @return True if the move was successful, otherwise false. 
	 */
	public boolean moveWest() {
		Tile newTile = islandGrid.getTile(getXCoord() - 1, getYCoord());
		return moveTo(newTile);
	}
	/**
	 * 
	 * Tries to move east. 
	 * Does nothing if unable.
	 * @return True if the move was successful, otherwise false. 
	 */
	public boolean moveEast() {
		Tile newTile = islandGrid.getTile(getXCoord() + 1, getYCoord());
		return moveTo(newTile);
	}
	/**
	 * 
	 * Tries to move north. 
	 * Does nothing if unable.
	 * @return True if the move was successful, otherwise false. 
	 */	
	public boolean moveNorth() {
		Tile newTile = islandGrid.getTile(getXCoord(), getYCoord() + 1);
		return moveTo(newTile);
	}
	/**
	 * Tries to move south. 
	 * Does nothing if unable. 
	 * @return True if the move was successful, otherwise false. 
	 */
	public boolean moveSouth() {
		Tile newTile = islandGrid.getTile(getXCoord(), getYCoord() - 1);
		return moveTo(newTile);
	}
	
	/**
	 * Tries to move to a tile. Does nothing if unable. This doesn't check whether the tile is within range or not.  
	 * @param newTile
	 * @return Whether the move was successful or not. 
	 */
	private boolean moveTo(Tile newTile) {
		if(newTile!=null && newTile.newOccupier(this)) {
			getTile().stopOccupying();
			xCoord = newTile.getXCoord();
			yCoord = newTile.getYCoord();
			return true;
		}
		return false;
	}

	public Tile getTile() {
		return islandGrid.getTile(getXCoord(), getYCoord());
	}
	
	/**
	 * @return the X coordinate of the character.
	 */
	public int getXCoord() {
		return xCoord;
	}
	
	/**
	 * @return the Y coordinate of the character. 
	 */
	public int getYCoord() {
		return yCoord;
	}
	
	/**
	 * Getter for the character's backpack. 
	 * @return The backpack. 
	 */
	public Container getContainer() {
		return backpack;
	}
	
}
