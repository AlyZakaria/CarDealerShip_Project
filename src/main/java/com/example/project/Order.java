package com.example.project;

import java.sql.SQLException;
import java.util.ArrayList;

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

        public Order(int userId,int orderId, String carType, int price, String Transmission, String Color,
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

        public static ArrayList<Order> getAllOrders(int userId) throws SQLException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            return instance.getAllOrders(userId);
        }

        public void DeleteOrder(int userId, int orderId) throws SQLException {
            Singleton_Connector instance = Singleton_Connector.getInstance();
            instance.deleteOrder(userId, orderId);
        }

        public void test(){
            System.out.println(carType + " " + price +" " + Transmission + " " + Color + " " + Model + " " + year +
                    " "  + kilometers + " " + ExtraInfo + " " + status );
        }
}
