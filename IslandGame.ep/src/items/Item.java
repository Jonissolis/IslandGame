package items;

/**
 * 
 * A class for items. 
 *
 */
public class Item {
	private final int id;
	private final int maxStackSize;
	private int currentStackSize;
	private ToolComponent toolComponent;
	private WeaponComponent weaponComponent;
	private String name;
	
	public Item(int id, int amount, int maxAmount, String name) {
		this.id = id;
		this.currentStackSize = amount;
		this.maxStackSize = maxAmount;
		this.name = name;
	}
	
	/**
	 * Sets the tool component of the item. 
	 * @param toolComponent The toolComponent to set this toolComponent equal to. 
	 */
	public void setToolComponent(ToolComponent toolComponent) {
		this.toolComponent = toolComponent;
	}
	
	/**
	 * Sets the tool component of the item. 
	 * @param toolComponent The toolComponent to set this toolComponent equal to. 
	 */
	public void setWeaponComponent(WeaponComponent weaponComponent) {
		this.weaponComponent = weaponComponent;
	}
	
	/**
	 * 
	 * @param toolComponent
	 * @return
	 */
	public ToolComponent getToolComponent() {
		return toolComponent;
	}
	
	/**
	 * 
	 * @param toolComponent
	 * @return
	 */
	public WeaponComponent getWeaponComponent() {
		return weaponComponent;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}