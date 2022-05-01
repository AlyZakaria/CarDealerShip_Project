package com.example.project;


import java.sql.*;

public class Singleton_Connector {
    Connection connection;
    private static Singleton_Connector instance;

    private Singleton_Connector() {

    }

    public static Singleton_Connector getInstance() {

        if(instance == null)
            instance = new Singleton_Connector();
        return instance;
    }

    private void establishConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database","root","rootpassword");
    }


    public Person checkCredentials(int ID, String password) throws SQLException {
        instance.establishConnection();
        String query = "SELECT * FROM tbl_users WHERE ID = " + ID;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        try {
            while(resultSet.next()) {
                if(password.equals(resultSet.getString("Password"))) {
                    String name = resultSet.getString("Name");
                    int age = resultSet.getInt("Age");
                    String address = resultSet.getString("Address");
                    String email = resultSet.getString("Email");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    System.out.println(name + " " + age + " " + address + " " + email +  " " + phoneNumber) ;

                    if(resultSet.getInt("AdminLevel") == 0)
                        return new Admin_User(ID, name, age, address, email, phoneNumber);

                    else
                        return new User(ID, name, age, address, email, phoneNumber);

                } else {
                    // Account exists but wrong password, to be implemented later
                    System.out.println("Wrong password");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
