package com.example.admin.smartindia.Models;

import java.io.Serializable;

/**
 * Created by admin on 18/03/2017.
 */
public class PendingMediclaimData implements Serializable {
    private String hospitalName;
    private String date;
    private String amount;

    public PendingMediclaimData(String hospitalName, String date, String amount) {
        this.hospitalName = hospitalName;
        this.date = date;
        this.amount = amount;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}
