/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerSubsystem;

public abstract class user {
    private String userName;
    private String mobileNumber;
    private String password;
    private String email;
    private UserStatus userStatus;
    
    public user(){}
    
    public user(String userName, String mobilenumber, String password, String email, UserStatus userStatus){
        this.userName = userName;
        this.mobileNumber = mobilenumber;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
    }
    
     public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    
    @Override
    public String toString() {
        return "Username: " + getUsername() + " , " + "Mobile Number: " + getMobileNumber() + " , " + "Email: " + getEmail() +
                " , " + "User Status: " + getUserStatus() + " , ";
    }
    
}