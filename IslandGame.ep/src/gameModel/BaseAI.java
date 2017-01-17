package gameModel;

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
	public boolean isTileOccupied(int x, int y) {
		return client.isTileOccupied(x, y);
	}
	public boolean isTileBlocked(int x, int y) {
		return client.isTileBlocked(x, y);
	}
	
}
