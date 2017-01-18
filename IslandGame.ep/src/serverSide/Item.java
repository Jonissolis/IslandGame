package serverSide;

public class Item {
	private int id;
	private int currentStackSize;
	private final int maxStackSize;
	public Item(int id, int amount) {
		this.id = id;
		/*
		* need method for max stackAmount.
		* probably just a search for it depending on id
		*/
		this.maxStackSize = 1;
		if(this.maxStackSize > amount) {
			this.currentStackSize = amount;
		}
		else {
			this.currentStackSize = maxStackSize;
		}
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