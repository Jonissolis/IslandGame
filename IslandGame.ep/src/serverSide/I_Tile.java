package serverSide;

import java.util.Observer;

public interface I_Tile {
	public void observe(Observer obs);
	
	public boolean isOccupied();
	
	public boolean hasInteraction(int interactionID);
	
	public boolean isBlocked();
}
