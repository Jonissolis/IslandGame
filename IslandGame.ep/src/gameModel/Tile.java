package gameModel;

import java.util.Observable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Tile extends Observable {
	private Lock lock = new ReentrantLock();
	
	private int xCoord;
	private int yCoord;
	private boolean blocked;
	private Character occupier = null;
	
	public Tile(boolean blocked, int xCoord, int yCoord) {
		this.blocked = blocked;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	
	
	public boolean newOccupier(Character character) {
		if(lock.tryLock()) {
			if(!blocked) {
				blocked = true;
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
	
	public void leaveTile() {
		occupier = null;
		blocked = false;
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
	
	
	/**
	 * This can be used to check for errors. Should only return false if there are errors.
	 *  
	 * @return False if errors were encountered. 
	 */
	private boolean blockedAssertion() {
		if(lock.tryLock()) {
			if(occupier != null && !blocked) {
				return false;
			}
			return true;
		} else {
			return true; //
		}
	}






	



	




	
	
}
