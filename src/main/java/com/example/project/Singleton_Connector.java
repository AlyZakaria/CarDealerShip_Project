package com.example.project;
import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Singleton_Connector {
    private static Connection connection;
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
    private void closeConnection() throws SQLException {
        connection.close();
    }

    public Person checkCredentials(String ID_string, String password) throws SQLException {
        if(ID_string.equals("") || password.equals("") ) {
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
                    String password_DB = resultSet.getString("Password");
                    int gender = resultSet.getInt("Gender");
                    String National_ID = resultSet.getString("National_ID");
                    System.out.println(name + " " + age + " " + address + " " + email + " " + phoneNumber);
                    if (resultSet.getInt("AdminLevel") == 0)
                        return new Admin_User(ID, name, age, address, email, phoneNumber, gender, password_DB, National_ID);
                    else if (resultSet.getInt("AdminLevel") == 1)
                        return new User(ID, name, age, address, email, phoneNumber, gender, password_DB, National_ID);
                    else
                        throw new InvalidInputException();

                } else {
                    //Wrong password
                    throw new WrongPasswordException();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
        throw new InvalidInputException();
    }


    public void deleteOrder(Order order) throws SQLException {
        instance.establishConnection();
        String query = "DELETE FROM tbl_orders WHERE Order_ID = " + order.getOrderId();
        Statement statement = connection.createStatement();
        try {
            int resultSet = statement.executeUpdate(query);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
    }

    public void deleteUser(User user) throws SQLException {
        instance.establishConnection();
        String query = "DELETE FROM tbl_users WHERE ID = " +user.getID();
        Statement statement = connection.createStatement();
        try {
            int resultSet = statement.executeUpdate(query);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
    }
    public ArrayList<Order> getAllUserOrders(User user) throws SQLException {
        instance.establishConnection();
        ArrayList<Order> orders = new ArrayList<>();
        //why is there an ID? ID isn't needed @Aly
        String query = "SELECT * FROM tbl_orders WHERE User_ID = " + user.getID();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        try{

            while(resultSet.next()){
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
                orders.add(new Order(user.getID(), orderId, carType, price, Transmission, Color,
                        Model, year, kilometers, ExtraInfo, status) );
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
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
        } finally {
            instance.closeConnection();
        }
        return last +1;
    }

    public int getLastUserID() throws SQLException {

        instance.establishConnection();
        String query = "SELECT max(ID) FROM tbl_users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Here");
        int last = -1;
        try {
            while(resultSet.next()) {
                last = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
        return last +1;
    }

    public void addOrder(Order order) throws SQLException {
        instance.establishConnection();
        String query = "INSERT INTO `project_database`.`tbl_orders` (User_ID, Order_ID, Car_Type, Price, Transmission, Color, Model, Year, Kilometers, Extra_Info, Status)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, order.getUserId());
            preparedStmt.setInt(2, order.getOrderId() );
            preparedStmt.setString(3, order.getCarType());
            preparedStmt.setInt(4, order.getPrice());
            preparedStmt.setString(5,order.getTransmission() );
            preparedStmt.setString(6, order.getColor());
            preparedStmt.setString(7, order.getModel() );
            preparedStmt.setInt(8, order.getYear());
            preparedStmt.setInt(9, order.getKilometers());
            preparedStmt.setString(10, order.getExtraInfo() );
            preparedStmt.setInt(11, order.getStatus());
            preparedStmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
    }
    public int userExists(String National_ID) throws SQLException {
        instance.establishConnection();

        String query = "SELECT * FROM tbl_users WHERE National_ID = " + National_ID;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        int ID = -1;

        try {
            while(resultSet.next()) {
                ID = resultSet.getInt("ID");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
        return ID;
    }
    public void addUser(User user) throws SQLException {
        if(userExists(user.getNational_ID()) != -1) {
            throw new UserExistsException();
        }
        instance.establishConnection();
        String query = "INSERT INTO `project_database`.`tbl_users` (ID, Name, Age, Address, Email, PhoneNumber, AdminLevel, Password, Gender, National_ID)"
                +   "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user.getID());
            preparedStmt.setString(2, user.getName());
            preparedStmt.setInt(3, user.getAge());
            preparedStmt.setString(4, user.getAddress());
            preparedStmt.setString(5, user.getEmail());
            preparedStmt.setString(6, user.getPhoneNumber());
            preparedStmt.setInt(7, 1);
            preparedStmt.setString(8, user.getPassword());
            preparedStmt.setInt(9, user.getGender());
            preparedStmt.setString(10, user.getNational_ID());

            preparedStmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        instance.establishConnection();
        ArrayList<Order> orders = new ArrayList<>();
        //change it later
        String query = "SELECT * FROM tbl_orders WHERE Status = 0";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try{

            while(resultSet.next()){
                int userId = resultSet.getInt("User_ID");
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

        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
        return orders;
    }

    public void deleteUserOrders(User user) throws SQLException {
        instance.establishConnection();
        String query = "DELETE FROM tbl_orders WHERE User_ID = " + user.getID();
        Statement statement = connection.createStatement();
        try {
          statement.executeUpdate(query);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
    }

    public static User EditInfo(User user, String Email, String PhoneNumber,String Address) throws SQLException {
        instance.establishConnection();
        String query = "UPDATE tbl_users " + " SET Email=?, PhoneNumber=?, Address=?"  + "WHERE ID=?";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, Email);

            preparedStmt.setString(2,PhoneNumber);
            preparedStmt.setString(3, Address);
            preparedStmt.setInt(4,user.getID());
            preparedStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            instance.closeConnection();
        }
        return new User(user.getID(),user.getName(),user.getAge(),Address,Email,PhoneNumber,user.getGender(),user.getPassword(),
                user.getNational_ID());
    }

    public boolean changePassword(int id, String oldpassword, String newpassword) throws SQLException {

        instance.establishConnection();
        String query = "UPDATE `project_database`.`tbl_users`" +" SET Password = '" + newpassword + "' WHERE ID = " +  id;
            Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            instance.closeConnection();
            return true;
        }
    }

    public ArrayList<Order> getAllPendingOrders() throws SQLException {
        instance.establishConnection();
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM tbl_orders WHERE Status = 0";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try{

            while(resultSet.next()){
                int userId = resultSet.getInt("User_ID");
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

        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
        return orders;
    }
    public void confirmOrder(Order order) throws SQLException {
        instance.establishConnection();
        String query = "UPDATE `project_database`.`tbl_orders` SET Status = 1  WHERE Order_ID = " +  order.getOrderId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
    }
    public static User getUserByID(int userID) throws SQLException {
        instance.establishConnection();
        String query = "SELECT * FROM tbl_users WHERE ID = " + userID;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String name = null;
        int age = 0;
        String address = null;
        String email = null;
        String phoneNumber = null;
        String password_DB = null;
        int gender = 0;
        String National_ID = null;
        try {
            while(resultSet.next()) {
                    name = resultSet.getString("Name");
                    age = resultSet.getInt("Age");
                    address = resultSet.getString("Address");
                    email = resultSet.getString("Email");
                    phoneNumber = resultSet.getString("PhoneNumber");
                    password_DB = resultSet.getString("Password");
                    gender = resultSet.getInt("Gender");
                    National_ID = resultSet.getString("National_ID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            instance.closeConnection();
        }
        return new User(userID, name, age, address, email, phoneNumber, gender, password_DB, National_ID);
    }
}

