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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable{


    //   private Label no_orders;
    @FXML
    private TextField name;
    @FXML
    private AnchorPane MainPane = new AnchorPane();

    @FXML
    private TextField date;


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
        Parent sidePane = ScreenSelector.getSettingScreen().load();
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll((AnchorPane)sidePane );
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
        Parent HomeScreen = ScreenSelector.getHomeScreen().load();
        MainPane.getChildren().removeAll();
        MainPane.getChildren().setAll( (AnchorPane) HomeScreen );
    }
    @FXML
    // it is not working yet
    public void ChangePassBtn(ActionEvent event) throws IOException {
        try {
            AnchorPane ChangePassScreen = ScreenSelector.getChangePassScreen().load();
            MainPane.getChildren().setAll(ChangePassScreen);

        }catch(NullPointerException e) {
            throw e;
        }
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

       name.setText(user.getName());
       date.setText(String.valueOf(java.time.LocalDate.now()));

      //  no_orders.setText(String.valueOf(Order.getAllOrders(user.getID()).size()));
    }
}
