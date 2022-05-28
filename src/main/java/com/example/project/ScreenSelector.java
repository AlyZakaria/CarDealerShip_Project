package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Locale;

public class ScreenSelector {
    public static FXMLLoader getLoginScreen() {
        return new FXMLLoader(HelloApplication.class.getResource("LoginScreen.fxml"));
    }
    public static FXMLLoader getUserMainScreen() {
        return new FXMLLoader(HelloApplication.class.getResource("UserMainScreen.fxml"));
    }
    public static FXMLLoader getSettingScreen(){
        return new FXMLLoader(HelloApplication.class.getResource("SettingScreen.fxml"));
    }
    public static FXMLLoader getHomeScreen(){
        return new FXMLLoader(HelloApplication.class.getResource("HomeScreen.fxml"));
    }

    public static FXMLLoader getMyOrders(){
        return new FXMLLoader(HelloApplication.class.getResource("MyOrders.fxml"));
    }
    public static FXMLLoader getAdminScreen() {
        return new FXMLLoader(HelloApplication.class.getResource("AdminMainScreen.fxml"));
    }
    public static FXMLLoader getSignUpScreen(){
        return new FXMLLoader(HelloApplication.class.getResource("SignUpScreen.fxml"));
    }

    public static FXMLLoader getOrderCard(){
        return new FXMLLoader(HelloApplication.class.getResource("OrderCard.fxml"));
    }
    public static FXMLLoader getImageSlider(){
        return new FXMLLoader(HelloApplication.class.getResource("imageSlider2.fxml"));
    }

    public static FXMLLoader getAddOrder(){
        return new FXMLLoader(HelloApplication.class.getResource("AddOrder.fxml"));
    }

}