package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    // final static ImageView IMGV_GAMETITLE = new
    // ImageView("images/blimeytitle.png");
    // final static ImageView IMGV_NEWGAME = new ImageView("images/newgame.png");
    // final static ImageView IMGV_ABOUT = new ImageView("images/about.png");
    // final static ImageView IMGV_INSTRUCTIONS = new
    // ImageView("images/instructions.png");
    // final static ImageView IMGV_EXIT = new ImageView("images/exitgame.png");
    // final static ImageView IMGV_INSTWINDOW = new
    // ImageView("images/instruct.png");
    // final static ImageView IMGV_YES = new ImageView("images/ayeaye.png");
    // final static ImageView IMGV_BACK = new ImageView("images/mainmenu.png");
    // final static ImageView IMGV_ABOUTWINDOW = new
    // ImageView("images/aboutwindow.png");
    // final static Image IMG_LOGO = new Image("images/logo.png");
    // final static Image IMG_MENUBG = new Image("images/flyship.png");
    // final static Image IMG_INSTRUCTIONSBG = new Image("images/jollyroger.png");
    // final static Image IMG_ABOUTBG = new Image("images/neverland.jpg");

    // final static ImageView IMGV_GAMETITLE = new
    // ImageView(HelloApplication.class.getResource("blimeytitle.png").toExternalForm());
    // final static ImageView IMGV_NEWGAME = new
    // ImageView(HelloApplication.class.getResource("newgame.png").toExternalForm());
    // final static ImageView IMGV_ABOUT = new
    // ImageView(HelloApplication.class.getResource("about.png").toExternalForm());
    // final static ImageView IMGV_INSTRUCTIONS = new
    // ImageView(HelloApplication.class.getResource("isntructions.png").toExternalForm());
    // final static ImageView IMGV_EXIT = new
    // ImageView(HelloApplication.class.getResource("exitgame.png").toExternalForm());

    @Override
    public void start(Stage stage) throws IOException {

        ImageView IMGV_GAMETITLE = new ImageView(
                HelloApplication.class.getResource("images/blimeytitle.png").toExternalForm());
        ImageView IMGV_NEWGAME = new ImageView(
                HelloApplication.class.getResource("images/newgame.png").toExternalForm());
        ImageView IMGV_ABOUT = new ImageView(
                HelloApplication.class.getResource("images/about.png").toExternalForm());
        ImageView IMGV_INSTRUCTIONS = new ImageView(
                HelloApplication.class.getResource("images/instructions.png").toExternalForm());
        ImageView IMGV_EXIT = new ImageView(
                HelloApplication.class.getResource("images/exitgame.png").toExternalForm());
        // FXMLLoader fxmlLoader = new
        // FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        // stage.setScene(scene);

        final int WINDOW_WIDTH = 800;
        final int WINDOW_HEIGHT = 800;
        final int BUTTON_HEIGHT = 85;
        final int BUTTON_X = 280;

        Pane menuPage = new Pane();

        Canvas menuBG = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc1 = menuBG.getGraphicsContext2D();

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

        menuPage.getChildren().addAll(menuBG, IMGV_GAMETITLE, newgameButton, instButton, aboutButton, exitButton);
        Scene scene1 = new Scene(menuPage, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}