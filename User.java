package com.example.backend;

public class User {
    //Placeholder User set up for functionality in adminScene class
    //AW 5/1/2024
    private String username;
    private String password;
    private boolean adminPriveleges;
    public User(String username, String password, boolean adminPriveleges){
        this.username = username;
        this.password = password;
        this.adminPriveleges = adminPriveleges;
    }
    public String toString(){
        return username;
    }
}
