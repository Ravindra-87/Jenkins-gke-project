package com.example.tryfirstproject.model;

public class Users
{
    private String name;
    private String email;

    public Users(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Users() {

    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
