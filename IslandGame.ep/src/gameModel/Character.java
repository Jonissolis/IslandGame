package gameModel;

public class Character {
	private IslandGrid islandGrid;
	private int xCoord;
	private int yCoord;
	
	
	
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
		}
	}
	
	/**
	 * Tries to move left. 
	 * Does nothing if unable. 
	 */
	public void moveWest() {
		Tile newTile = islandGrid.getTile(getXCoord() - 1, getYCoord());
		moveTo(newTile);
	}
	/**
	 * Tries to move left. 
	 * Does nothing if unable. 
	 */
	public void moveEast() {
		Tile newTile = islandGrid.getTile(getXCoord() + 1, getYCoord());
		moveTo(newTile);
	}
	/**
	 * Tries to move left. 
	 * Does nothing if unable. 
	 */
	public void moveNorth() {
		Tile newTile = islandGrid.getTile(getXCoord(), getYCoord() + 1);
		moveTo(newTile);
	}
	/**
	 * Tries to move left. 
	 * Does nothing if unable. 
	 */
	public void moveSouth() {
		Tile newTile = islandGrid.getTile(getXCoord(), getYCoord() - 1);
		moveTo(newTile);
	}
	
	/**
	 * Tries to move to a tile. Does nothing if unable. 
	 * @param newTile
	 */
	private void moveTo(Tile newTile) {
		if(newTile!=null && newTile.newOccupier(this)) {
			getTile().leaveTile();
			xCoord = newTile.getXCoord();
			yCoord = newTile.getYCoord();
		}
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
	
	
}
