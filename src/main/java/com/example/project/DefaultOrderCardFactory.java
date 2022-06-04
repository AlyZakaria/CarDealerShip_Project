package com.example.project;

import javafx.fxml.FXMLLoader;

public class DefaultOrderCardFactory implements iFactory{
    @Override
    public FXMLLoader getOrderCard() {
        return ScreenSelector.getOrderCard();
    }
}

