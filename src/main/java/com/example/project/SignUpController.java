package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    private Stage stage;

    public void CloseBtn(ActionEvent event) throws IOException {System.exit(0);
    }
    public void returnBack(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = ScreenSelector.getLoginScreen();
        Parent loginScreen = loader.load();
        HelloController LoginScreen = loader.getController();

        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }
}
