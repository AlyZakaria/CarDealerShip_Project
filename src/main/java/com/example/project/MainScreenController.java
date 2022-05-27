package com.example.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable{

    public Button SettingsButton;

    public Button LogOutBtn;

    public Label MyOdrders;

    @FXML
    private Label nameField;


    @FXML
    private Label date;
    @FXML
    private Label name;


    //   private Label no_orders;

    @FXML
    private AnchorPane MainPane;

    private Person person;
    private User user;

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

        controller.SetValues(user);
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
    public void HomeScreenBtn(ActionEvent event) throws IOException {

        System.out.println("here");
        FlowPane flowPane = new FlowPane();
        for(int i = 0; i < 5; i++) {
            FXMLLoader loader = ScreenSelector.getOrderCard();
            Parent HomeScreen = loader.load();
            flowPane.getChildren().add(HomeScreen);
        }

        flowPane.setHgap(10);
        flowPane.setVgap(10);
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll(flowPane);
    }

    @FXML
    public void MyOrdersBtn(ActionEvent event) throws IOException {
        AnchorPane MyOrder = ScreenSelector.getMyOrders().load();
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll((AnchorPane) MyOrder);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void sendPersonData(Person person) throws SQLException {
        user = new User(person.getID(), person.getName(), person.getAge(), person.getAddress(), person.getEmail(),
                person.getPhoneNumber(), person.getGender(), person.getPassword(), person.getNational_ID());

        name.setText("Hello, " + user.getName());
        date.setText(String.valueOf(java.time.LocalDate.now()));

    }

    @FXML
    public void EditInfo(ActionEvent event) throws IOException{

    }


}