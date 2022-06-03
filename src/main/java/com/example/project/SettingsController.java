package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsController implements Initializable {

    public Button ChangePassBtn;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Label PasswordMessage;
    @FXML
    private Label MessageText;
    @FXML
    private Label genderField;
    @FXML
    private Label nationalIdField;
    @FXML
    private TextField EmailText;
    @FXML
    private TextField phoneText;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private Label ageField;
    @FXML
    private TextField addressText;
    @FXML
    private Label MyOrders;
    @FXML
    private Label nameField;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SetValues(User user){
        try{
            this.user = user;
            nameField.setText(user.getName());
            ageField.setText(String.valueOf(user.getAge()));
            if(user.getGender() == 0)
                genderField.setText("Male");
            else
                genderField.setText("Female");
            nationalIdField.setText(user.getNational_ID());
            EmailText.setPromptText(user.getEmail());
            addressText.setPromptText(user.getAddress());
            phoneText.setPromptText(user.getPhoneNumber());
            MyOrders.setText(String.valueOf(Order.getAllUserOrders(user).size()));
        }catch(Exception e){
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void ChangePassBtn(ActionEvent event) throws IOException {
        String oldpassword = oldPassword.getText();
        String newpassword = newPassword.getText();
        try{
            if(oldpassword.equals("") || newpassword.equals(""))
                throw new Exception();
            if(user.changePassword(user.getID() , oldpassword, newpassword)) {
                PasswordMessage.setText("Password has been Changed!");
                user.setPassword(newpassword);
            }
            else
                throw new Exception();

        }catch(Exception e) {
            PasswordMessage.setText("Invalid Input");
        }
        oldPassword.clear();newPassword.clear();
    }

    public void EditInfo(ActionEvent event) throws IOException {

        try{
            String Email = EmailText.getText();
            String PhoneNumber = phoneText.getText();
            String Address = addressText.getText();
            if(Email.equals("") || PhoneNumber.equals("") || Address.equals("") )
                throw new Exception();

            user = User.EditInfo(user,Email,PhoneNumber,Address);
            if(user != null){
                MessageText.setText("The Information has been Updated!");
                EmailText.clear();phoneText.clear();
                addressText.clear();
                SetValues(user);
            }
            else
                throw new Exception();

        }catch(Exception e){
            MessageText.setText("Invalid Input");
        }
    }


}
