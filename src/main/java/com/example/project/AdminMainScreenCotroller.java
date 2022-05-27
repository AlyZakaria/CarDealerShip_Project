package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminMainScreenCotroller {

    public AnchorPane TopAnchorPane;
    public Button userScreenExitBtn;
    public Button HomeBtn;
    public Button PendingOrdersBtn;
    public Button SearchUserBtn;
    public Button SettingsBtn;
    public Button LogOutBtn;
    public Label date;
    public AnchorPane MainPane;

    public void exitBtn(ActionEvent event) {
        System.exit(0);
    }

    public void SettingsButton(ActionEvent event) throws IOException {

    }
    public void LogOutBtn(ActionEvent event) throws IOException {
        Parent loginScreen = ScreenSelector.getLoginScreen().load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }

    public void HomeScreenBtn(ActionEvent event) throws IOException {
        Parent HomeScreen = ScreenSelector.getHomeScreen().load();
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll( (AnchorPane) HomeScreen );
    }

    public void searchUserBtn() {
        System.out.println("Test");
    }

    public void pendingOrdersBtn() {

    }
}
