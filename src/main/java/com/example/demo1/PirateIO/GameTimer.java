package com.example.demo1.PirateIO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo1.HelloApplication;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class GameTimer extends AnimationTimer {

	private GraphicsContext gc;
	private GraphicsContext gcStats;
	private Scene scene;
	private MainGameStage mainGame;
	private Player player;
	private Immunity immunity;
	private SpeedUp speedUp;
	private Rectangle2D mainMenuBounds;

	private boolean gameOver;
	private int foodEaten, blobsEaten, timeAlive;
	private long startTime;

	private List<Food> foods;
	private List<Enemy> enemies;

	final static String IMMUNE = "immune";
	final static String SPEEDUP = "speedup";

	final static int POSX_IMG_MAINMENU = 275;
	final static int POSY_IMG_MAINMENU = 585;
	final static int HEIGHT_IMG_MAINMENU = 92;
	final static int WIDTH_IMG_MAINMENU = 250;

	final static int POSX_POWERUP_POPUP = 150;
	final static int POSY_POWERUP_POPUP = 450;
	final static int HEIGHT_POWERUP_POPUP = 500;
	final static int WIDTH_POWERUP_POPUP = 500;

	final static int FONTSIZE = 40;
	final static String SRC_FONT = "/fonts/Seagram.ttf";

	final static Image IMG_GAMEOVER = new Image(
			HelloApplication.class.getResource("images/gameoverwindow.png").toExternalForm());
	final static Image IMG_STATUSBAR = new Image(
			HelloApplication.class.getResource("images/statusbar.png").toExternalForm());
	final static Image IMG_MAINMENU = new Image(
			HelloApplication.class.getResource("images/mainmenu.png").toExternalForm());

	final static Image IMG_IMMUNE5 = new Image(HelloApplication.class.getResource("images/I5.png").toExternalForm());
	final static Image IMG_IMMUNE4 = new Image(HelloApplication.class.getResource("images/I4.png").toExternalForm());
	final static Image IMG_IMMUNE3 = new Image(HelloApplication.class.getResource("images/I3.png").toExternalForm());
	final static Image IMG_IMMUNE2 = new Image(HelloApplication.class.getResource("images/I2.png").toExternalForm());
	final static Image IMG_IMMUNE1 = new Image(HelloApplication.class.getResource("images/I1.png").toExternalForm());
	final static Image IMG_SPEEDUP5 = new Image(
			HelloApplication.class.getResource("images/S5.png").toExternalForm());
	final static Image IMG_SPEEDUP4 = new Image(
			HelloApplication.class.getResource("images/S4.png").toExternalForm());
	final static Image IMG_SPEEDUP3 = new Image(
			HelloApplication.class.getResource("images/S3.png").toExternalForm());
	final static Image IMG_SPEEDUP2 = new Image(
			HelloApplication.class.getResource("images/S2.png").toExternalForm());
	final static Image IMG_SPEEDUP1 = new Image(
			HelloApplication.class.getResource("images/S1.png").toExternalForm());

	GameTimer(GraphicsContext gc, Scene scene, GraphicsContext gcStats, MainGameStage mainGame) {

		this.gc = gc;
		this.gcStats = gcStats;
		this.scene = scene;
		this.mainGame = mainGame;

		this.foodEaten = 0;
		this.blobsEaten = 0;
		this.timeAlive = 0;
		this.gameOver = false;

		// loads the font
		Font myFont = Font.loadFont(HelloApplication.class.getResource("fonts/Seagram.ttf").toExternalForm(),
				FONTSIZE);
		this.gcStats.setFont(myFont);

		// creates the player
		this.player = new Player();
		this.handleKeyPressEvent();

		// creates an arraylist of foods
		this.foods = new ArrayList<Food>();
		for (int i = 0; i < MainGameStage.NUMBER_OF_FOODS; i++) {
			this.foods.add(new Food());
		}

		// creates power ups
		this.immunity = new Immunity(this);
		this.speedUp = new SpeedUp(this);
		this.immunity.start();
		this.speedUp.start();

		// creates an arraylist of enemies
		this.enemies = new ArrayList<Enemy>();
		for (int i = 0; i < MainGameStage.NUMBER_OF_ENEMIES; i++) {
			this.enemies.add(new Enemy(this));
		}

		// starts automated moves of enemies
		for (Enemy enemy : this.enemies) {
			enemy.start();
		}
		this.startTime = System.nanoTime();
	}

	@Override
	public void handle(long currentNanoTime) {

		// move enemies
		this.moveEnemies();

		// checkCollisions
		this.checkCollisions();

		// shows the background
		this.gc.drawImage(MainGameStage.IMG_BACKGROUND, 0, 0, MainGameStage.GAME_SIZE, MainGameStage.GAME_SIZE);

		// shows the power ups
		this.immunity.render(this.gc);
		this.speedUp.render(this.gc);

		// shows the foods
		for (Food food : this.foods) {
			food.render(this.gc);
		}

		// shows the player
		this.player.render(this.gc);
		this.player.move();

		// shows the enemies
		for (Enemy enemy : this.enemies) {
			enemy.render(this.gc);
		}

		// centers the player view
		this.mainGame.centerPlayer();

		// shows the status bar and status info
		this.gcStats.drawImage(IMG_STATUSBAR, 0, -360, 800, 800);
		this.gcStats.fillText(": " + Integer.toString(this.foodEaten), 95, 54);
		this.gcStats.fillText(": " + Integer.toString(this.blobsEaten), 275, 54);
		this.gcStats.fillText(": " + Integer.toString((int) this.player.getDiameter()), 460, 54);
		this.gcStats.fillText(": " + String.format("%.2f", (float) (currentNanoTime - this.startTime) / 1000000000),
				635, 54);

		if (this.gameOver == true) {
			this.timeAlive = (int) ((currentNanoTime - this.startTime) / 1000000000);
			this.showGameOver();
		}
	}

	// pop up timer for powerUps
	private void runTimer(String string) {
		if (string == IMMUNE) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					gcStats.drawImage(IMG_IMMUNE5, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_IMMUNE4, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_IMMUNE3, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_IMMUNE2, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_IMMUNE1, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.clearRect(0, 0, MainGameStage.WINDOW_HEIGHT, MainGameStage.WINDOW_WIDTH);
				}
			});
			t1.start();
		} else if (string == SPEEDUP) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					gcStats.drawImage(IMG_SPEEDUP5, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_SPEEDUP4, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_SPEEDUP3, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_SPEEDUP2, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.drawImage(IMG_SPEEDUP1, POSX_POWERUP_POPUP, POSY_POWERUP_POPUP, HEIGHT_POWERUP_POPUP,
							WIDTH_POWERUP_POPUP);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gcStats.clearRect(0, 0, MainGameStage.WINDOW_HEIGHT, MainGameStage.WINDOW_WIDTH);
				}
			});
			t1.start();
		}
	}

	// checks collisions
	private void checkCollisions() {

		// sprite eating food
		for (Food food : this.foods) {

			// player eating food
			if (this.collisionChecker(this.player, food)) {
				this.player.eatFood(food);
				this.foodEaten += 1;
			}

			// enemy eating food
			for (Enemy enemy : this.enemies) {
				if (this.collisionChecker(enemy, food)) {
					enemy.eatFood(food);
				}
			}
		}

		// player consuming a powerUp
		// immunity
		if (this.collisionChecker(this.player, this.immunity) && this.immunity.getVisible() == true) {
			this.immunity.eaten();
			this.player.eatImmunity();
			this.runTimer(IMMUNE);
		}
		// speedup
		if (this.collisionChecker(this.player, this.speedUp) && this.speedUp.getVisible() == true) {
			this.speedUp.eaten();
			this.player.eatSpeedUp();
			this.runTimer(SPEEDUP);
		}

		// eating a sprite (pirate)
		for (Enemy enemy : this.enemies) {

			// checking player-enemy collisions
			if (this.collisionChecker(this.player, enemy)) {

				// if player is larger than the enemy
				if (this.player.getRadius() > enemy.getRadius()) {
					player.eatPirate(enemy);
					this.blobsEaten += 1;
				}

				// if player is smaller than the enemy (GAME OVER)
				else if ((this.player.getRadius() < enemy.getRadius()) && this.player.getIsImmune() == false) {
					this.gameOver = true;
					this.stop();
				}
			}

			// checking enemy-enemy collisions
			for (Enemy enemy1 : this.enemies) {

				if (this.collisionChecker(enemy1, enemy) && enemy.getId() != enemy1.getId()) {

					// if enemy is larger than enemy1
					if (enemy.getRadius() > enemy1.getRadius()) {
						enemy.eatPirate(enemy1);
					}

					// if enemy is smaller than enemy1
					if (enemy.getRadius() < enemy1.getRadius()) {
						enemy1.eatPirate(enemy);
					}
				}
			}
		}
	}

	// shows a game over pop up with the stats
	private void showGameOver() {
		this.gcStats.clearRect(0, 0, 800, 800);
		this.gcStats.drawImage(IMG_GAMEOVER, 171, 74, 458, 571);
		this.gcStats.fillText(Integer.toString(this.foodEaten), 400, 310);
		this.gcStats.fillText(Integer.toString(this.blobsEaten), 400, 381);
		this.gcStats.fillText(Integer.toString(this.timeAlive), 400, 452);
		this.gcStats.fillText(Integer.toString((int) this.player.getDiameter()), 400, 523);

		this.gcStats.drawImage(IMG_MAINMENU, POSX_IMG_MAINMENU, POSY_IMG_MAINMENU, WIDTH_IMG_MAINMENU,
				HEIGHT_IMG_MAINMENU);
		this.mainMenuBounds = new Rectangle2D(POSX_IMG_MAINMENU, POSY_IMG_MAINMENU, WIDTH_IMG_MAINMENU,
				HEIGHT_IMG_MAINMENU);
		this.handleMouseEvent();
	}

	// there is a collision if the distance between the centers of the circles is
	// less than or equal to the sum of their radius
	// √((x2-x1)²+(y2-y1)²) <= r1 + r2
	private boolean collisionChecker(Sprite s1, Sprite s2) {
		if (Math.sqrt(Math.pow(s1.getPosX() - s2.getPosX(), 2) + Math.pow(s1.getPosY() - s2.getPosY(), 2)) <= s1
				.getRadius() + s2.getRadius()) {
			return true;
		}
		return false;
	}

	// moves all the enemies
	private void moveEnemies() {
		for (Enemy enemy : this.enemies) {
			enemy.move();
		}
	}

	// mouse event for the Main Menu button
	private void handleMouseEvent() {
		this.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (mainMenuBounds.contains(e.getX(), e.getY())) {
					mainGame.backToMainMenu();
				}
			}
		});
	}

	// keyPress event for the movement of player
	private void handleKeyPressEvent() {
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				movePlayer(e.getCode());
			}
		});

		this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				stopPlayer(e.getCode());
			}
		});
	}

	private void movePlayer(KeyCode e) {
		if (e == KeyCode.A) {
			this.player.setSpeedX(-1);
		}
		if (e == KeyCode.D) {
			this.player.setSpeedX(1);
		}
		if (e == KeyCode.S) {
			this.player.setSpeedY(1);
		}
		if (e == KeyCode.W) {
			this.player.setSpeedY(-1);
		}
	}

	private void stopPlayer(KeyCode e) {
		if (e == KeyCode.A || e == KeyCode.D) {
			this.player.setSpeedX(0);
		}
		if (e == KeyCode.W || e == KeyCode.S) {
			this.player.setSpeedY(0);
		}
	}

	///////////////////////////
	// Getters //
	Player getPlayer() {
		return this.player;
	}

	boolean getGameOver() {
		return this.gameOver;
	}
	///////////////////////////
}
