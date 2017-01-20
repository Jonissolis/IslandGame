package serverSide;

import items.Item;
import items.ToolComponent;

public class ItemFactory {
	
	public static final int HATCHET_ID = 0;
	
	public static Item getItem(int ID) {	// This is for testing purpose. Remove later on!
		if(ID == HATCHET_ID) {
			Item item = new Item(1, 1, 1, "Hatchet");
			ToolComponent toolComponent = new ToolComponent();
			toolComponent.setIsWoodcuttingTool(true);
			item.setToolComponent(toolComponent);
			return item;
			
		}
		return null;
	}
}
