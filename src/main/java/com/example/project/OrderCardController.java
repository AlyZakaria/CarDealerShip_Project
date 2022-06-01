package com.example.project;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;

public class OrderCardController {
    public ImageView OrderImg;
    public Label PriceLbl;
    public Label ModelLbl;
    private Order order;
    public void t(MouseEvent event) throws IOException {
        System.out.println("test");
    }
    public void getOrder(Order order) {
        this.order = order;
        setData();
    }
    private void setData() {
        File[] files = File_Handler.getOrderDirectory(order).listFiles();
        Image img = new Image(files[0].getAbsolutePath());

        OrderImg.setImage(img);
        OrderImg.setFitHeight(112);
        OrderImg.setFitWidth(200);
        PriceLbl.setText("$"+String.valueOf(order.getPrice()));
        ModelLbl.setText(order.getModel());
    }
}
