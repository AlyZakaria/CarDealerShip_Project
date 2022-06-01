package com.example.project;

import java.sql.SQLException;
import java.util.Random;

public class Admin_User extends Person{
    public Admin_User(int ID, String name, int age, String address, String email, String phoneNumber, int gender, String password, String National_ID) {
        super(ID, name, age, address, email, phoneNumber, gender, password, National_ID);
    }
    public Admin_User() {

    }

    @Override
    public int getAdminlvl() {
        return 0;
    }

    public String addUser(User user) throws SQLException {
        String password = generatePassword();
        Singleton_Connector instance = Singleton_Connector.getInstance();
        user.setPassword(password);
        instance.addUser(user);
        return password;
    }
    public void deleteUser(User user) throws SQLException {
        Singleton_Connector instance = Singleton_Connector.getInstance();
        instance.deleteUser(user);
        File_Handler.deleteAllUserOrdersFiles(user);
        instance.deleteUserOrders(user);
    }
    private String generatePassword() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }
}
