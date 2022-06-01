package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    public Order order;
    public User user;

    @FXML
    private Label orderPrice;

    @FXML
    private Label ExtraInfo;
    @FXML
    private Label Color;

    @FXML
    private Label Kilometers;
    @FXML
    private Label Transmission;

    @FXML
    private Label OrderID;

    @FXML
    private Label carType;

    @FXML
    private Label orderModel;

    @FXML
    private Label userPhone;

    @FXML
    private Label userEmail;
    @FXML
    private Label userName;

    @FXML
    private StackPane ImagePane;


    public void WishlistFunction(ActionEvent event){
        System.out.println("LIKED");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void sendOrderInfo(Order order, User user) {
        this.order = order;
        this.user = user;
        FXMLLoader loader = ScreenSelector.getImageSlider();
        try {
            Parent imageSlider = loader.load();
            ImageSliderController ImageController = loader.getController();
            ImageController.setTheImages(order);
            ImagePane.getChildren().addAll((AnchorPane)imageSlider);
            userName.setText(user.getName());
            userEmail.setText(user.getEmail());
            userPhone.setText(user.getPhoneNumber());
//            OrderID.setText(String.valueOf(order.getOrderId()));
            orderModel.setText(order.getModel() + " - " + order.getYear());
            carType.setText("   " + "Car Type:      " + order.getCarType());
            Transmission.setText("   "+"Transmission:      "+ order.getTransmission());
            Kilometers.setText("   "+"Kilometers:           "+ order.getKilometers());
            Color.setText("   "+ "Color:           "+ order.getColor());
            orderPrice.setText("   " +"Price:            $"+ order.getPrice());
            ExtraInfo.setText("  "+ "Extra Information:      " + order.getExtraInfo());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
