package com.example.admin.smartindia.Models;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by admin on 01/04/2017.
 */
public class EmergencyHospitalData implements Serializable {
    private String hospitalName;
    private String hospitalAddress;
    private String emergencySpecialist;
    private String emergencyHospitalOpenHours;
    private String emergencyHospitalHelpilne;

    public EmergencyHospitalData(String hospitalName, String hospitalAddress, String emergencySpecialist,
                                 String emergencyHospitalOpenHours, String emergencyHospitalHelpilne) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.emergencySpecialist = emergencySpecialist;
        this.emergencyHospitalOpenHours = emergencyHospitalOpenHours;
        this.emergencyHospitalHelpilne = emergencyHospitalHelpilne;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public String getEmergencySpecialist() {
        return emergencySpecialist;
    }

    public String getEmergencyHospitalOpenHours() {
        return emergencyHospitalOpenHours;
    }

    public String getEmergencyHospitalHelpilne() {
        return emergencyHospitalHelpilne;
    }
}
