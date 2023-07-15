package com.example.keepactivebackend.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")


    private Long id;
    private  String username;
    private String email;
    private String password;
    private String imageUrl;
    private String imagePath;
    private boolean isVerified;
    private String passwordResetToken;
    private LocalDate passwordResetExpires;
    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public LocalDate getPasswordResetExpires() {
        return passwordResetExpires;
    }

    public void setPasswordResetExpires(LocalDate passwordResetExpires) {
        this.passwordResetExpires = passwordResetExpires;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isVerified=" + isVerified +
                ", passwordResetToken='" + passwordResetToken + '\'' +
                ", passwordResetExpires=" + passwordResetExpires +
                '}';
    }
}
