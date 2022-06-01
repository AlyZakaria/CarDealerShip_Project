package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserOrderScreen implements Initializable {

    @FXML
    private AnchorPane MainOrderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPane(AnchorPane pane){
        MainOrderPane = pane;

    }
}
