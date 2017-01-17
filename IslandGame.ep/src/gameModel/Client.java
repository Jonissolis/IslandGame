package gameModel;

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
	public boolean isTileOccupied(int x, int y) {
		return islandGrid.getTile(x, y).isOccupied();
	}
	public boolean isTileBlocked(int x, int y) {
		return islandGrid.getTile(x, y).isBlocked();
	}
}
