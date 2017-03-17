package com.example.admin.smartindia.Models;

import java.io.Serializable;

/**
 * Created by admin on 17/03/2017.
 */
public class UserCurrentMedicalData implements Serializable {
    private String time;
    private String med;

    public UserCurrentMedicalData(String time, String med) {
        this.time=time;
        this.med=med;
    }

    public String getTime() {
        return time;
    }

    public String getMed() {
        return med;
    }
}
