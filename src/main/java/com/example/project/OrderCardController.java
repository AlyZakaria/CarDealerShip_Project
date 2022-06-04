package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
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
    private Order order;
    private Person person;
    public AnchorPane mainPane;
    private ComboBox comboBox;

    private boolean myOrder = false;
    private boolean pending = false;

    public void CardPressed(MouseEvent event) throws IOException, SQLException {
        if(comboBox != null)
            comboBox.setVisible(false);
        ScrollPane ScrollPane = new ScrollPane();
        ScrollPane.setPrefSize(695, 474);
        OrderMaker orderMaker = new OrderMaker(new OrderScreenFactory());
        FXMLLoader loader = orderMaker.getOrderFXML();
        Parent OrderScreen = loader.load();
        OrderController controller = loader.getController();


        if(person instanceof User && !myOrder) {
            OrderMaker orderMaker1 = new OrderMaker(new OrderUserScreenFactory());
            FXMLLoader loader1 = orderMaker1.getOrderFXML();

            AnchorPane UserOderScreen = loader1.load();
            UserOrderController controller1 = loader1.getController();

            controller1.setPane((AnchorPane) OrderScreen, order ,(User)person);
            person = Singleton_Connector.getInstance().getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            ScrollPane.setContent(UserOderScreen);
        }
        else if(person instanceof Admin_User && pending) {
            OrderMaker orderMaker1 = new OrderMaker(new PendingOrderScreenFactory());
            FXMLLoader loader1 = orderMaker1.getOrderFXML();
            person = Singleton_Connector.getInstance().getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            AnchorPane PendingOrderScreen = loader1.load();
            PendingOrderController controller1 = loader1.getController();

            controller1.setPane((AnchorPane) OrderScreen, order);
            ScrollPane.setContent(PendingOrderScreen);
        }
        else if(person instanceof Admin_User){
            person = Singleton_Connector.getInstance().getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            OrderMaker orderMaker1 = new OrderMaker(new AdminOrderScreenFactory());
            FXMLLoader loader1 = orderMaker1.getOrderFXML();
            AnchorPane AdminOrderScreen = loader1.load();
            AdminOrderController controller1 = loader1.getController();
            controller1.setPane((AnchorPane) OrderScreen,order);
            ScrollPane.setContent(AdminOrderScreen);
        }

        else if(person instanceof User && myOrder){
            person = Singleton_Connector.getInstance().getUserByID(order.getUserId());
            controller.sendOrderInfo(order, (User) person);
            OrderMaker orderMaker1 = new OrderMaker(new MyOrderScreenFactory());
            FXMLLoader loader2 = orderMaker1.getOrderFXML();
            AnchorPane MyOrder = loader2.load();
            MyOrderController controller2 = loader2.getController();
            controller2.setPane((AnchorPane) OrderScreen,order);
            ScrollPane.setContent(MyOrder);
        }
        mainPane.getChildren().removeAll();
        mainPane.getChildren().setAll(ScrollPane);

    }
    public void getOrder(Order order, AnchorPane mainPane, Person person, Boolean myOrder, ComboBox comboBox) {
        this.myOrder = myOrder;
        this.mainPane = mainPane;
        this.order = order;
        this.person = person;
        this.comboBox = comboBox;

        setData();
    }
    private void setData() {
        File[] files = File_Handler.getOrderDirectory(order).listFiles();
        Image img;
        if(files == null || files.length == 0) {
            File temp = new File("Images\\no-image.jpg");
            img = new Image(temp.getAbsolutePath(), 200, 112, false, true);
        } else {
            img = new Image(files[0].getAbsolutePath(), 200, 112, false, true);
        }
        OrderImg.setImage(img);
        PriceLbl.setText("$"+String.valueOf(order.getPrice()));
        ModelLbl.setText(order.getModel());
    }
    public void setPending(boolean pending) {
        this.pending = pending;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
