package com.example.project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Flow;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException, SQLException {

//        //file chooser
//        Pane pane = new Pane();
//        Button btn = new Button("Click me");
//        pane.getChildren().add(btn);
//        btn.setOnAction(event -> {
//            FileChooser fc = new FileChooser();
//            List<File> list = fc.showOpenMultipleDialog(null);
//            try {
//                File_Handler.createOrderFile(list);
//            } catch (Exception e) {
//                System.out.println("here");
//                System.out.println(e.getMessage());
//            }
//
//        });

//        Singleton_Connector instance = Singleton_Connector.getInstance();
//        Order order = new Order(1, instance.getLastOrderID(), "Sedan", 500, "automatic", "red", "test",
//                2001, 90, "sh8ala", 1);
//        order.AddOrder(null);
//        order.DeleteOrder();


//        Singleton_Connector instance = Singleton_Connector.getInstance();
//        try {
//            instance.addUser(new User(5,"Mina", 21, "rasafa", "minamagdy651@gmail.com", "01203662382", 1, "test", "001"));
//        } catch (UserExistsException e) {
//            System.out.println("user exists");
//        }


        Admin_User admin = new Admin_User(5,"Mina", 21, "rasafa", "minamagdy651@gmail.com", "01203662382", 1, "test", "001");
        //admin.addUser(new User(9,"Mina", 21, "rasafa", "minamagdy651@gmail.com", "01203662382", 1, "test", "609"));
        User user = new User(5,"Mina", 21, "rasafa", "minamagdy651@gmail.com", "01203662382", 1, "test", "609");
        admin.deleteUser(user);

        // when creating an order, you have to set the orderID to be the last one, use getLastOrderID

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