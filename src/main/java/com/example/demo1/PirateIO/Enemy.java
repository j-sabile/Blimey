package com.example.demo1.PirateIO;

import com.example.demo1.HelloApplication;
import javafx.scene.image.Image;

public class Enemy extends Pirate {

	private GameTimer gametimer;
	private int direction, seconds; // stores the automated moves of the enemies

	final static Image IMG_ENEMY = new Image(HelloApplication.class.getResource("images/enemy.png").toExternalForm());
	final static int MAX_SECS = 5;
	final static int MAX_DIRECTION = 4;

	Enemy(GameTimer gametimer) {
		super();
		this.gametimer = gametimer;
		this.setImage(IMG_ENEMY);
	}

	// generates random move every random seconds
	public void run() {
		while (this.gametimer.getGameOver() == false) {
			// reset directions
			this.resetDirections();

			// generate random move
			this.generateRandomMove();

			try {
				Thread.sleep(this.seconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// generates directions
	private void resetDirections() {
		this.speedX = 0;
		this.speedY = 0;
	}

	// generates random moves
	private void generateRandomMove() {
		this.direction = MainGameStage.rand.nextInt(MAX_DIRECTION);
		this.seconds = MainGameStage.rand.nextInt(MAX_SECS + 1);

		// moves right
		if (this.direction == 0) {
			this.speedX = 1;
		}

		// moves upward
		else if (this.direction == 1) {
			this.speedY = 1;
		}

		// moves left
		else if (this.direction == 2) {
			this.speedX = -1;
		}

		// moves downward
		else if (this.direction == 3) {
			this.speedY = -1;
		}
	}

	// spawns at random position
	void spawn() {
		super.spawn();
		this.resetDirections();
	}

}
