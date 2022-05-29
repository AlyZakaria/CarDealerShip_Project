package com.example.project;

import javafx.fxml.FXMLLoader;

public class OrderCardDeleteFactory implements iFactory{
    //change it later
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getOrderCard();
    }
}
