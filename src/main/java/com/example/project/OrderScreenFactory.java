package com.example.project;

import javafx.fxml.FXMLLoader;

public class OrderScreenFactory implements iFactory{
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getOrderScreen();
    }
}
