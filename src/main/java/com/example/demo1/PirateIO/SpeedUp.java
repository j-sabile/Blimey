package com.example.demo1.PirateIO;

import com.example.demo1.HelloApplication;

import javafx.scene.image.Image;

public class SpeedUp extends PowerUp {

	final static Image IMG_SPEEDUP = new Image(HelloApplication.class.getResource("images/speed.png").toExternalForm());

	SpeedUp(GameTimer gameTimer) {
		super(gameTimer);
		this.setImage(IMG_SPEEDUP);
	}

}
