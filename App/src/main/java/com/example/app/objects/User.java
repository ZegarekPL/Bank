package com.example.app.objects;

public class User {
    private String user_name;
    private String user_surname;
    private String user_birth;
    private String user_phone;
    private String user_password;
    private String user_login;
    private String user_email;

    public User(){
        this.user_name="";
        this.user_surname="";
        this.user_birth="";
        this.user_phone="";
        this.user_password="";
        this.user_login="";
        this.user_email="";
    }

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) { this.user_name = user_name; }

    public String getUser_surname() {
        return user_surname;
    }
    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_birth() {
        return user_birth;
    }
    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_phone() {
        return user_phone;
    }
    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_login() {
        return user_login;
    }
    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
