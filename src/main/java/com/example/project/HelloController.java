package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloController {
    @FXML
    public TextField ID;
    public PasswordField password;
    public Text status;
    public Button loginBtn;

    @FXML
    private AnchorPane loginPane;

    private Stage stage;
    private Scene scene;
    private Person person;
    public void exitBtn(ActionEvent event) {
        System.exit(0);
    }


    public void LoginBtn(ActionEvent event) throws SQLException, IOException {
        Singleton_Connector instance = Singleton_Connector.getInstance();

        Order order1 = new Order(0,1,"Mercedes",50000,"","","",2000,
                2000,"",5);




//        FXMLLoader loader1 = ScreenSelector.getImageSlider();
//        Parent Screen = loader1.load();
//        ImageSliderController imageController = loader1.getController();
//        imageController.setTheImages(order1);
//        stage = new Stage();
//        Scene scene2 = new Scene(Screen,480 , 291);
//        stage.setScene(scene2);
//        stage.show();



        try{
            person = instance.checkCredentials(ID.getText(), password.getText());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();


            Parent UserScreen;
            if(person instanceof Admin_User) {
                FXMLLoader loader = ScreenSelector.getAdminScreen();
                UserScreen = loader.load();
                AdminMainScreenCotroller adminController = loader.getController();
                adminController.sendPersonData(person);

            }
            else {
                FXMLLoader loader = ScreenSelector.getUserMainScreen();
                UserScreen = loader.load();
                MainScreenController mainController = loader.getController();
                mainController.sendPersonData(person);
               // mainController.HomeScreenBtn(event);
            }


            //To be able to drag it
            UserScreen.setOnMousePressed(pressEvent -> {
                UserScreen.setOnMouseDragged(dragEvent -> {
                    stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                    stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                });
            });

            Scene scene = new Scene(UserScreen);
            scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (LoginExceptionEmpty E) {
            status.setText("Please Enter Your ID And Password");
            ID.clear();
            password.clear();
        } catch (WrongPasswordException E) {
            status.setText("Wrong Password");
            password.clear();
        } catch (InvalidInputException E) {
            status.setText("Invalid ID");
            ID.clear();
            password.clear();
        }


    }
    public void SignUpBtn(ActionEvent event)throws IOException{
        FXMLLoader loader = ScreenSelector.getSignUpScreen();
        Parent SignUpScreen = loader.load();
        loginPane.getChildren().removeAll();
        loginPane.getChildren().setAll ( (AnchorPane)SignUpScreen );
        SignUpController signUPScreen = loader.getController();
    }

}