package com.example.project;

import javafx.fxml.FXMLLoader;

public class OrderUserScreenFactory implements iFactory{
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getUserOrderScreen();
    }
}
