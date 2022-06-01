package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserOrderController implements Initializable {

    @FXML
    private AnchorPane MainOrderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPane(AnchorPane pane) throws IOException {
        MainOrderPane.getChildren().removeAll();
        MainOrderPane.getChildren().setAll(pane);

    }
}
