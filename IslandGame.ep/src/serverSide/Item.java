package serverSide;

/**
 * 
 * A class for items. 
 *
 */
public class Item {
	private final int id;
	private final int maxStackSize;
	private int currentStackSize;
	private final int[] properties;
	public static final int WOODCUTTING_POWER_INDEX = 0;
	public static final int NUMBER_OF_PROPERTIES = 1;
	
	
	public Item(int id, int amount, int maxAmount, int[] properties) {
		assert properties.length == NUMBER_OF_PROPERTIES: "A tile was not given the correct amount of properties. ";
		this.id = id;
		this.currentStackSize = amount;
		this.maxStackSize = maxAmount;
		this.properties = properties.clone();
	}
	
	/**
	 * 
	 * @return The id of the item. 
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Tries to add to the currentStackSize.
	 * @param amount The amount to be added. 
	 * @return The amount exceeding maximum stack limit. 0 if everything was added. 
	 */
	public int addToStack(int amount) {
		if (currentStackSize + amount <= maxStackSize) {
			currentStackSize += amount;
			return 0;
		} else {
			int remaining = amount - currentStackSize + maxStackSize;
			currentStackSize = maxStackSize;
			return remaining;
		}
	}
	
	/**
	 * Tries to remove from the currentStackSize.
	 * @param amount The amount to be removed. 
	 * @return false if the amount if not available, otherwise true. 
	 */
	public boolean removeFromStack(int amount) {
		if (amount < currentStackSize){
			currentStackSize -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public int[] getProperties() {
		return properties.clone();
	}
	
	public int getProperty(int property) {
		return properties[property];
	}
	
	
	
	
	
	
	
}