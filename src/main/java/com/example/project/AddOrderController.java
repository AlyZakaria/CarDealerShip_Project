package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddOrderController implements Initializable {
    public ComboBox CarTypeCB;
    public ComboBox TransmissionCB;
    public Label status;
    public Button AddOrderBtn;
    public Button AddPicturesBtn;
    public TextField ColorTxt;
    public TextField ModelTxt;
    public TextField YearTxt;
    public TextField KilometerTxt;
    public TextField PriceTxt;
    public TextField ExtraInfoTxt;

    private String carType;
    private String TransType;

    private List<File> list;

    private User user;

    ObservableList<String> carTypes = FXCollections.observableArrayList("Sedan", "Suv", "Truck");
    ObservableList<String> transTypes = FXCollections.observableArrayList("Automatic", "Manual");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CarTypeCB.setItems(carTypes);
        TransmissionCB.setItems(transTypes);
    }

    public void ComboBoxChanged(ActionEvent event) {
        carType = (String) CarTypeCB.getValue();
        TransType = (String) TransmissionCB.getValue();
    }

    private void cleanTextFields() {
        ColorTxt.clear();
        ModelTxt.clear();
        YearTxt.clear();
        KilometerTxt.clear();
        PriceTxt.clear();
        ExtraInfoTxt.clear();
    }
    public void AddOrderBtn(ActionEvent event) throws SQLException, IOException {
        try {
            String color = ColorTxt.getText();
            String model = ModelTxt.getText();
            int year = Integer.parseInt(YearTxt.getText());
            int kilos = Integer.parseInt(KilometerTxt.getText());
            int price = Integer.parseInt(PriceTxt.getText());
            String extraInfo = ExtraInfoTxt.getText();
            Singleton_Connector instance = Singleton_Connector.getInstance();

            Order order = new Order(user.getID(), instance.getLastOrderID(), carType, price, TransType, color, model, year, kilos, extraInfo, 0 );
            File_Handler.createOrderFile(list, order);
            instance.addOrder(order);
            status.setText("Order Added Successfully, Order ID: " + order.getOrderId());
            cleanTextFields();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            status.setText("Invalid Inputs");
            cleanTextFields();
        }


    }
    public void AddPicturesBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        list = fc.showOpenMultipleDialog(null);

    }

    public void SetValues(User user) {
        this.user = user;
    }
}

