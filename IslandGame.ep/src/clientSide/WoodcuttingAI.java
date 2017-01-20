package clientSide;

import java.util.Set;

import interactions.Woodcutting;
import serverSide.Client;

public class WoodcuttingAI extends BaseAI implements Runnable{

	private int xCoord;
	private int yCoord;
	
	public WoodcuttingAI(Client client) {
		super(client);
	}

	
	private boolean woodcutIfAble(int x, int y) {
		Set<Integer> interactions = getInteractions(x, y);
		if(interactions != null && interactions.contains(Woodcutting.INTERACTION_ID)) {
			return performInteraction(Woodcutting.INTERACTION_ID, x, y);
		}
		return false;
	}
	
	@Override
	public void run() {
		int x = 0;
		while(x < 100000) {
			xCoord = getXCoord();
			yCoord = getYCoord();
			woodcutIfAble(xCoord + 1, yCoord); 
			woodcutIfAble(xCoord - 1, yCoord); 
			woodcutIfAble(xCoord, yCoord + 1);
			woodcutIfAble(xCoord, yCoord - 1); 
			randomWalk(); 
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x++;
		}
	}
	
}
