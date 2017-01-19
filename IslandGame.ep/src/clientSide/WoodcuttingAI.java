package clientSide;

import java.util.Random;
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
		Random random = new Random();
		double randomDouble;
		int x = 0;
		
		while(x < 1000) {
			xCoord = getXCoord();
			yCoord = getYCoord();
			if(woodcutIfAble(xCoord + 1, yCoord)) {} 
			else if(woodcutIfAble(xCoord - 1, yCoord)) {} 
			else if(woodcutIfAble(xCoord, yCoord + 1)) {}
			else if(woodcutIfAble(xCoord, yCoord - 1)) {} 
			
			randomDouble = random.nextDouble();
			if(randomDouble <= 0.25) {
				moveWest();
			} else if(randomDouble <= 0.5) {
				moveEast();
			} else if(randomDouble <= 0.75) {
				moveNorth();
			} else {
				moveSouth();
			} 
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
