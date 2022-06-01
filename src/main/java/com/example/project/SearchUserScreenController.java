package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchUserScreenController implements Initializable {
    
    public Label Name_lbl;
    public Label Email_lbl;
    public Label Address_lbl;
    public Label Phone_lbl;
    public Label NatID_lbl;

    public Button search_btn;
    public Button Delete_btn;

    public TableView<Order> tableview;
    public TableColumn<Order, Integer> Order_ID;
    public TableColumn<Order, String> Model;
    public TableColumn<Order, String> Year;
    public TableColumn<Order, String> Transmission;
    public TableColumn<Order, String> Price;

    public Label status_lbl;
    public TextField id_txt;

    private Label[] labels;

    User user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labels = new Label[] {Name_lbl, Email_lbl, Address_lbl, Phone_lbl, NatID_lbl};
        hideInfo();
    }

    public void searchUser(ActionEvent event) throws SQLException {
        hideInfo();
        tableview.setVisible(false);
        Delete_btn.setVisible(false);
        Singleton_Connector instance = Singleton_Connector.getInstance();
        status_lbl.setText("");
        int id;
        try {
            id = Integer.parseInt(id_txt.getText());
            user = instance.getUserByID(id);
        }  catch (UserNotFoundException e) {
            status_lbl.setText("User Not Found");
            id_txt.clear();
            return;
        } catch (Exception e) {
            status_lbl.setText("Please Enter A Valid ID");
            id_txt.clear();
            return;
        }
        Name_lbl.setText("Name: " +user.getName());
        Email_lbl.setText("Email: " + user.getEmail());
        Address_lbl.setText("Address: " + user.getAddress());
        Phone_lbl.setText("Phone: " + user.getPhoneNumber());
        NatID_lbl.setText("National ID: " + user.getNational_ID());

        ArrayList<Order> userOrders = instance.getAllUserOrders(user);
        ObservableList<Order> orders = FXCollections.observableArrayList();
        for(Order order: userOrders){
            orders.add(order);
        }

        Order_ID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        Model.setCellValueFactory(new PropertyValueFactory<>("model"));
        Year.setCellValueFactory(new PropertyValueFactory<>("year"));
        Transmission.setCellValueFactory(new PropertyValueFactory<>("Transmission"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableview.setItems(orders);
        showInfo();
    }
    public void deleteUser(ActionEvent event) throws SQLException {
        Admin_User admin = new Admin_User();
        admin.deleteUser(user);
    }
    private void hideInfo() {
        for(Label label : labels) {
            label.setVisible(false);
        }
        tableview.setVisible(false);
        Delete_btn.setVisible(false);
    }
    private void showInfo() {
        for(Label label : labels) {
            label.setVisible(true);
        }
        tableview.setVisible(true);
        Delete_btn.setVisible(true);
    }
}
