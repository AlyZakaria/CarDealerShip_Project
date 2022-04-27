package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane main_pane = new BorderPane();
        HBox left_pane = new HBox();
        VBox top_pane = new VBox();
        left_pane.setMinSize(60, 50);
        top_pane.setMinSize(60, 50);
        left_pane.setBackground(new Background(new BackgroundFill(Color.web("#" + "000000"), CornerRadii.EMPTY, Insets.EMPTY)));
        top_pane.setBackground(new Background(new BackgroundFill(Color.web("#" + "abcdef"), CornerRadii.EMPTY, Insets.EMPTY)));
        main_pane.setLeft(left_pane);
        main_pane.setTop(top_pane);
        Scene scene = new Scene(main_pane, 300, 240);
        //for full screen
        stage.setMaximized(true);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //test
    }

    public static void main(String[] args) {
        launch();
    }
}