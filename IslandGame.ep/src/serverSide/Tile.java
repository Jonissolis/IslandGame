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
	public static final int IS_BLOCKED_INDEX = 0;
	public static final int IS_OCCUPIED_INDEX = 1;
	public static final int NUMBER_OF_PROPERTIES = 2;
	
	private Lock lock = new ReentrantLock();
	private int xCoord;
	private int yCoord;
	private final int[] properties;
	private Map<Integer, Interaction> interactions = new HashMap<Integer, Interaction>();
	
	private Character occupier = null;
	
	/**
	 * Constructor
	 * @param blocked Tells whether characters can move onto this tile or not. 
	 * @param xCoord The x coordinate of this tile. 
	 * @param yCoord The y coordinate of this tile. 
	 */
	public Tile(int xCoord, int yCoord, int[] properties) {
		assert properties.length == NUMBER_OF_PROPERTIES: "A tile was not given the correct amount of properties. ";
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.properties = properties.clone();
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
		properties[IS_OCCUPIED_INDEX] = 1;
		lock.unlock();
	}
	
	/**
	 * Sets the current occupier to null. 
	 */
	public void leaveTile() {
		stopOccupying();
	}
	
	/**
	 * Sets current occupier to null and changes the occupied property to zero. 
	 */
	private void stopOccupying() {
		lock.lock();
		occupier = null;
		properties[IS_OCCUPIED_INDEX] = 0;
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
	 * @return True if blocked. False otherwise. This method will not take the tiles lock which means something else might occupy this tile before anything can be done with it. 
	 */
	public boolean isBlocked() {
		return properties[0] == 1 ? true : false;
	}
	
	/**
	 * 
	 * @return True if occupied. False otherwise.  
	 */
	public boolean isOccupied() {
		return properties[1] == 1 ? true : false;
	}

	/**
	 * 
	 * @return The character occupying this tile. Null if none is occupying it. 
	 */
	public Character getOccupier() {
		return occupier;
	}
	
	public int getProperty(int propertyIndex) {
		return properties[propertyIndex];
	}
	public void changeProperty(int propertyIndex, int newValue) {
		properties[propertyIndex] = newValue;
		setChanged();
		notifyObservers();
	}
	public Set<Integer> getInteractions() {
		return interactions.keySet();
	}
	public boolean interact(int interactionID, Character performer) {
		Interaction interaction = interactions.get(interactionID);
		if(interaction != null) {
			return interaction.interact(performer, this);
		}
		return false;
	}
	public void addInteraction(Interaction interaction) {
		interactions.put(interaction.getInteractionID(), interaction);
	}
	
	public boolean hasInteraction(int interactionID) {
		return interactions.containsKey(interactionID);
	}
	public boolean hasInteraction(Interaction interaction) {
		return interactions.containsValue(interaction);
	}

	public void removeInteraction(Interaction interaction) {
		if(interactions.remove(interaction.getInteractionID()) != interaction) {
			System.out.println("ERROR: Wrong interaction was removed from a tile. "); // Error message. Could be caused by two characters doing the same interaction. 
		}
		
	}
}
