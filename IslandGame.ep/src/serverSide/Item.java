package serverSide;

public class Item {
	private final int id;
	private int currentStackSize;
	private final int maxStackSize;
	public Item(int id, int amount, int maxAmount) {
		this.id = id;
		this.currentStackSize = amount;
		this.maxStackSize = maxAmount;
	}
	public int getID() {
		return id;
	}
	/** 
	 * Tries to add to the currentStackSize
	 * Returns the amount that was not added
	 */
	public int addToStack(int amount) {
		if (currentStackSize+amount <= maxStackSize) {
			currentStackSize += amount;
			return 0;
		}else {
			remaining = amount-currentStackSize+maxStackSize;
			currentStackSize = maxStackSize;
			return remaining;
		}
	}
	/** 
	 * Tries to remove from the currentStackSize
	 * Returns false if the amount if not available
	 */
	public boolean removeFromStack(int amount) {
		if (amount < currentStackSize){
			currentStackSize -= amount
			return true
		} else {
			return false
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}