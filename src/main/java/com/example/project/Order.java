package com.example.project;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order {
        private int userId;
        private int orderId;
        private String carType;
        private int price;
        private String Transmission;
        private String Color;
        private String Model;
        private int year;
        private int kilometers;
        private String ExtraInfo;
        private int status;

    public int getUserId() {
        return userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCarType() {
        return carType;
    }

    public int getPrice() {
        return price;
    }

    public String getTransmission() {
        return Transmission;
    }

    public String getColor() {
        return Color;
    }

    public String getModel() {
        return Model;
    }

    public int getYear() {
        return year;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getExtraInfo() {
        return ExtraInfo;
    }

    public int getStatus() {
        return status;
    }

    public Order(int userId, int orderId, String carType, int price, String Transmission, String Color,
                 String Model, int year, int kilometers, String ExtraInfo, int status){
         this.userId = userId;
         this.orderId = orderId;
         this.carType = carType;
         this.price = price;
         this.Transmission = Transmission;
         this.Color = Color;
         this.Model = Model;
         this.year = year;
         this.kilometers = kilometers;
         this.ExtraInfo = ExtraInfo;
         this.status = status;
        }

        public static Order getOrder(int userId,int orderId) throws SQLException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            return instance.checkOrder(userId,orderId);
        }

        public static ArrayList<Order> getAllUserOrders(User user) throws SQLException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            return instance.getAllUserOrders(user);
        }

        public void DeleteOrder() throws SQLException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            File_Handler.deleteOrder(this);
            instance.deleteOrder(this);
        }
        public void AddOrder(List<File> list) throws SQLException, IOException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            instance.addOrder(this);
            File_Handler.createOrderFile(list, this);
        }
        public static ArrayList<Order> getAllOrders() throws SQLException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            return instance.getAllOrders();
        }
        public void test(){
            System.out.println(carType + " " + price +" " + Transmission + " " + Color + " " + Model + " " + year +
                    " "  + kilometers + " " + ExtraInfo + " " + status);
        }
}
