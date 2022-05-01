package com.example.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class Login {
    private int ID;
    private String password;

    public TextField ID_txt = new TextField();
    public TextField Password_txt = new TextField();
    public Button Login_btn = new Button("Login");
    Person person;

    public void ID_Password_Setter(int ID, String password) {
        this.ID = ID;
        this.password = password;
    }



    public Pane getPane() {
        HBox fullPane = new HBox();

        File image = new File("Images\\login.png");
        ImageView loginImg = new ImageView(image.getAbsolutePath());
        loginImg.setFitWidth(1100);
        fullPane.getChildren().add(loginImg);

        ID_txt.setPrefHeight(40);
        ID_txt.setPrefWidth(350);

        Password_txt.setPrefHeight(40);
        Password_txt.setPrefWidth(350);

        GridPane rightPane = new GridPane();
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(10, 10, 10, 10));
        rightPane.setVgap(10);
        rightPane.setHgap(20);

        rightPane.add(ID_txt, 0, 0);
        rightPane.add(Password_txt, 0, 1);

        Login_btn.setPrefWidth(350);
        Login_btn.setPrefHeight(40);
        rightPane.add(Login_btn, 0, 2);
        fullPane.getChildren().add(rightPane);

        return fullPane;
    }

}
