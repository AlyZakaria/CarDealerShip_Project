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

import java.sql.*;

import java.io.IOException;
import java.util.concurrent.Flow;

public class HelloApplication extends Application {
    Person person;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);

        Login login = new Login();

        Pane test = login.getPane();

        //login event handler
        login.Login_btn.setOnAction(event ->  {
            Singleton_Connector connector = Singleton_Connector.getInstance();
            int IDInput = Integer.parseInt(login.ID_txt.getText());
            String passwordInput = login.Password_txt.getText();
            try {
                person = connector.checkCredentials(IDInput, passwordInput);
                MainScreen mainScreen = new MainScreen();

                if(person != null) {
                    Scene mainScene = new Scene(mainScreen.getMainPane(), 300, 240);

                    stage.setScene(mainScene);
                    stage.setMaximized(true);

                    stage.show();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });


        Scene scene = new Scene(test);
        stage.setTitle("Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}