package com.user.services;

public class User {

    private int id;
    private String email;
    private String password;
    private String username;
    private String firstname;
    private String lastname;
    private int id_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }
    
    public String getlastName() {
        return lastname;
    }

    public void setlastName(String lastname) {
        this.lastname = lastname;
    }
    
    public String getfirstName() {
        return firstname;
    }

    public void setfirstName(String firstname) {
        this.firstname = firstname;
    }
}
