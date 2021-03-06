package com.example.admin.smartindia.Models;

import java.io.Serializable;

/**
 * Created by admin on 18/03/2017.
 */
public class UserMedicalHistoryData implements Serializable {
    private String hname;
    private String doctor;
    private String issue;
    private String medicine;
    private String date;

    public UserMedicalHistoryData(String hname, String doctor, String issue, String medicine, String date) {
        this.hname = hname;
        this.doctor = doctor;
        this.issue = issue;
        this.medicine = medicine;
        this.date = date;
    }

    public String getName() {
        return hname;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getIssue() {
        return issue;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDate() {
        return date;
    }
}
