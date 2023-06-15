package com.example.demo1.PirateIO;

import com.example.demo1.Stage.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		Welcome welcome = new Welcome();
		welcome.open(stage);
	}

}