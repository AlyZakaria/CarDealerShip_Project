package com.example.project;

import javafx.fxml.FXMLLoader;

public class BigOrderCardFactory implements iFactory{
    //change it later
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getOrderCard();
    }
}
