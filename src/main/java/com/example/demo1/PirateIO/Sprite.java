package com.example.demo1.PirateIO;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite extends Thread {

	protected float posX, posY, radius, diameter;
	protected boolean visible;

	private Image img;

	// spawns randomly in the map
	Sprite() {
		this.spawn();
		this.visible = true;
	}

	// spawns in the given parameters
	Sprite(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.visible = true;
	}

	// spawn randomly in the map
	void spawn() {
		this.posX = MainGameStage.rand.nextInt(MainGameStage.GAME_SIZE);
		this.posY = MainGameStage.rand.nextInt(MainGameStage.GAME_SIZE);
	}

	// renders if the sprite is visible
	void render(GraphicsContext gc) {
		if (this.visible == true) {
			gc.drawImage(this.img, this.posX - this.radius, this.posY - this.radius, this.diameter, this.diameter);
		}
	}

	///////////////////////////
	// Setters //
	void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	void setRadius(int radius) {
		this.radius = radius;
	}

	void setImage(Image img) {
		this.img = img;
	}
	///////////////////////////

	///////////////////////////
	// Getters //
	float getPosX() {
		return this.posX;
	}

	float getPosY() {
		return this.posY;
	}

	float getRadius() {
		return this.radius;
	}

	float getDiameter() {
		return this.diameter;
	}

	boolean getVisible() {
		return this.visible;
	}
	///////////////////////////

}
