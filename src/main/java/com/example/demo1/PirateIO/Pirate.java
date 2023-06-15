package com.example.demo1.PirateIO;

public class Pirate extends Sprite {

	protected float speedX, speedY;
	private int speedUp; // used in the speed PowerUp(serves as a multiplier)
							// 1:normal speed, 2:doubles the speed

	final static int INITIAL_DIAMETER = 40;
	final static int INITIAL_RADIUS = 20;

	// spawns randomly (for enemy pirates)
	Pirate() {
		super();
		this.setInitialSizeAndSpeed();
	}

	// spawns at a specific position (for main player)
	Pirate(int x, int y) {
		super(x, y);
		this.setInitialSizeAndSpeed();
	}

	// spawns at random and resets the size and speed
	void spawn() {
		super.spawn();
		this.setInitialSizeAndSpeed();
	}

	// resets size, speed, and speedUp
	void setInitialSizeAndSpeed() {
		this.setRadius(INITIAL_RADIUS);
		this.setDiameter(INITIAL_DIAMETER);
		this.speedX = 0;
		this.speedY = 0;
		this.speedUp = 1;
	}

	// returns the maxSpeed of the pirate
	float maxSpeed() {
		return (float) 120 / this.diameter;
	}

	// moves the pirate
	void move() {

		// restricts pirates to move horizontally outside the game
		if ((this.speedX > 0 && this.posX + this.speedX <= MainGameStage.GAME_SIZE - this.radius)
				|| (this.speedX < 0 && this.posX + this.speedX >= this.radius)) {
			this.posX += this.speedX * this.maxSpeed() * this.speedUp;
		}

		// restricts pirates to move vertically outside the game
		if ((this.speedY > 0 && this.posY + this.speedY <= MainGameStage.GAME_SIZE - this.radius)
				|| (this.speedY < 0 && this.posY + this.speedY >= this.radius)) {
			this.posY += this.speedY * this.maxSpeed() * this.speedUp;
		}
	}

	// eats a food, increases its size, and respawns the food at a random position
	void eatFood(Food food) {
		this.radius += Food.FOOD_VALUE_DIAMETER / 2;
		this.diameter += Food.FOOD_VALUE_DIAMETER;
		food.spawn();
	}

	// eats a pirate, increases its size and respawns the pirate at a random
	// position
	void eatPirate(Pirate pirate) {
		this.radius += pirate.getRadius();
		this.diameter += pirate.getRadius() * 2;
		pirate.spawn();
	}

	///////////////////////////
	// Setters //
	void setSpeedUp(int value) {
		this.speedUp = value;
	}

	void setSpeedX(int value) {
		this.speedX = value;
	}

	void setSpeedY(int value) {
		this.speedY = value;
	}
	///////////////////////////

}
