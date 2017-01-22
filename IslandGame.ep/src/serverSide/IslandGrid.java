package serverSide;


public class IslandGrid implements I_IslandGrid {
	private final int width;
	private final int height;
	private Tile[][] grid;
	
	
	public IslandGrid(int width, int height) {
		grid = new Tile[width][height];
		this.width = width;
		this.height = height;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < width; j++) {
				grid[i][j] = TileFactory.getTile(0.1, 0.5, i, j);
			}
		}
	}
	
	
	/**
	 *  
	 * @param x The x coordinate of the tile. 
	 * @param y The y coordinate of the tile. 
	 * @return The tile at the specified coordinate. Returns null if either x or y is out of range. 
	 */
	public Tile getTile(int x, int y) {
		if(x >= 0 && x < width && y >= 0 && y < height) {
			return grid[x][y];
		} else {
			return null;
		}
	}

	/**
	 * This method places a character on a random empty tile on the grid. 
	 * 
	 * @param character The character to place
	 * @return The tile the character was placed on. Returns null if no empty tile was found.  
	 */
	public Tile placeOnEmptyTile(Character character) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(grid[i][j].newOccupier(character)) {
					return grid[i][j];
				}
			}
		}
		return null;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
