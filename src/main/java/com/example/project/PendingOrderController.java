package com.example.project;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class PendingOrderController {
    public Label Message;
    public AnchorPane MainOrderPane;
    public Button RejectOrder;
    public Button AcceptOrder;
    private Order order;

    public void RejectOrder(ActionEvent event) throws SQLException {
        order.deleteOrder();
        Message.setText("Order Rejected");
        AcceptOrder.setDisable(true);
    }

    public void AcceptOrder(ActionEvent event) throws SQLException {
        order.confirmOrder();
        Message.setText("Order Accepted");
        RejectOrder.setDisable(true);
    }
    public void setPane(AnchorPane pane,Order order) throws IOException {
        this.order = order;
        MainOrderPane.getChildren().setAll(pane);
    }
    
}
