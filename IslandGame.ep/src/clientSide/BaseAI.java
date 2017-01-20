package clientSide;

import java.util.Random;

import serverSide.Client;

public abstract class BaseAI extends BaseControls {

	public BaseAI(Client client) {
		super(client);
	}

	public void randomWalk() {
		switch(new Random().nextInt(4)){
		case 0:
			moveWest();
			break;
		case 1:
			moveEast();
			break;
		case 2:
			moveNorth();
			break;
		case 3:
			moveSouth();
			break;
		}
	}
}