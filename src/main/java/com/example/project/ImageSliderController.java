package com.example.project;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ImageSliderController implements Initializable {

    @FXML
    private AnchorPane mainPane;


    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;

    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;

    ArrayList<AnchorPane> panes = new ArrayList<>();
    ArrayList<ImageView> images = new ArrayList<>();
    int i = 0;
    boolean firstTime = true;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void back(ActionEvent event) throws IOException{
        int maxSize = panes.size();

        if(i > 0){
            panes.get(i).getChildren().removeAll();
            mainPane.getChildren().removeAll();
            panes.get(i).getChildren().setAll(images.get(i));
            mainPane.getChildren().setAll(panes.get(i));
            i--;
        }
        else if (i == 0){
            if(firstTime && maxSize > 1) {
                i++;
                firstTime = false;
            }
            panes.get(i).getChildren().removeAll();
            mainPane.getChildren().removeAll();
            panes.get(i).getChildren().setAll(images.get(i));
            mainPane.getChildren().setAll(panes.get(i));
            i = maxSize-1;
        }
    }


    public void next(ActionEvent event) throws IOException{
        int maxsize = panes.size();
        if(i < maxsize - 1){
            if(firstTime) {
                i++;
                firstTime = false;
            }
            panes.get(i).getChildren().removeAll();
            mainPane.getChildren().removeAll();
            panes.get(i).getChildren().setAll(images.get(i));
            mainPane.getChildren().setAll(panes.get(i));
            i++;
        }
        else if(i == maxsize -1){
            panes.get(i).getChildren().removeAll();
            mainPane.getChildren().removeAll();
            panes.get(i).getChildren().setAll(images.get(i));
            mainPane.getChildren().setAll(panes.get(i));
            i = 0;
        }
        else{
            i = 0;
            panes.get(i).getChildren().removeAll();
            mainPane.getChildren().removeAll();
            panes.get(i).getChildren().setAll(images.get(i));
            mainPane.getChildren().setAll(panes.get(i));
        }
    }



    public void setTheImages(Order order) throws SQLException, IOException {
        File[] files = File_Handler.getOrderDirectory(order).listFiles();
        int  p = 1;

        for(File file: files) {
            if(p == 1) {
                image1 = new ImageView(file.getAbsolutePath());
                images.add(image1);
                image1.setFitWidth(398);
                image1.setFitHeight(240);

                panes.add(pane1);
            }
            else if(p == 2) {
                image2 = new ImageView(file.getAbsolutePath());
                images.add(image2);
                image2.setFitWidth(398);
                image2.setFitHeight(240);

                panes.add(pane2);
            }else {
                image3 = new ImageView(file.getAbsolutePath());
                images.add(image3);
                image3.setFitWidth(398);
                image3.setFitHeight(240);
                panes.add(pane3);
            }
            p++;
        }


        panes.get(0).getChildren().setAll(images.get(0));
        mainPane.getChildren().setAll(panes.get(0));

    }
}