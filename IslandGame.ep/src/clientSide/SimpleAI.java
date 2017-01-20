package clientSide;

import serverSide.Client;

public class SimpleAI extends BaseAI implements Runnable {

	public SimpleAI(Client client) {
		super(client);
	}

	@Override
	public void run() {
		int x = 0;
		while(x < 1000) {
			randomWalk();
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
