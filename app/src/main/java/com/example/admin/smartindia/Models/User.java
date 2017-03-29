package com.example.admin.smartindia.Models;

import java.io.Serializable;

/**
 * Created by admin on 25/03/2017.
 */
public class User implements Serializable{
    private String userName;
    private String userAadhar;
    private String userDob;
    private String userBloodGroup;
    private String userEmail;
    private String userPhone;
    private String userAddress;

    public User(String userAddress, String userPhone, String userEmail, String userBloodGroup, String userDob, String userAadhar, String userName) {
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userBloodGroup = userBloodGroup;
        this.userDob = userDob;
        this.userAadhar = userAadhar;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAadhar() {
        return userAadhar;
    }

    public String getUserDob() {
        return userDob;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

}
