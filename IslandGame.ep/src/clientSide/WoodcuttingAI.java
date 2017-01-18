package clientSide;

import java.util.List;
import java.util.Random;

import serverSide.Client;
import serverSide.Interaction;

public class WoodcuttingAI extends BaseAI implements Runnable{

	private int xCoord;
	private int yCoord;
	
	public WoodcuttingAI(Client client) {
		super(client);
	}

	@Override
	public void run() {
		xCoord = getXCoord();
		yCoord = getYCoord();
		
		Random random = new Random();
		double randomDouble;
		int x = 0;
		List<Integer> interactions;
		while(x < 1000) {
			if((interactions = getInteractions(xCoord + 1, yCoord)) != null && interactions.contains(Interaction.WOODCUTTING)) {
				performInteraction(Interaction.WOODCUTTING, xCoord + 1, yCoord);
			} else if((interactions = getInteractions(xCoord - 1, yCoord)) != null && interactions.contains(Interaction.WOODCUTTING)) {
				performInteraction(Interaction.WOODCUTTING, xCoord - 1, yCoord);
			} else if((interactions = getInteractions(xCoord, yCoord + 1)) != null && interactions.contains(Interaction.WOODCUTTING)) {
				performInteraction(Interaction.WOODCUTTING, xCoord, yCoord + 1);
			} else if((interactions = getInteractions(xCoord, yCoord - 1)) != null && interactions.contains(Interaction.WOODCUTTING)) {
				performInteraction(Interaction.WOODCUTTING, xCoord, yCoord - 1);
			}
			
			randomDouble = random.nextDouble();
			if(randomDouble <= 0.25) {
				if(moveWest()) {
					xCoord -= 1;
				}
			} else if(randomDouble <= 0.5) {
				if(moveEast()) {
					xCoord += 1;
				}
			} else if(randomDouble <= 0.75) {
				if(moveNorth()) {
					yCoord += 1;
				}
			} else {
				if(moveSouth()) {
					yCoord -= 1;
				}
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
