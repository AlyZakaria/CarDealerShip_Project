package com.example.project;

import javafx.fxml.FXMLLoader;

public class PendingOrderScreenFactory implements iFactory{
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getPendingOrderScreen();
    }
}
