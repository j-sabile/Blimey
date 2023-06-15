package com.example.demo1.PirateIO;

public class PowerUp extends Sprite {

	private GameTimer gameTimer;

	final static int RADIUS = 20;
	final static int DIAMETER = 40;
	final static int SECS_SPAWN_INTERVAL = 10;
	final static int SECS_BEFORE_DISAPPEAR = 5;

	PowerUp(GameTimer gameTimer) {
		this.spawn();
		this.setInitialSize();
		this.gameTimer = gameTimer;
		this.visible = false;
	}

	// thread that makes the powerUp appear every 10 seconds and disappear after 5
	// seconds
	public void run() {

		try {
			Thread.sleep(MainGameStage.rand.nextInt(SECS_SPAWN_INTERVAL) * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		while (this.gameTimer.getGameOver() == false) {

			this.visible = true;

			// waits 5 seconds after setting to visible is true
			try {
				Thread.sleep(SECS_BEFORE_DISAPPEAR * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.visible = false;

			try {
				Thread.sleep((SECS_SPAWN_INTERVAL - SECS_BEFORE_DISAPPEAR) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.spawn();
			this.visible = true;
		}
	}

	private void setInitialSize() {
		this.radius = RADIUS;
		this.diameter = DIAMETER;
	}

	void eaten() {
		this.visible = false;
	}

}
