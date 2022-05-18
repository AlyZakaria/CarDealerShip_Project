package com.example.project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

import java.io.IOException;
import java.util.concurrent.Flow;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException, SQLException {


        Parent LoginScreen = ScreenSelector.getLoginScreen().load();
        //To be able to drag it
        LoginScreen.setOnMousePressed(pressEvent -> {
            LoginScreen.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });

        Scene scene = new Scene(LoginScreen);
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}