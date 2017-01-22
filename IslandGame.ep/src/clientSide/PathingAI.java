package clientSide;

import cookieGame.EatCookie;
import java.util.Random;
import serverSide.Client;

public class PathingAI extends BaseControls implements Runnable {

	//Target Position
	private int xTarget;
	private int yTarget;
	//"Bird" path values
	private int xStart;
	private int yStart;
	private double xDiff;
	private double yDiff;
	private double totDiff;
	private double xDir;
	private double yDir;
	//current position in double and int
	private double xCurrFloat;
	private double yCurrFloat;
	private int xCurr;
	private int yCurr;

	public PathingAI(Client client) {
		super(client);
	}

	@Override
	public void run() {
		int xGoal = EatCookie.xCoord;
		int yGoal = EatCookie.yCoord;
		lineWalk(xGoal, yGoal);
		System.out.println("Done");
		//goTo(19, 19);
	}

	private void resetValues() {
		xStart = xCurr;
		yStart = yCurr;
		xDiff = xTarget - xStart;
		yDiff = yTarget - yStart;
		totDiff = Math.abs(xDiff) + Math.abs(yDiff);
		xDir = xDiff / totDiff;
		yDir = yDiff / totDiff;
		xCurrFloat = xStart;
		yCurrFloat = yStart;
	}

	private boolean goInX() {
		return (Math.signum(xDir) * (xCurrFloat - xCurr) >= Math.signum(yDir) * (yCurrFloat - yCurr));
	}

	private void lineWalk(int x, int y) {
		//Target Position
		xTarget = x;
		yTarget = y;
		//Calculate the "bird" path
		xCurr = getXCoord();
		yCurr = getYCoord();
		resetValues();
		//while not at target position we should move.
		while (xTarget != xCurr || yTarget != yCurr) {
			waitSome();
			//one step in bird path
			xCurrFloat += xDir;
			yCurrFloat += yDir;
			//go towards the int point most distant from bord path.
			if (goInX()) { //Should go i x
				if (isTileBlocked((int) (xCurr + Math.signum(xDir)), yCurr)) {
					if (yDir > 0 && yTarget != yCurr) { //x was blocked try going in y instead
						if (!tryMoveNorth()) {
							break;
						}
						yCurr++;
					} else if (yTarget != yCurr) {
						if (!tryMoveSouth()) {
							break;
						}
						yCurr--;
					} else {
						break;
					}
					//since we got blocked we make a new line
					resetValues();
				} else { //x är inte blockat...
					if (xDir > 0) { // gå i x
						if (!tryMoveEast()) {
							break;
						}
						xCurr++;
					} else {
						if (!tryMoveWest()) {
							break;
						}
						xCurr--;
					}
				}
			} else { // vill gå i y
				if (isTileBlocked(xCurr, (int) (yCurr + Math.signum(yDir)))) { //är y blockat?
					if (xDir > 0 && xTarget != xCurr) { // gå i x istället
						if (!tryMoveEast()) {
							break;
						}
						xCurr++;

					} else if (xTarget != xCurr) {
						if (!tryMoveWest()) {
							break;
						}
						xCurr--;
					} else {
						break;
					}
					//since we got blocked we make a new line
					resetValues();
				} else { //y är inte blockat...
					if (yDir > 0) { // gå i y
						if (!tryMoveNorth()) {
							break;
						}

						yCurr++;
					} else {
						if (!tryMoveSouth()) {
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

	private void waitSome() {
		try {// a small pause
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean tryMoveNorth() {
		for (int stuckTime = 0; stuckTime < 3; stuckTime++) {
			waitSome();
			if (moveNorth()) {
				return true;
			}
		}
		return false;
	}

	private boolean tryMoveEast() {
		for (int stuckTime = 0; stuckTime < 3; stuckTime++) {
			waitSome();
			if (moveEast()) {
				return true;
			}
		}
		return false;
	}

	private boolean tryMoveSouth() {
		for (int stuckTime = 0; stuckTime < 3; stuckTime++) {
			waitSome();
			if (moveSouth()) {
				return true;
			}
		}
		return false;
	}

	private boolean tryMoveWest() {
		for (int stuckTime = 0; stuckTime < 3; stuckTime++) {
			waitSome();
			if (moveWest()) {
				return true;
			}
		}
		return false;
	}

}
