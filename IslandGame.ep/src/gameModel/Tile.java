package gameModel;

import java.util.Observable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * Class for the tiles on the play grid. 
 * 
 * Currently, each tile can either be empty, blocked or occupied. 
 *
 */
public class Tile extends Observable {
	private Lock lock = new ReentrantLock();
	private int xCoord;
	private int yCoord;
	private boolean blocked;
	private Character occupier = null;
	
	/**
	 * Constructor
	 * @param blocked Tells whether characters can move onto this tile or not. 
	 * @param xCoord The x coordinate of this tile. 
	 * @param yCoord The y coordinate of this tile. 
	 */
	public Tile(boolean blocked, int xCoord, int yCoord) {
		this.blocked = blocked;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	
	/**
	 * Tries to have a character occupy this tile. 
	 * 
	 * @param character The character that tries to occupy this tile. 
	 * @return True if the character succeeded with moving to this tile. False if it was unable to.
	 */
	public boolean newOccupier(Character character) {
		if(lock.tryLock()) {
			if(!blocked && !isOccupied()) {
				occupier = character;
				setChanged();
				notifyObservers();
				lock.unlock();
				return true;
			}
			lock.unlock();
		}
		return false;
	}
	
	/**
	 * Sets the current occupier to null. 
	 */
	public void leaveTile() {
		occupier = null;
	}
	
	/**
	 * 
	 * @return The x coordinate of the tile. 
	 */
	public int getXCoord() {
		return xCoord;
	}
	
	/**
	 * 
	 * @return The y coordinate of the tile. 
	 */
	public int getYCoord() {
		return yCoord;
	}
	
	/**
	 * 
	 * @return True if blocked. False otherwise. This method will not take the tiles lock which means something else might occupy this tile before anything can be done with it. 
	 */
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * 
	 * @return True if occupied. False otherwise.  
	 */
	public boolean isOccupied() {
		return occupier!=null;
	}

	
	
}
