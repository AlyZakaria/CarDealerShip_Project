package com.example.project;

public class  Person {
    private int ID;
    private String Name;
    private int Age;
    private String Address;
    private String Email;
    private String PhoneNumber;
    private int gender;
    private String password;
    private String National_ID;
    public Person() {

    }
    public Person(int ID, String name, int age, String address, String email, String phoneNumber, int gender, String password, String National_ID) {
        this.ID = ID;
        this.Name = name;
        this.Age = age;
        this.Address = address;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
        this.National_ID = National_ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getNational_ID() {
        return National_ID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}


