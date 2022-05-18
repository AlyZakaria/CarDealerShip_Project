package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class HelloController {

    public TextField ID;
    public PasswordField password;
    public Text status;

    public void exitBtn(ActionEvent event) {
        System.exit(0);
    }
    public void LoginBtn(ActionEvent event) throws SQLException {
        Singleton_Connector instance = Singleton_Connector.getInstance();
        try{
            instance.checkCredentials(ID.getText(), password.getText());
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
}