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
        return new FXMLLoader(HelloApplication.class.getResource("ImageSlider.fxml"));
    }

    public static FXMLLoader getAddOrder(){
        return new FXMLLoader(HelloApplication.class.getResource("AddOrder.fxml"));
    }

    public static FXMLLoader getOrderScreen(){
        return new FXMLLoader(HelloApplication.class.getResource("OrderScreen.fxml"));
    }
    public static FXMLLoader getUserOrderScreen(){
        return new FXMLLoader(HelloApplication.class.getResource("UserOrderScreen.fxml"));
    }
    public static FXMLLoader getAdminOrderScreen() {
        return new FXMLLoader(HelloApplication.class.getResource("AdminOrderScreen.fxml"));
    }
    public static FXMLLoader getSearchUser(){
        return new FXMLLoader(HelloApplication.class.getResource("SearchUserScreen.fxml"));
    }
    public static FXMLLoader getMyOrderScreen(){
        return new FXMLLoader(HelloApplication.class.getResource("MyOrderScreen.fxml"));
    }
    public static FXMLLoader getPendingOrderScreen() {
        return new FXMLLoader(HelloApplication.class.getResource("PendingOrderScreen.fxml"));
    }
}