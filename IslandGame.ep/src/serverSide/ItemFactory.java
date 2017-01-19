package serverSide;

public class ItemFactory {
	
	public static final int HATCHET_ID = 0;
	
	public static Item getItem(int ID) {	// This is for testing purpose. Remove later on!
		if(ID == 0) {
			int[] properties = new int[Item.NUMBER_OF_PROPERTIES];
			properties[Item.WOODCUTTING_POWER_INDEX] = 10;
			return new Item(1, 1, 1, properties);
		}
		return null;
	}
}
