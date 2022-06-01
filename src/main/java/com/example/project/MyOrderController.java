package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MyOrderController implements Initializable {
    @FXML
    private AnchorPane MainPaneOrder;
    @FXML
    private Label Pending;
    @FXML
    private Label Confirmed;
    Order order;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPane(AnchorPane orderScreen, Order order) {

        this.order = order;
        if(order.getStatus() == 0) {
            Pending.setVisible(true);
            Confirmed.setVisible(false);
        }
        else {
            Pending.setVisible(false);
            Confirmed.setVisible(true);
        }
        MainPaneOrder.getChildren().removeAll();
        MainPaneOrder.getChildren().setAll(orderScreen);
    }
}
