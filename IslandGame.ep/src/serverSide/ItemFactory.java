package serverSide;

public class ItemFactory {
	public static Item getHatchet() {	// This is for testing purpose. Remove later on!
		int[] properties = {10};
		return new Item(1, 1, 1, properties);
	}
}
