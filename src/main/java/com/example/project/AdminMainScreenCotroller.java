package com.example.project;

import javafx.event.ActionEvent;
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
    private Person person;

    public void exitBtn(ActionEvent event) {
        System.exit(0);
    }

    public void SettingsButton(ActionEvent event) throws IOException {
        FXMLLoader Loader = ScreenSelector.getSettingScreen();
        Parent SettingPane = Loader.load();
        SettingsController controller = Loader.getController();

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll((AnchorPane)SettingPane);
        controller.SetValues ((User) person);
    }
    public void LogOutBtn(ActionEvent event) throws IOException {
        Parent loginScreen = ScreenSelector.getLoginScreen().load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }

    public void sendPersonData(Person person) throws SQLException, IOException {
        this.person = person;
        name.setText("Hello, " + person.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));
        HomeScreenBtn(new ActionEvent());
    }
    public void HomeScreenBtn(ActionEvent event) throws IOException, SQLException {
        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(695, 474);
        ArrayList<Order> orders = Order.getAllOrders();
        for(Order order : orders) {
            OrderMaker orderMaker = new OrderMaker(new DefaultOrderCardFactory());
            FXMLLoader loader = orderMaker.getOrderFXML();
            Parent OrderPane = loader.load();
            OrderCardController  controller = loader.getController();
            controller.getOrder(order,MainPane, person,false);
            flowPane.getChildren().add(OrderPane);
        }

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }

    public void searchUserBtn() throws IOException {
        FXMLLoader Loader = ScreenSelector.getSearchUser();
        Parent SearchPane = Loader.load();
        SearchUserScreenController controller = Loader.getController();
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(SearchPane);
    }

    public void pendingOrdersBtn() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        }
    }
