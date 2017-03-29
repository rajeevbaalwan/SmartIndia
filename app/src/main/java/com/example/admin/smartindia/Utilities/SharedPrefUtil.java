package com.example.admin.smartindia.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.smartindia.Models.User;

/**
 * Created by admin on 24/03/2017.
 */
public class SharedPrefUtil {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String KEY_LOGGED_IN = "is_logged_in";
    private String KEY_FILE_NAME = "smart_india_shared_pref";
    private String KEY_USER_NAME = "user_name";
    private String KEY_AADHAR = "aadhar_no";
    private String KEY_DOB = "dob";
    private String KEY_BLOOD_GROUP = "blood_group";
    private String KEY_EMAIL = "email";
    private String KEY_PHONE = "phone";
    private String KEY_ADDRESS = "address";
    private String KEY_USER_ID = "user_id";
    private Context context;

    public SharedPrefUtil(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(KEY_FILE_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();

        if(!sharedPreferences.contains(KEY_LOGGED_IN)){
            editor.putBoolean(KEY_LOGGED_IN,false);
        }
    }

    public void startRegisterSession(User user){
        editor.putBoolean(KEY_LOGGED_IN,true);
        editor.putString(KEY_USER_ID,user.getUserId());
        editor.putString(KEY_USER_NAME,user.getUserName());
        editor.putString(KEY_AADHAR,user.getUserAadhar());
        editor.putString(KEY_DOB,user.getUserDob());
        editor.putString(KEY_BLOOD_GROUP,user.getUserBloodGroup());
        editor.putString(KEY_EMAIL,user.getUserEmail());
        editor.putString(KEY_PHONE,user.getUserPhone());
        editor.putString(KEY_ADDRESS,user.getUserAddress());
        editor.commit();
    }

    public Boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_LOGGED_IN,false);
    }

    public User getLoggedInUser(){
        return new User(sharedPreferences.getString(KEY_USER_ID,null),sharedPreferences.getString(KEY_ADDRESS,null),sharedPreferences.getString(KEY_PHONE,null)
                        ,sharedPreferences.getString(KEY_EMAIL,null),sharedPreferences.getString(KEY_BLOOD_GROUP,null)
                        ,sharedPreferences.getString(KEY_DOB,null),sharedPreferences.getString(KEY_AADHAR,null)
                        ,sharedPreferences.getString(KEY_USER_NAME,null));
    }
    public void userLogOut(){
        editor.clear().commit();
    }


}
