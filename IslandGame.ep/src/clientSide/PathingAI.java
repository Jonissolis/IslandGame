package clientSide;

import cookieGame.EatCookie;
import java.util.Random;
import serverSide.Client;

public class PathingAI extends BaseControls implements Runnable {

	public PathingAI(Client client) {
		super(client);
	}

	@Override
	public void run() {
		//if (canLineWalk(19, 19)) {

		//}
		int xGoal = EatCookie.xCoord;
		int yGoal = EatCookie.yCoord;

		for (int j = 0; j < 10; j++) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lineWalk(xGoal, yGoal);
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				randomWalk();
			}
		}
		System.out.println("Done");
		//goTo(19, 19);
	}

	private void lineWalk(int x, int y) {
		int stuckTime = 0;
		//Target Position
		int xTarget = x;
		int yTarget = y;
		//Calculate the "bird" path
		int xStart = getXCoord();
		int yStart = getYCoord();
		double xDiff = xTarget - xStart;
		double yDiff = yTarget - yStart;
		double totDiff = Math.abs(xDiff) + Math.abs(yDiff);
		double xAdd = xDiff / totDiff;
		double yAdd = yDiff / totDiff;
		//current position in double and int
		double xCurrFloat = xStart;
		double yCurrFloat = yStart;
		int xCurr = xStart;
		int yCurr = yStart;
		int iteration = (xStart + yStart);
		//while not at target position we should move there.
		while (xTarget != xCurr || yTarget != yCurr && stuckTime < 3) {

			try {// a small pause
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//one step in bird path
			xCurrFloat += xAdd;
			yCurrFloat += yAdd;
			//go towards the int point most distant from bord path.
			if (Math.signum(xAdd) * (xCurrFloat - xCurr) >= Math.signum(yAdd) * (yCurrFloat - yCurr)) { //vill gå i x
				if (isTileBlocked((int) (xCurr + Math.signum(xAdd)), yCurr)) { //är x blockat?
					if (yAdd > 0 && yTarget != yCurr) { // gå i y istället
						while (!moveNorth() && stuckTime < 3) {
							System.out.println("FAST");
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						yCurr++;

					} else if (yTarget != yCurr) {
						while (!moveSouth() && stuckTime < 3) {
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						yCurr--;
					} else {
						System.out.println("kan ej slutföra");
						break;
					}
					//since we got blocked we make a new bird path
					xStart = xCurr;
					yStart = yCurr;
					xDiff = xTarget - xStart;
					yDiff = yTarget - yStart;
					totDiff = Math.abs(xDiff) + Math.abs(yDiff);
					xAdd = xDiff / totDiff;
					yAdd = yDiff / totDiff;
					xCurrFloat = xCurr;
					yCurrFloat = yCurr;
				} else { //x är inte blockat...
					if (xAdd > 0) { // gå i x
						while (!moveEast() && stuckTime < 3) {
							System.out.println("FAST");
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						xCurr++;
					} else {
						while (!moveWest() && stuckTime < 3) {
							System.out.println("FAST");
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						xCurr--;
					}
				}
			} else { // vill gå i y
				if (isTileBlocked(xCurr, (int) (yCurr + Math.signum(yAdd)))) { //är y blockat?
					if (xAdd > 0 && xTarget != xCurr) { // gå i x istället
						while (!moveEast() && stuckTime < 3) {
							System.out.println("FAST");
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						xCurr++;

					} else if (xTarget != xCurr) {
						while (!moveWest() && stuckTime < 3) {
							System.out.println("FAST");
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						xCurr--;
					} else {
						System.out.println("kan ej slutföra");
						break;
					}
					//since we got blocked we make a new bird path
					xStart = xCurr;
					yStart = yCurr;
					xDiff = xTarget - xStart;
					yDiff = yTarget - yStart;
					totDiff = Math.abs(xDiff) + Math.abs(yDiff);
					xAdd = xDiff / totDiff;
					yAdd = yDiff / totDiff;
					xCurrFloat = xCurr;
					yCurrFloat = yCurr;
				} else { //y är inte blockat...
					if (yAdd > 0) { // gå i y
						while (!moveNorth() && stuckTime < 3) {
							System.out.println("FAST");
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						yCurr++;
					} else {
						while (!moveSouth() && stuckTime < 3) {
							stuckTime++;
						}
						if (stuckTime > 2) {
							break;
						}
						yCurr--;
					}
				}
			}
		}
	}

	public void randomWalk() {
		switch (new Random().nextInt(4)) {
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
	
	private boolean canLineWalk(int x, int y) {
		//Target Position
		int xTarget = x;
		int yTarget = y;
		//Calculate the "bird" path
		int xStart = getXCoord();
		int yStart = getYCoord();
		double xDiff = xTarget - xStart;
		double yDiff = yTarget - yStart;
		double totDiff = xDiff + yDiff;
		double xAdd = xDiff / totDiff;
		double yAdd = yDiff / totDiff;
		//current position in double and int
		double xCurrFloat = xStart;
		double yCurrFloat = yStart;
		int xCurr = xStart;
		int yCurr = yStart;
		//while not at target position we should move there.
		while (xTarget != xCurr || yTarget != yCurr) {
			//one step in bird path
			xCurrFloat += xAdd;
			yCurrFloat += yAdd;
			//go towards the int point most distant from bord path.
			if (Math.signum(xAdd) * (xCurrFloat - xCurr) >= Math.signum(yAdd) * (yCurrFloat - yCurr)) { //vill gå i x
				if (isTileBlocked((int) (xCurr + Math.signum(xAdd)), yCurr)) { //är x blockat?
					if (yAdd > 0 && yTarget != yCurr && !isTileBlocked(xCurr, yCurr + 1)) { // gå i y istället
						yCurr++;
					} else if (yAdd < 0 && yTarget != yCurr && !isTileBlocked(xCurr, yCurr - 1)) {
						yCurr--;
					} else {
						return false;
					}
					//since we got blocked we make a new bird path
					xStart = xCurr;
					yStart = xCurr;
					xDiff = xTarget - xStart;
					yDiff = yTarget - yStart;
					totDiff = xDiff + yDiff;
					xAdd = xDiff / totDiff;
					yAdd = yDiff / totDiff;
					xCurrFloat = xCurr;
					yCurrFloat = xCurr;
				} else { //x är inte blockat...
					if (xAdd > 0) { // gå i x
						xCurr++;
					} else {
						xCurr--;
					}
				}
			} else { // vill gå i y
				if (isTileBlocked(xCurr, (int) (yCurr + Math.signum(yAdd)))) { //är y blockat?
					if (xAdd > 0 && xTarget != xCurr && !isTileBlocked(xCurr + 1, yCurr)) { // gå i y istället
						xCurr++;
					} else if (xAdd < 0 && xTarget != xCurr && !isTileBlocked(xCurr - 1, yCurr)) {
						xCurr--;
					} else {
						return false;
					}

				} else { //y är inte blockat...
					if (yAdd > 0) { // gå i y
						yCurr++;
					} else {
						yCurr--;
					}
				}
			}
			System.out.println(" ");
			System.out.println("x: " + xCurr);
			System.out.println("y: " + yCurr);
		}
		return true;
	}
	//private void lineWalk(int x, int y) {
	//}

}
