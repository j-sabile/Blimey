package com.example.demo1.Stage;

import javafx.stage.Stage;

import com.example.demo1.HelloApplication;
import com.example.demo1.PirateIO.MainGameStage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

//http://www.learningaboutelectronics.com/Articles/How-to-create-multiple-scenes-and-switch-between-scenes-in-JavaFX.php
//https://genuinecoder.com/category/javafx-gui/

public class Welcome {

    Stage window;
    Scene scene1, scene2, scene3;

    public Welcome() {
    }

    public void open(Stage stage) {

        final ImageView IMGV_GAMETITLE = new ImageView(
                HelloApplication.class.getResource("images/blimeytitle.png").toExternalForm());
        final ImageView IMGV_NEWGAME = new ImageView(
                HelloApplication.class.getResource("images/newgame.png").toExternalForm());
        final ImageView IMGV_ABOUT = new ImageView(
                HelloApplication.class.getResource("images/about.png").toExternalForm());
        final ImageView IMGV_INSTRUCTIONS = new ImageView(
                HelloApplication.class.getResource("images/instructions.png").toExternalForm());
        final ImageView IMGV_EXIT = new ImageView(
                HelloApplication.class.getResource("images/exitgame.png").toExternalForm());
        final ImageView IMGV_INSTWINDOW = new ImageView(
                HelloApplication.class.getResource("images/instruct.png").toExternalForm());
        final ImageView IMGV_YES = new ImageView(
                HelloApplication.class.getResource("images/ayeaye.png").toExternalForm());
        final ImageView IMGV_BACK = new ImageView(
                HelloApplication.class.getResource("images/mainmenu.png").toExternalForm());
        final ImageView IMGV_ABOUTWINDOW = new ImageView(
                HelloApplication.class.getResource("images/aboutwindow.png").toExternalForm());
        final Image IMG_LOGO = new Image(HelloApplication.class.getResource("images/logo.png").toExternalForm());
        final Image IMG_MENUBG = new Image(
                HelloApplication.class.getResource("images/flyship.png").toExternalForm());
        final Image IMG_INSTRUCTIONSBG = new Image(
                HelloApplication.class.getResource("images/jollyroger.png").toExternalForm());
        final Image IMG_ABOUTBG = new Image(
                HelloApplication.class.getResource("images/neverland.jpg").toExternalForm());

        window = stage;
        stage.getIcons().add(IMG_LOGO);

        final int WINDOW_WIDTH = 800;
        final int WINDOW_HEIGHT = 800;
        final int BUTTON_HEIGHT = 85;
        final int BUTTON_X = 280;

        // fill in scene 1
        Pane menuPage = new Pane();

        Canvas menuBG = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc1 = menuBG.getGraphicsContext2D();

        gc1.drawImage(IMG_MENUBG, 0, 0);

        IMGV_GAMETITLE.setX(165);
        IMGV_GAMETITLE.setY(70);
        IMGV_GAMETITLE.setPreserveRatio(true);
        IMGV_GAMETITLE.setFitHeight(210);

        Button newgameButton = new Button();
        IMGV_NEWGAME.setPreserveRatio(true);
        IMGV_NEWGAME.setFitHeight(BUTTON_HEIGHT);
        newgameButton.setGraphic(IMGV_NEWGAME);
        newgameButton.setBackground(null);
        newgameButton.setLayoutX(BUTTON_X);
        newgameButton.setLayoutY(290);

        Button instButton = new Button();
        IMGV_ABOUT.setPreserveRatio(true);
        IMGV_ABOUT.setFitHeight(BUTTON_HEIGHT);
        instButton.setGraphic(IMGV_ABOUT);
        instButton.setBackground(null);
        instButton.setLayoutX(BUTTON_X);
        instButton.setLayoutY(510);

        Button aboutButton = new Button();
        IMGV_INSTRUCTIONS.setPreserveRatio(true);
        IMGV_INSTRUCTIONS.setFitHeight(BUTTON_HEIGHT);
        aboutButton.setGraphic(IMGV_INSTRUCTIONS);
        aboutButton.setBackground(null);
        aboutButton.setLayoutX(BUTTON_X);
        aboutButton.setLayoutY(400);

        Button exitButton = new Button();
        IMGV_EXIT.setPreserveRatio(true);
        IMGV_EXIT.setFitHeight(BUTTON_HEIGHT);
        exitButton.setGraphic(IMGV_EXIT);
        exitButton.setBackground(null);
        exitButton.setLayoutX(BUTTON_X);
        exitButton.setLayoutY(620);

        newgameButton.setOnAction(e -> this.openMainMenu());
        aboutButton.setOnAction(e -> stage.setScene(scene2));
        instButton.setOnAction(e -> stage.setScene(scene3));
        exitButton.setOnAction(e -> closeProgram());
        menuPage.getChildren().addAll(menuBG, IMGV_GAMETITLE, newgameButton, instButton, aboutButton, exitButton);
        scene1 = new Scene(menuPage, WINDOW_WIDTH, WINDOW_HEIGHT);

        // end of scene 1

        // fill in scene2

        Canvas instructionsBG = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc2 = instructionsBG.getGraphicsContext2D();
        gc2.drawImage(IMG_INSTRUCTIONSBG, 0, 0);

        IMGV_INSTWINDOW.setPreserveRatio(true);
        IMGV_INSTWINDOW.setFitHeight(710);
        IMGV_INSTWINDOW.setX(50);
        IMGV_INSTWINDOW.setY(15);

        Button yesButton = new Button(); // returns to main menu
        IMGV_YES.setPreserveRatio(true);
        IMGV_YES.setFitHeight(80);
        yesButton.setGraphic(IMGV_YES);
        yesButton.setBackground(null);
        yesButton.setLayoutX(BUTTON_X);
        yesButton.setLayoutY(650);

        Pane instructionsPage = new Pane();
        yesButton.setOnAction(e -> stage.setScene(scene1));
        instructionsPage.getChildren().addAll(instructionsBG, IMGV_INSTWINDOW, yesButton);
        scene2 = new Scene(instructionsPage, WINDOW_WIDTH, WINDOW_HEIGHT);

        // end of scene 2

        // fill scene 3
        Canvas aboutBG = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc3 = aboutBG.getGraphicsContext2D();
        gc3.drawImage(IMG_ABOUTBG, 0, 0);

        IMGV_ABOUTWINDOW.setPreserveRatio(true);
        IMGV_ABOUTWINDOW.setFitHeight(700);
        IMGV_ABOUTWINDOW.setX(120);
        IMGV_ABOUTWINDOW.setY(23);

        Button backButton = new Button(); // IMGV_BACK to main menu button
        IMGV_BACK.setPreserveRatio(true);
        IMGV_BACK.setFitHeight(80);
        backButton.setGraphic(IMGV_BACK);
        backButton.setBackground(null);
        backButton.setLayoutX(295);
        backButton.setLayoutY(665);

        Pane aboutPage = new Pane();
        backButton.setOnAction(e -> stage.setScene(scene1));
        aboutPage.getChildren().addAll(aboutBG, IMGV_ABOUTWINDOW, backButton);
        scene3 = new Scene(aboutPage, WINDOW_WIDTH, WINDOW_HEIGHT);

        // end of scene3

        stage.setScene(scene1);
        stage.setTitle("Blimey!");
        stage.show();
    }

    private void closeProgram() {
        window.close();
    }

    private void openMainMenu() {
        MainGameStage mainGame = new MainGameStage(this.window);
        mainGame.run();
    }

}
