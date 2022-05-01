package com.example.project;

public abstract class  Person {
    private int ID;
    private String Name;
    private int Age;
    private String Address;
    private String Email;
    private String PhoneNumber;

    public Person(int ID, String name, int age, String address, String email, String phoneNumber) {
        this.ID = ID;
        Name = name;
        Age = age;
        Address = address;
        Email = email;
        PhoneNumber = phoneNumber;
    }
    public  void test() {
        System.out.println(this.Name);
    }
}


