package com.example.project;

import javafx.fxml.FXMLLoader;

public class AdminOrderScreenFactory implements iFactory{
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getAdminOrderScreen();
    }
}
