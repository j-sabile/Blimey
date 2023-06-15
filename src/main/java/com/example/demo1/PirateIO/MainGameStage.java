package com.example.demo1.PirateIO;

import java.util.Random;

import com.example.demo1.HelloApplication;
import com.example.demo1.Stage.Welcome;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainGameStage {

	private Stage stage;
	private StackPane root;
	private ScrollPane scroll;
	private Scene scene;
	private GraphicsContext gc, gcStats;
	private Canvas canvasStats, canvas;
	private GameTimer gametimer;

	final static int WINDOW_WIDTH = 800;
	final static int WINDOW_HEIGHT = 800;
	final static int MS_INTERVAL = 50;
	final static int GAME_SIZE = 2400;
	final static int MOVE_RATIO = 10;
	final static int NUMBER_OF_FOODS = 50;
	final static int NUMBER_OF_ENEMIES = 10;
	final static int FONTSIZE = 16;

	final static Random rand = new Random();
	final static Image IMG_BACKGROUND = new Image(
			HelloApplication.class.getResource("images/background.png").toExternalForm());

	public MainGameStage(Stage stage) {

		this.root = new StackPane();
		this.scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_WIDTH, Color.WHITE);
		this.scroll = new ScrollPane();
		this.canvas = new Canvas(GAME_SIZE, GAME_SIZE);
		this.gc = this.canvas.getGraphicsContext2D();
		this.canvasStats = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.gcStats = this.canvasStats.getGraphicsContext2D();

		this.scroll.setContent(this.canvas);
		this.scroll.setPannable(false);
		this.scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		this.scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		this.root.getChildren().addAll(this.scroll, this.canvasStats);

		this.stage = stage;
		this.stage.setTitle("PirateIO");
		this.stage.setScene(this.scene);
		this.stage.setResizable(false);

	}

	public void run() {
		this.gametimer = new GameTimer(this.gc, this.scene, this.gcStats, this);
		this.gametimer.start();
		this.stage.show();

	}

	private float scrollVal(float val) {
		return ((float) val / (GAME_SIZE - WINDOW_WIDTH))
				- ((float) (((float) WINDOW_WIDTH / 2) / (GAME_SIZE - WINDOW_WIDTH)));
	}

	void centerPlayer() {
		this.scroll.setHvalue(scrollVal(this.gametimer.getPlayer().getPosX()));
		this.scroll.setVvalue(scrollVal(this.gametimer.getPlayer().getPosY()));
	}

	void backToMainMenu() {
		Welcome wel = new Welcome();
		wel.open(this.stage);
	}

	///////////////////////////
	// Setters //
	public Scene getScene() {
		return this.scene;
	}
	///////////////////////////

	///////////////////////////
	// Getters //
	StackPane getStackPane() {
		return this.root;
	}

	Stage getStage() {
		return this.stage;
	}
	///////////////////////////

}
