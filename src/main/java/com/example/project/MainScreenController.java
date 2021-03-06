package com.example.project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable{



    @FXML
    private ComboBox comboBox;

    @FXML
    private Label nameField;

    @FXML
    private Label date;
    @FXML
    private Label name;


    Boolean SortD = false;
    Boolean SortI = false;
    Boolean AutomaticCar = false;
    Boolean ManualCar = false;
    Boolean SedanCar = false;
    Boolean SuvCar = false;
    Boolean Truck = false;
    @FXML
    private AnchorPane MainPane;

    private Person person;

    ObservableList<String> Sort = FXCollections.observableArrayList("High to Low", "Low to High" , "Automatic" , "Manual",
            "Sedan" , "Suv", "Truck");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exitBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void SettingsButton(ActionEvent event) throws IOException  {
        comboBox.setVisible(false);
        comboBox.getSelectionModel().clearSelection();

        FXMLLoader Loader = ScreenSelector.getSettingScreen();
        Parent SettingPane = Loader.load();
        SettingsController controller = Loader.getController();

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll((AnchorPane)SettingPane);

        controller.SetValues((User) person);
    }
    @FXML
    public void LogOutBtn(ActionEvent event) throws IOException {
        comboBox.setVisible(false);
        comboBox.getSelectionModel().clearSelection();

        Parent loginScreen = ScreenSelector.getLoginScreen().load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void HomeScreenBtn(ActionEvent event) throws IOException, SQLException {
        comboBox.setVisible(true);
        comboBox.setItems(Sort);

        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(695, 474);
        ArrayList<Order> orders;
        if(SortD) {
            orders = Singleton_Connector.getInstance().sortOrderDecreasing();
            SortD = false;
        }
        else if(SortI){
            orders = Singleton_Connector.getInstance().sortOrderIncreasing();
            SortI = false;
        }
        else if(AutomaticCar){
            orders = Singleton_Connector.getInstance().getAll_AutomaticCars();
            AutomaticCar = false;
        }else if(ManualCar){
            orders = Singleton_Connector.getInstance().getAll_ManualCars();
            ManualCar = false;
        }else if(SedanCar){
            orders = Singleton_Connector.getInstance().getAll_SedanCars();
            SedanCar = false;
        }else if(SuvCar){
            orders = Singleton_Connector.getInstance().getAll_SuvCars();
            SuvCar = false;
        }else if(Truck){
            orders = Singleton_Connector.getInstance().getAll_Trucks();
            Truck = false;
        } else{
            comboBox.getSelectionModel().clearSelection();
            orders = Order.getAllOrders();
        }
        for(Order order : orders) {
            OrderMaker orderMaker = new OrderMaker(new DefaultOrderCardFactory());
            FXMLLoader loader = orderMaker.getOrderFXML();
            Parent OrderPane = loader.load();
            OrderCardController  controller = loader.getController();
            controller.getOrder(order,MainPane,person, false , comboBox);
            flowPane.getChildren().add(OrderPane);

        }
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }

    @FXML
    public void MyOrdersBtn(ActionEvent event) throws IOException, SQLException {
        comboBox.setVisible(false);
        comboBox.getSelectionModel().clearSelection();

        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(695, 474);
        ArrayList<Order> orders = Order.getAllUserOrders((User) person);
        for(Order order : orders) {
            OrderMaker orderMaker = new OrderMaker(new DefaultOrderCardFactory());
            FXMLLoader loader = orderMaker.getOrderFXML();
            Parent OrderPane = loader.load();
            OrderCardController  controller = loader.getController();
            controller.getOrder(order,MainPane,person, true, comboBox);
            flowPane.getChildren().add(OrderPane);
        }

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }





    public void sendPersonData(Person person) throws SQLException, IOException {

        this.person = person;
        name.setText("Hello, " + person.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));
        HomeScreenBtn(new ActionEvent());
    }


    public void AddOrderBtn(ActionEvent event) throws IOException {
        comboBox.setVisible(false);
        comboBox.getSelectionModel().clearSelection();

        FXMLLoader Loader = ScreenSelector.getAddOrder();
        Parent AddOrder = Loader.load();
        AddOrderController controller = Loader.getController();
        controller.SetValues((User) person);

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(AddOrder);
    }

    public void WishListBtn(ActionEvent event) throws SQLException, IOException {
        comboBox.setVisible(false);
        comboBox.getSelectionModel().clearSelection();

        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(695, 474);
        ArrayList<Order> orders = Singleton_Connector.getInstance().getUserWishList((User) person);
        for(Order order : orders) {
            OrderMaker orderMaker = new OrderMaker(new DefaultOrderCardFactory());
            FXMLLoader loader = orderMaker.getOrderFXML();
            Parent OrderPane = loader.load();
            OrderCardController  controller = loader.getController();
            //@Aly
            controller.getOrder(order,MainPane,person, false, comboBox);
            flowPane.getChildren().add(OrderPane);
        }

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }

    public void ComboAction(ActionEvent event) throws SQLException, IOException {
        String val = (String) comboBox.getValue();
        ArrayList<Order> orders;
        try {
            if (val.equals("High to Low")) {
                SortD = true;
                HomeScreenBtn(new ActionEvent());
            } else if (val.equals("Low to High")) {
                SortI = true;
                HomeScreenBtn(new ActionEvent());
            }else if(val.equals("Automatic")){
                AutomaticCar = true;
                HomeScreenBtn(new ActionEvent());
            }else if(val.equals("Manual")){
                ManualCar = true;
                HomeScreenBtn(new ActionEvent());
            }else if(val.equals("Sedan")){
                SedanCar = true;
                HomeScreenBtn(new ActionEvent());
            }else if(val.equals("Suv")){
                SuvCar = true;
                HomeScreenBtn(new ActionEvent());
            }else if(val.equals("Truck")){
                Truck = true;
                HomeScreenBtn(new ActionEvent());
            }

        }catch(NullPointerException e){

        }
    }
}