package com.example.project;

import javafx.fxml.FXMLLoader;

public class OrderMaker {
    iFactory factory;
    public OrderMaker(iFactory factory) {
        this.factory = factory;
    }
    public FXMLLoader getOrderFXML() {
        return factory.getOrderCard();
    }
}
