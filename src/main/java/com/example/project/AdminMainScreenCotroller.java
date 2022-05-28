package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminMainScreenCotroller implements Initializable {

    public AnchorPane TopAnchorPane;
    public Button userScreenExitBtn;
    public Button HomeBtn;
    public Button PendingOrdersBtn;
    public Button SearchUserBtn;
    public Button SettingsBtn;
    public Button LogOutBtn;
    public Label date;
    public AnchorPane MainPane;
    public Label name;
    private Person user;

    public void exitBtn(ActionEvent event) {
        System.exit(0);
    }

    public void SettingsButton(ActionEvent event) throws IOException {
        FXMLLoader Loader = ScreenSelector.getSettingScreen();
        Parent SettingPane = Loader.load();
        SettingsController controller = Loader.getController();

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll((AnchorPane)SettingPane);
        controller.SetValues((User) user);
    }
    public void LogOutBtn(ActionEvent event) throws IOException {
        Parent loginScreen = ScreenSelector.getLoginScreen().load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }

    public void sendPersonData(Person person) throws SQLException {
        user = new User(person.getID(), person.getName(), person.getAge(), person.getAddress(), person.getEmail(),
                person.getPhoneNumber(), person.getGender(), person.getPassword(), person.getNational_ID());

        name.setText("Hello, " + user.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));

    }
    public void HomeScreenBtn(ActionEvent event) throws IOException, SQLException {
        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);

        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(MainPane.getWidth(), MainPane.getHeight());
        ArrayList<Order> orders = Order.getAllOrders();
        for(Order order : orders) {
            FXMLLoader loader = ScreenSelector.getOrderCard();
            Parent OrderPane = loader.load();
            OrderCardController  controller = loader.getController();
            controller.getOrder(order);
            flowPane.getChildren().add(OrderPane);
        }

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }

    public void searchUserBtn() {
        System.out.println("Test");
    }

    public void pendingOrdersBtn() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);

        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(698, 470);
        ArrayList<Order> orders = null;
        try {
            orders = Order.getAllOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Order order : orders) {
            FXMLLoader loader = ScreenSelector.getOrderCard();
            Parent OrderPane = null;
            try {
                OrderPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            OrderCardController  controller = loader.getController();
            controller.getOrder(order);
            flowPane.getChildren().add(OrderPane);
        }

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }
}
