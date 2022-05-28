package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {


    @FXML
    private RadioButton Male;
    @FXML
    private RadioButton Female;

    @FXML
    private Label LabelMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField National_IDField;
    @FXML
    private TextField AgeField;
    private Stage stage;
    private User user;

    public void CloseBtn(ActionEvent event) throws IOException {System.exit(0);}

    public void returnBack(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = ScreenSelector.getLoginScreen();
        Parent loginScreen = loader.load();
        HelloController LoginScreen = loader.getController();

        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }

    public void SignUpBtn(ActionEvent event) throws IOException {

        Singleton_Connector instance = Singleton_Connector.getInstance();
        Admin_User admin = new Admin_User(5,"Mina", 21, "rasafa",
                "minamagdy651@gmail.com", "01203662382", 1, "test", "001");
        int gender = -1;

        try {
            if(nameField.getText().equals("") || AgeField.getText().equals("") || PhoneField.getText().equals("")
                    || EmailField.getText().equals("") || AddressField.getText().equals("") || National_IDField.getText().equals(""))
                throw new Exception();

            if(!nameField.getText().matches("[a-zA-Z]+")  || AgeField.getText().matches("[a-zA-Z]+") ||
                National_IDField.getText().matches("[a-zA-Z]+") || PhoneField.getText().matches("[a-zA-Z]+"))
                throw new Exception();

            if(instance.userExists(National_IDField.getText()) != -1)
                throw new UserExistsException();


            int newId = instance.getLastUserID();
            if(Male.isSelected())
                gender = 0;
            if(Female.isSelected())
                gender = 1;

            User user = new User(newId , nameField.getText() , Integer.parseInt(AgeField.getText() ), AddressField.getText(),
                    EmailField.getText() , PhoneField.getText() , gender , "", National_IDField.getText() );
            String newpassword = admin.addUser(user);

                LabelMessage.setText("UserID is " + newId + "  " + "\nPassword is "+newpassword);

        }catch (UserExistsException e){
                LabelMessage.setText("User is already exist..");
        }catch (Exception e) {
                LabelMessage.setText("Invalid Inputs, Try Again..");
        }


    }
}
