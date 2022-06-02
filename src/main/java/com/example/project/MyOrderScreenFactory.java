package com.example.project;

import javafx.fxml.FXMLLoader;

public class MyOrderScreenFactory implements iFactory{
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getMyOrderScreen();
    }
}
