package com.example.demo1.PirateIO;

import com.example.demo1.HelloApplication;

import javafx.scene.image.Image;

public class Immunity extends PowerUp {

	final static Image IMG_IMMUNITY = new Image(
			HelloApplication.class.getResource("images/immunity.png").toExternalForm());

	Immunity(GameTimer gameTimer) {
		super(gameTimer);
		this.setImage(IMG_IMMUNITY);
	}

}
