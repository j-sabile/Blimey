package com.example.demo1.PirateIO;

import com.example.demo1.HelloApplication;

import javafx.scene.image.Image;

public class Player extends Pirate {

	private boolean isImmune; // used in the immunity powerUp

	final static int INITIAL_SPAWN = 1200;

	final static Image IMG_PLAYER = new Image(HelloApplication.class.getResource("images/player.png").toExternalForm());
	final static Image IMG_IMMUNEPLAYER = new Image(
			HelloApplication.class.getResource("images/immuneplayer.png").toExternalForm());

	Player() {
		super(INITIAL_SPAWN, INITIAL_SPAWN);
		super.setImage(IMG_PLAYER);
		this.isImmune = false;
	}

	// thread that makes the player immune in 5secs
	void eatImmunity() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				isImmune = true;
				setImage(IMG_IMMUNEPLAYER);

				// waits 5 seconds before reverting back to normal
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				isImmune = false;
				setImage(IMG_PLAYER);
			}
		});
		t1.start();
	}

	// thread that makes the player double its speed in 5secs
	void eatSpeedUp() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				setSpeedUp(2);

				// waits 5 seconds before reverting back to normal
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				setSpeedUp(1);
			}
		});
		t1.start();
	}

	///////////////////////////
	// Getters //
	boolean getIsImmune() {
		return this.isImmune;
	}
	///////////////////////////

}
