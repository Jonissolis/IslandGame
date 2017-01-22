package serverSide;

public interface I_IslandGrid {
	public I_Tile getTile(int xCoord, int yCoord);
	
	public int getWidth();
	
	public int getHeight();
}
