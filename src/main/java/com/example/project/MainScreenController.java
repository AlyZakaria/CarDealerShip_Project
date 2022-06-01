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

public class MainScreenController implements Initializable{

    public Button SettingsButton;

    public Button LogOutBtn;

    public Button AddOrderBtn;
    public Button HomeScreenBtn;
    public Button WishListBtn;
    public Button MyOrders;

    @FXML
    private Label nameField;

    @FXML
    private Label date;
    @FXML
    private Label name;


    @FXML
    private AnchorPane MainPane;

    private Person person;

    /*public MainScreenController(Person person) {
        this.user = new User(person.getID(), person.getName(), person.getAge(), person.getAddress(), person.getEmail(),
                person.getPhoneNumber(), person.getGender(), person.getPassword(), person.getNational_ID());
    }
*/

    public void exitBtn(ActionEvent event) { System.exit(0); }

    @FXML
    public void SettingsButton(ActionEvent event) throws IOException  {
        FXMLLoader Loader = ScreenSelector.getSettingScreen();
        Parent SettingPane = Loader.load();
        SettingsController controller = Loader.getController();

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll((AnchorPane)SettingPane);

        controller.SetValues((User) person);
    }
    @FXML
    public void LogOutBtn(ActionEvent event) throws IOException {
        Parent loginScreen = ScreenSelector.getLoginScreen().load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void HomeScreenBtn(ActionEvent event) throws IOException, SQLException {

        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setPrefSize(695, 474);;

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
            System.out.println(person instanceof User? "Yes" : "NO");
            controller.getOrder(order,MainPane,person , false);
            flowPane.getChildren().add(OrderPane);

        }
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }

    @FXML
    public void MyOrdersBtn(ActionEvent event) throws IOException, SQLException {
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
            controller.getOrder(order,MainPane,person, true);
            flowPane.getChildren().add(OrderPane);
        }

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(scrollPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            HomeScreenBtn(new ActionEvent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public void sendPersonData(Person person) throws SQLException, IOException {
       // System.out.println("MainScreenController");
       // System.out.println(person instanceof User? "Yes" : "NO");
        this.person = person;
        name.setText("Hello, " + person.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));
        HomeScreenBtn(new ActionEvent());
    }

    public void sendPersonData(Person person) throws SQLException {
        user = (User) person;
        name.setText("Hello, " + user.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));
    }
    public void sendPersonData(Person person) throws SQLException, IOException {
        this.person = person;
        name.setText("Hello, " + person.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));
        HomeScreenBtn(new ActionEvent());
    }



    public void AddOrderBtn(ActionEvent event) throws IOException {
        FXMLLoader Loader = ScreenSelector.getAddOrder();
        Parent AddOrder = Loader.load();
        AddOrderController controller = Loader.getController();

        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(AddOrder);
        controller.SetValues(user);

    }

}