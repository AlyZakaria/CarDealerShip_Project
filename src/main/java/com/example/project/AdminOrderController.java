package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminOrderController implements Initializable {
    @FXML
    private Label Message;
    @FXML
    private AnchorPane MainOrderPane;
    Order order;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPane(AnchorPane pane,Order order) throws IOException {
        this.order = order;
        MainOrderPane.getChildren().setAll(pane);
    }



    public void deleteOrder(ActionEvent event) throws SQLException {
        order.DeleteOrder();
        Message.setText("Order has Been deleted");
    }
}
