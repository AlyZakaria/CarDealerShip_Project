package com.example.project;
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
    }


    public Person checkCredentials(String ID_string, String password) throws SQLException {
        if(ID_string == "" || password == "" ) {
            //wrong input
            throw  new LoginExceptionEmpty();
        }
        int ID;
        try {
            ID = Integer.parseInt(ID_string);
        } catch (Exception e) {
            throw  new InvalidInputException();
        }
        instance.establishConnection();
        String query = "SELECT * FROM tbl_users WHERE ID = " + ID;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        try {
            while(resultSet.next()) {
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
                    //Wrong password
                    throw new WrongPasswordException();
                }
            }
        } catch (SQLException e) {
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
                userId = resultSet.getInt("User_ID");
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
        //why is there an ID? ID isn't needed @Aly
        String query = "SELECT * FROM tbl_orders WHERE User_ID = " + userId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        try{

            while(resultSet.next()){
                userId = resultSet.getInt("User_ID");
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

    public int getLastOrderID() throws SQLException {
        instance.establishConnection();
        String query = "SELECT max(Order_ID) FROM tbl_orders";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int last = -1;
        try {
            while(resultSet.next()) {
                last = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return last +1;
    }
}

