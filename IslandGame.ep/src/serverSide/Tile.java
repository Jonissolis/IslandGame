package serverSide;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interactions.Interaction;


/**
 * 
 * Class for the tiles on the play grid. 
 * 
 * Currently, each tile can either be empty, blocked or occupied. 
 *
 */
public class Tile extends Observable {
	private boolean blocked;
	private Lock lock = new ReentrantLock();
	private int xCoord;
	private int yCoord;
	private Map<Integer, Interaction> interactions = new HashMap<Integer, Interaction>();
	
	private Character occupier = null;
	
	/**
	 * Constructor
	 * @param blocked Tells whether characters can move onto this tile or not. 
	 * @param xCoord The x coordinate of this tile. 
	 * @param yCoord The y coordinate of this tile. 
	 */
	public Tile(int xCoord, int yCoord, boolean blocked) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.blocked = blocked;
	}
	
	
	/**
	 * Tries to have a character occupy this tile. 
	 * 
	 * @param character The character that tries to occupy this tile. 
	 * @return True if the character succeeded with moving to this tile. False if it was unable to.
	 */
	public boolean newOccupier(Character character) {
		if(lock.tryLock()) {
			if(!isBlocked() && !isOccupied()) {
				Occupy(character);
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
	 * Makes a character occupy this tile. 
	 * @param character The character to occupy the tile. 
	 */
	private void Occupy(Character character) {
		lock.lock();
		occupier = character;
		lock.unlock();
	}
	
	/**
	 * Makes this tile unoccupied.  
	 */
	public void stopOccupying() {
		lock.lock();
		occupier = null;
		lock.unlock();
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
	 * @return True if blocked. False otherwise. 
	 */
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * 
	 * @return True if occupied. False otherwise.  
	 */
	public boolean isOccupied() {
		return occupier != null;
	}

	/**
	 * 
	 * @return The character occupying this tile. Null if none is occupying it. 
	 */
	public Character getOccupier() {
		return occupier;
	}
	
	/**
	 * Getter for a set of IDs corresponding to the IDs of the interactions available on this tile. 
	 * @return A set of IDs. 
	 */
	public Set<Integer> getInteractions() {
		return interactions.keySet();
	}
	
	/**
	 * Allows a character to perform an interaction on this tile. 
	 * @param interactionID The ID of the interaction to be performed. 
	 * @param performer The character who is going to perform the interaction. 
	 * @return True if the interaction was successful, otherwise false. 
	 */
	public boolean interact(int interactionID, Character performer) {
		Interaction interaction = interactions.get(interactionID);
		if(interaction != null) {
			return interaction.interact(performer, this);
		}
		return false;
	}
	
	/**
	 * Adds an interaction to this tile. 
	 * @param interaction The interaction to be added. 
	 */
	public void addInteraction(Interaction interaction) {
		interactions.put(interaction.getInteractionID(), interaction);
	}
	
	/**
	 * Checks whether this tile has an interaction with a certain ID or not. 
	 * @param interactionID The ID of the interaction. 
	 * @return True if it has the interaction, otherwise false. 
	 */
	public boolean hasInteraction(int interactionID) {
		return interactions.containsKey(interactionID);
	}
	
	/**
	 * Checks whether this tile has an interaction or not. 
	 * @param interaction The interaction. 
	 * @return True if it has the interaction, otherwise false. 
	 */
	public boolean hasInteraction(Interaction interaction) {
		return interactions.containsValue(interaction);
	}

	/**
	 * Removes an interaction from this tile. 
	 * @param interaction The interaction to be removed. 
	 */
	public void removeInteraction(Interaction interaction) {
		if(interactions.remove(interaction.getInteractionID()) != interaction) {
			System.out.println("ERROR: Wrong interaction was removed from a tile. "); // Error message. Could be caused by two characters doing the same interaction. 
		}
		
	}
}
