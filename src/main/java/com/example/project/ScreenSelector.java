package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ScreenSelector {
    public static FXMLLoader getLoginScreen() {
        return  new FXMLLoader(HelloApplication.class.getResource("LoginScreen.fxml"));
    }

}