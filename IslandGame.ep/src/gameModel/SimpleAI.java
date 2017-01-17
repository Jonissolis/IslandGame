package gameModel;

import java.util.Random;

public class SimpleAI extends BaseAI implements Runnable {

	public SimpleAI(Client client) {
		super(client);
	}

	@Override
	public void run() {
		Random random = new Random();
		double randomDouble;
		int x = 0;
		while(x < 1000) {
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
