package clientSide;

import java.util.Set;

import serverSide.Client;

public abstract class BaseAI {
	private Client client;
	
	public BaseAI(Client client) {
		this.client = client;
	}
	public boolean moveWest() {
		return client.moveWest();
	}
	public boolean moveEast() {
		return client.moveEast();
	}
	public boolean moveNorth() {
		return client.moveNorth();
	}
	public boolean moveSouth() {
		return client.moveSouth();
	}
	public int getXCoord() {
		return client.getXCoord();
	}
	public int getYCoord() {
		return client.getYCoord();
	}
	public boolean isTileOccupied(int x, int y) {
		return client.isTileOccupied(x, y);
	}
	public boolean isTileBlocked(int x, int y) {
		return client.isTileBlocked(x, y);
	}
	public Set<Integer> getInteractions(int x, int y) {
		return client.getInteractions(x, y);
	}
	public boolean performInteraction(int interactionID, int x, int y) {
		return client.performInteraction(interactionID, x, y);
	}
}
