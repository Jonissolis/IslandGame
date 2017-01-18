package clientSide;

import java.util.Random;

import serverSide.Client;

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
				if(moveWest()) { }
			} else if(randomDouble <= 0.5) {
				if(moveEast()) { }
			} else if(randomDouble <= 0.75) {
				if(moveNorth()) { }
			} else {
				if(moveSouth()) { }
			} 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x++;
		}
	}
	
}
