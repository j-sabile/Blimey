package com.example.demo1.PirateIO;

import com.example.demo1.HelloApplication;
import javafx.scene.image.Image;

public class Food extends Sprite {

	final static int FOOD_DIAMETER = 20;
	final static int FOOD_RADIUS = 10;
	final static int FOOD_VALUE_DIAMETER = 10;

	final static Image IMG_FOOD = new Image(HelloApplication.class.getResource("images/food.png").toExternalForm());

	Food() {
		super();
		this.setInitialSize();
		this.setImage(IMG_FOOD);
	}

	// sets the size of food
	void setInitialSize() {
		this.radius = FOOD_RADIUS;
		this.diameter = FOOD_DIAMETER;
	}

}
