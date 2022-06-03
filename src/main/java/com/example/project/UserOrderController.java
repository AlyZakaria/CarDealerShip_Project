package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserOrderController implements Initializable {

    @FXML
    private Button AddWishlist;
    @FXML
    private Button RemoveWishlist;
    @FXML
    private Label Message;
    @FXML
    private AnchorPane MainOrderPane;

    ArrayList<Order> Wishlists = new ArrayList<Order>();
    Boolean AddButton = true;

    User user;
    Order order;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPane(AnchorPane pane, Order order, User person) throws IOException, SQLException {
        this.order = order;
        this.user = person;
        MainOrderPane.getChildren().removeAll();
        MainOrderPane.getChildren().setAll(pane);
        Wishlists = Singleton_Connector.getInstance().getUserWishList(user);

        if(user.getID() == order.getUserId()){
            RemoveWishlist.setDisable(true);
            AddWishlist.setDisable(true);
        }else {
            for (Order mywish : Wishlists) {
                if (mywish.getOrderId() == order.getOrderId()) {
                    AddButton = false;
                    break;
                }

            }
            if (AddButton) {
                RemoveWishlist.setDisable(true);
                AddWishlist.setDisable(false);
            } else {

                AddWishlist.setDisable(true);
                RemoveWishlist.setDisable(false);
            }
        }
    }
    public void AddWishList() throws SQLException {
        Singleton_Connector.getInstance().AddToWishList(user,order);
        Message.setText("Order has been added to wishlist");
        AddWishlist.setDisable(true);
        RemoveWishlist.setDisable(false);
    }
    public void RemoveWishList() throws SQLException {
        Singleton_Connector.getInstance().RemoveFromWishList(user,order);
        Message.setText("Order has been removed from wishlist");
        RemoveWishlist.setDisable(true);
        AddWishlist.setDisable(false);
    }
}
