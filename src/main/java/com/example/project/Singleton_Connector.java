package com.example.project;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.File;
import java.security.spec.ECField;
import java.sql.*;
import java.util.ArrayList;

public class Singleton_Connector {
    Connection connection;
    private static Singleton_Connector instance;

    private Singleton_Connector() {

    }

    public static Singleton_Connector getInstance() {

        if (instance == null)
            instance = new Singleton_Connector();
        return instance;
    }

    private void establishConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database", "root", "rootpassword");
        ;
    }


    public Person checkCredentials(int ID, String password) throws SQLException {
        instance.establishConnection();
        String query = "SELECT * FROM tbl_users WHERE ID = " + ID;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        try {
            while (resultSet.next()) {
                if (password.equals(resultSet.getString("Password"))) {
                    String name = resultSet.getString("Name");
                    int age = resultSet.getInt("Age");
                    String address = resultSet.getString("Address");
                    String email = resultSet.getString("Email");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    System.out.println(name + " " + age + " " + address + " " + email + " " + phoneNumber);
                    if (resultSet.getInt("AdminLevel") == 0)
                        return new Admin_User(ID, name, age, address, email, phoneNumber);

                    else
                        return new User(ID, name, age, address, email, phoneNumber);

                } else {
                    // Account exists but wrong password, to be implemented later
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Order checkOrder(int userId, int orderId) throws SQLException {
        instance.establishConnection();
        String query = "SELECT * FROM tbl_orders WHERE User_ID = " + userId + " AND Order_ID = " + orderId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        try {

            while (resultSet.next()) {
                userId = resultSet.getInt("User_Id");
                orderId = resultSet.getInt("Order_ID");
                String carType = resultSet.getString("Car_Type");
                int price = resultSet.getInt("Price");
                String Transmission = resultSet.getString("Transmission");
                String Color = resultSet.getString("Color");
                String Model = resultSet.getString("Model");
                int year = resultSet.getInt("Year");
                int kilometers = resultSet.getInt("Kilometers");
                String ExtraInfo = resultSet.getString("Extra_Info");
                int status = resultSet.getInt("Status");

                if (!resultSet.next())
                    return new Order(userId, orderId, carType, price, Transmission, Color,
                            Model, year, kilometers, ExtraInfo, status);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void deleteOrder(int userId , int orderId) throws SQLException {
        instance.establishConnection();
        String query = "DELETE FROM tbl_orders WHERE User_ID = " + userId + " AND Order_ID = " + orderId;
        Statement statement = connection.createStatement();
        try {
            int resultSet = statement.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Order> getAllOrders(int userId) throws SQLException {
        
        instance.establishConnection();
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM tbl_orders WHERE User_ID = " + userId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        try{

            while(resultSet.next()){
                userId = resultSet.getInt("User_Id");
                int orderId = resultSet.getInt("Order_ID");
                String carType = resultSet.getString("Car_Type");
                int price = resultSet.getInt("Price");
                String Transmission = resultSet.getString("Transmission");
                String Color = resultSet.getString("Color");
                String Model = resultSet.getString("Model");
                int year = resultSet.getInt("Year");
                int kilometers = resultSet.getInt("Kilometers");
                String ExtraInfo = resultSet.getString("Extra_Info");
                int status = resultSet.getInt("Status");

                orders.add(new Order(userId, orderId, carType, price, Transmission, Color,
                        Model, year, kilometers, ExtraInfo, status) );
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    return orders;
    }
}

