package com.example.project;

import java.sql.SQLException;

public class User extends Person{
    public User(int ID, String name, int age, String address, String email, String phoneNumber, int gender, String password, String National_ID) {
        super(ID, name, age, address, email, phoneNumber, gender, password, National_ID);
    }
    public static User EditInfo(User user, String Email , String PhoneNumber,String Address) throws SQLException {
        Singleton_Connector instance = Singleton_Connector.getInstance();
        return instance.EditInfo(user,Email, PhoneNumber,Address);
    }
}
