package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderCardController implements Initializable {
    public ImageView OrderImg;
    public Label PriceLbl;
    public Label ModelLbl;
    Order order;
    Person person;
    public AnchorPane mainPane;
    boolean myOrder = false;

    public void t(MouseEvent event) throws IOException, SQLException {
        ScrollPane ScrollPane = new ScrollPane();
        ScrollPane.setPrefSize(695, 474);
        FXMLLoader loader = ScreenSelector.getOrderScreen();
        Parent OrderScreen = loader.load();
        OrderController controller = loader.getController();


        if(person instanceof User && !myOrder) {

            person = Singleton_Connector.getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            FXMLLoader loader1 = ScreenSelector.getUserOrderScreen();
            AnchorPane UserOderScreen = loader1.load();
            UserOrderController controller1 = loader1.getController();
            controller1.setPane((AnchorPane) OrderScreen);
            ScrollPane.setContent(UserOderScreen);
        }
        else if(person instanceof Admin_User){
            person = Singleton_Connector.getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            FXMLLoader loader1 = ScreenSelector.getAdminOrderScreen();
            AnchorPane AdminOrderScreen = loader1.load();
            AdminOrderController controller1 = loader1.getController();
            controller1.setPane((AnchorPane) OrderScreen,order);
            ScrollPane.setContent(AdminOrderScreen);
        }
        else{
            person = Singleton_Connector.getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            FXMLLoader loader2 = ScreenSelector.getMyOrderScreen();
            AnchorPane MyOrder = loader2.load();
            MyOrderController controller2 = loader2.getController();
            controller2.setPane((AnchorPane) OrderScreen,order);
            ScrollPane.setContent(MyOrder);
        }
        mainPane.getChildren().removeAll();
        mainPane.getChildren().setAll(ScrollPane);

    }
    public void getOrder(Order order, AnchorPane mainPane, Person person , Boolean myOrder) {
        this.myOrder = myOrder;
        this.mainPane = mainPane;
        this.order = order;
        this.person = person;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
