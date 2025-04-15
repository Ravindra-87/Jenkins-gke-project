package com.example.tryfirstproject.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users_details") // Specifies the table name
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increment primary key
    private Long user_id;

    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "user_email", nullable = false, unique = true)
    private String useremail;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}