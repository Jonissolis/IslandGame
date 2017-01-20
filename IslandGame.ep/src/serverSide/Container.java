package serverSide;

import items.Item;
import items.ToolComponent;

public class Container {
	private int size;
	private Item[] items;
	
	public Container(int size) {
		this.size = size;
		this.items = new Item[size];
	}
	
	/**
	 * Tries to add an item to the bag. 
	 * Is not concurrent safe!
	 * TODO Currently has no support for stacking items. 
	 * 
	 * @param item The item to be added. 
	 * @return True if the item was successfully added. Otherwise false. 
	 */
	public boolean addItem(Item item) {
		for(int i = 0; i < size; i++) {
			if(items[i] == null) {
				items[i] = item;
				return true;
			}
		}
		return false;
	}

	public boolean canWoodcut() {
		Item item;
		ToolComponent toolComponent;
		for(int i = 0; i < size; i++) {
			item = items[i];
			if(item != null) {
				toolComponent = item.getToolComponent();
				if(toolComponent != null) {
					return(toolComponent.canCutWood());
				}
			}
		}
		return false;
	}
}
