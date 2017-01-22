package clientSide;

import cookieGame.EatCookie;
import java.util.Random;
import serverSide.Client;

public class PathingAI extends BaseControls implements Runnable {

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

	private boolean shouldGoInX() {
		return (Math.signum(xDir) * (xCurrFloat - xCurr) >= Math.signum(yDir) * (yCurrFloat - yCurr));
	}

	private void lineWalk(int xTarget, int yTarget) {
		//Target Position
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
			if (shouldGoInX()) { //Should go i x
				if (isTileBlocked((int) (xCurr + Math.signum(xDir)), yCurr)) {
					if (!goInY(yTarget)) {
						break;
					}
					resetValues();
				} else {
					goInX(xTarget);
				}
			} else { // vill gå i y
				if (isTileBlocked(xCurr, (int) (yCurr + Math.signum(yDir)))) {
					if (!goInX(xTarget)) {
						break;
					}
					resetValues();
				} else {
					goInY(yTarget);
				}
			}
		}
	}

	private boolean canLineWalk(int xTarget, int yTarget) {
		//Calculate the "bird" path
		xCurr = getXCoord();
		yCurr = getYCoord();
		resetValues();
		//while not at target position we should move.
		while (xTarget != xCurr || yTarget != yCurr) {
			//one step in bird path
			xCurrFloat += xDir;
			yCurrFloat += yDir;
			//go towards the int point most distant from bord path.
			if (shouldGoInX()) { //Should go i x
				if (isTileBlocked((int) (xCurr + Math.signum(xDir)), yCurr)) {
					if (yTarget != yCurr) { //x was blocked try going in y instead
						if (isTileBlocked(xCurr, (int) (yCurr + Math.signum(yDir)))) {
							return false;
						}
						yCurr += Math.signum(yDir);
					}
					//since we got blocked we make a new line
					resetValues();
				} else { //x är inte blockat...
					xCurr += Math.signum(xDir);
				}
			} else { // vill gå i y
				if (isTileBlocked(xCurr, (int) (yCurr + Math.signum(yDir)))) { //är y blockat?
					if (xTarget != xCurr) { // gå i x istället
						if (isTileBlocked((int) (xCurr + Math.signum(xDir)), yCurr)) {
							return false;
						}
						xCurr++;
					}
					//since we got blocked we make a new line
					resetValues();
				} else {
					yCurr += Math.signum(yDir);
				}
			}
		}
		return true;
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

	private boolean goInY(int yTarget) {
		if (yDir > 0 && yTarget != yCurr) { //x was blocked try going in y instead
			if (!tryMoveNorth()) {
				return false;
			}
			yCurr++;
		} else if (yTarget != yCurr) {
			if (!tryMoveSouth()) {
				return false;
			}
			yCurr--;
		} else {
			return false;
		}
		return true;
	}

	private boolean goInX(int xTarget) {
		if (xDir > 0 && xTarget != xCurr) { //x was blocked try going in y instead
			if (!tryMoveEast()) {
				return false;
			}
			xCurr++;
		} else if (xTarget != xCurr) {
			if (!tryMoveWest()) {
				return false;
			}
			xCurr--;
		} else {
			return false;
		}
		return true;
	}
}
